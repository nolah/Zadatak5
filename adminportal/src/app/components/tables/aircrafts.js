(function () {
    'use strict';

    angular
        .module('adminportal')
        .directive('aircrafts', function () {
            return {
                restrict: 'E',
                scope: {},
                templateUrl: 'src/app/components/tables/aircrafts.html',
                controller: 'AircraftsController'
            };
        });

    angular
        .module('adminportal')
        .controller('AircraftsController', AircraftsController);

    AircraftsController.$inject = ['$scope', 'dialogService', 'eventBus', 'adminApi'];

    function AircraftsController($scope, dialogService, eventBus, adminApi) {

        $scope.model = [];
        $scope.errorCode = null;
        $scope.onAircraftCreated = eventBus.onEvent('AircraftCreated', onAircraftCreated, $scope);
        $scope.onAircraftUpdated = eventBus.onEvent('AircraftUpdated', onAircraftUpdated, $scope);
        $scope.onClickEditAircraft = onClickEditAircraft;

        onInit();

        function onInit() {

            adminApi.aircrafts().then((data) => {
                $scope.errorCode = null;
                $scope.model = data.map((item) => {
                    return {
                        id: item.id,
                        maker: item.maker,
                        type: item.type
                    }
                });
            }).catch(onError);

        }

        function onAircraftCreated(event, payload) {
            onInit();
        }

        function onAircraftUpdated(event, payload) {
            onInit();
        }

        function onClickEditAircraft(item) {
            dialogService.openUpdateAircraftForm({
                id: item.id
            });
        }

        function onError(response) {
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