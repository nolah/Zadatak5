(function () {
    'use strict';

    angular
        .module('adminportal')
        .directive('updateAirlineForm', function () {
            return {
                restrict: 'E',
                replace: true,
                scope: {
                    dialog: '=',
                    id: '='
                },
                templateUrl: 'src/app/components/forms/updateAirlineForm.html',
                controller: 'UpdateAirlineFormController'
            };
        });

    angular
        .module('adminportal')
        .controller('UpdateAirlineFormController', UpdateAirlineFormController);

    UpdateAirlineFormController.$inject = ['$scope', 'dialogService', 'eventBus', 'adminApi', 'conversionUtilityService'];

    function UpdateAirlineFormController($scope, dialogService, eventBus, adminApi, conversionUtilityService) {

        $scope.model = {};
        $scope.errorCode = null;
        $scope.submitDisabled = false;

        $scope.onClickSubmit = onClickSubmit;

        if ($scope.id !== undefined) {
            onInit($scope.id);
        }

        function onInit(id) {

            let request = {
                id: id
            };
            adminApi.readAirline(request).then((data) => {
                $scope.errorCode = null;
                $scope.model.id = data.id;
                $scope.model.name = data.name;
                $scope.model.description = data.description;
                $scope.model.luggageDetails = data.luggageDetails;
            }).catch(onError);

        }

        function onClickSubmit(form) {
            if (form.$invalid) {
                form.$setSubmitted();
                return false;
            }
            $scope.submitDisabled = true;
            let request = {
                id: $scope.model.id,
                name: $scope.model.name,
                description: $scope.model.description,
                luggageDetails: conversionUtilityService.convertEmpty($scope.model.luggageDetails)
            };
            adminApi.updateAirline(request).then((data) => {
                $scope.submitDisabled = false;
                $scope.errorCode = null;
                eventBus.emitEvent('AirlineUpdated', {});
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