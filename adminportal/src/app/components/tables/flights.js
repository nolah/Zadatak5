(function () {
    'use strict';

    angular
        .module('adminportal')
        .directive('flights', function () {
            return {
                restrict: 'E',
                scope: {},
                templateUrl: 'src/app/components/tables/flights.html',
                controller: 'FlightsController'
            };
        });

    angular
        .module('adminportal')
        .controller('FlightsController', FlightsController);

    FlightsController.$inject = ['$scope', 'dialogService', 'eventBus', 'adminApi'];

    function FlightsController($scope, dialogService, eventBus, adminApi) {

        $scope.model = [];
        $scope.errorCode = null;
        $scope.onFlightCreated = eventBus.onEvent('FlightCreated', onFlightCreated, $scope);
        $scope.onFlightUpdated = eventBus.onEvent('FlightUpdated', onFlightUpdated, $scope);
        $scope.onClickUpdateFlight = onClickUpdateFlight;

        onInit();

        function onInit() {

            adminApi.flights().then((data) => {
                $scope.errorCode = null;
                $scope.model = data.map((item) => {
                    return {
                        id: item.id,
                        aircraftId: item.aircraftId,
                        timestamp: new Date(item.timestamp),
                        numberOfEconomySeats: item.numberOfEconomySeats,
                        priceOfEconomySeat: item.priceOfEconomySeat,
                        numberOfBusinessSeats: item.numberOfBusinessSeats,
                        priceOfBusinessSeats: item.priceOfBusinessSeats,
                        fromAirport: item.fromAirport,
                        toAirport: item.toAirport,
                        aircraftMaker: item.aircraftMaker,
                        aircraftType: item.aircraftType,
                        aircraftAirlineId: item.aircraftAirlineId,
                        airlineId: item.airlineId,
                        airlineName: item.airlineName,
                        airlineDescription: item.airlineDescription,
                        airlineLuggageDetails: item.airlineLuggageDetails
                    }
                });
            }).catch(onError);

        }

        function onFlightCreated(event, payload) {
            onInit();
        }

        function onFlightUpdated(event, payload) {
            onInit();
        }

        function onClickUpdateFlight(item) {
            dialogService.openUpdateFlightForm({
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