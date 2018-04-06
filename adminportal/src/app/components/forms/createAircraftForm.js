(function () {
    'use strict';

    angular
        .module('adminportal')
        .directive('createAircraftForm', function () {
            return {
                restrict: 'E',
                replace: true,
                scope: {
                    dialog: '='
                },
                templateUrl: 'src/app/components/forms/createAircraftForm.html',
                controller: 'CreateAircraftFormController'
            };
        });

    angular
        .module('adminportal')
        .controller('CreateAircraftFormController', CreateAircraftFormController);

    CreateAircraftFormController.$inject = ['$scope', 'dialogService', 'eventBus', 'adminApi'];

    function CreateAircraftFormController($scope, dialogService, eventBus, adminApi) {

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
                maker: $scope.model.maker,
                type: $scope.model.type
            };
            adminApi.createAircraft(request).then((data) => {
                $scope.submitDisabled = false;
                $scope.errorCode = null;
                eventBus.emitEvent('AircraftCreated', {});
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