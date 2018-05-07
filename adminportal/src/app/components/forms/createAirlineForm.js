(function () {
    'use strict';

    angular
        .module('adminportal')
        .directive('createAirlineForm', function () {
            return {
                restrict: 'E',
                replace: true,
                scope: {
                    dialog: '='
                },
                templateUrl: 'src/app/components/forms/createAirlineForm.html',
                controller: 'CreateAirlineFormController'
            };
        });

    angular
        .module('adminportal')
        .controller('CreateAirlineFormController', CreateAirlineFormController);

    CreateAirlineFormController.$inject = ['$scope', 'dialogService', 'eventBus', 'adminApi', 'conversionUtilityService'];

    function CreateAirlineFormController($scope, dialogService, eventBus, adminApi, conversionUtilityService) {

        $scope.model = {};
        $scope.errorCode = null;
        $scope.submitDisabled = false;

        $scope.onClickSubmit = onClickSubmit;

        function onClickSubmit(form) {
            if (form.$invalid) {
                form.$setSubmitted();
                return false;
            }
            $scope.submitDisabled = true;
            let request = {
                name: $scope.model.name,
                description: $scope.model.description,
                luggageDetails: conversionUtilityService.convertEmpty($scope.model.luggageDetails)
            };
            adminApi.createAirline(request).then((data) => {
                $scope.submitDisabled = false;
                $scope.errorCode = null;
                eventBus.emitEvent('AirlineCreated', {});
                if ($scope.dialog) $scope.dialog.close();
            }).catch(onError);

        }

        function onError(response) {
            $scope.submitDisabled = false;
            if (response.status && response.statusText) {
                $scope.errorCode = response.statusText;
            } else {
                $scope.errorCode = 'SOMETHING_WENT_WRONG';
            }

            let dialog = dialogService.openErrorDialog({
                title: 'OOPS',
                message: $scope.errorCode
            });
        }

    }
})();