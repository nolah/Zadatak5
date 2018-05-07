(function () {
    'use strict';

    angular
        .module('adminportal')
        .directive('airlines', function () {
            return {
                restrict: 'E',
                scope: {},
                templateUrl: 'src/app/components/tables/airlines.html',
                controller: 'AirlinesController'
            };
        });

    angular
        .module('adminportal')
        .controller('AirlinesController', AirlinesController);

    AirlinesController.$inject = ['$scope', 'dialogService', 'eventBus', 'adminApi'];

    function AirlinesController($scope, dialogService, eventBus, adminApi) {

        $scope.model = [];
        $scope.errorCode = null;
        $scope.onAirlineCreated = eventBus.onEvent('AirlineCreated', onAirlineCreated, $scope);
        $scope.onAirlineUpdated = eventBus.onEvent('AirlineUpdated', onAirlineUpdated, $scope);
        $scope.onClickEditAirlines = onClickEditAirlines;

        onInit();

        function onInit() {

            adminApi.airlines().then((data) => {
                $scope.errorCode = null;
                $scope.model = data.map((item) => {
                    return {
                        id: item.id,
                        name: item.name,
                        description: item.description,
                        luggageDetails: item.luggageDetails
                    }
                });
            }).catch(onError);

        }

        function onAirlineCreated(event, payload) {
            onInit();
        }

        function onAirlineUpdated(event, payload) {
            onInit();
        }

        function onClickEditAirlines(item) {
            dialogService.openUpdateAirlineForm({
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