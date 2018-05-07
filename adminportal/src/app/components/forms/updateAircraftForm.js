(function () {
    'use strict';

    angular
        .module('adminportal')
        .directive('updateAircraftForm', function () {
            return {
                restrict: 'E',
                replace: true,
                scope: {
                    dialog: '=',
                    id: '='
                },
                templateUrl: 'src/app/components/forms/updateAircraftForm.html',
                controller: 'UpdateAircraftFormController'
            };
        });

    angular
        .module('adminportal')
        .controller('UpdateAircraftFormController', UpdateAircraftFormController);

    UpdateAircraftFormController.$inject = ['$scope', 'dialogService', 'eventBus', 'adminApi'];

    function UpdateAircraftFormController($scope, dialogService, eventBus, adminApi) {

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
            adminApi.readAircraft(request).then((data) => {
                $scope.errorCode = null;
                $scope.model.id = data.id;
                $scope.model.maker = data.maker;
                $scope.model.type = data.type;
                $scope.model.airlineId = data.airlineId;
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
                maker: $scope.model.maker,
                type: $scope.model.type,
                airlineId: $scope.model.airlineId
            };
            adminApi.updateAircraft(request).then((data) => {
                $scope.submitDisabled = false;
                $scope.errorCode = null;
                eventBus.emitEvent('AircraftUpdated', {});
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