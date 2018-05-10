(function () {
    'use strict';

    angular
        .module('adminportal')
        .directive('updateFlightForm', function () {
            return {
                restrict: 'E',
                replace: true,
                scope: {
                    dialog: '=',
                    id: '='
                },
                templateUrl: 'src/app/components/forms/updateFlightForm.html',
                controller: 'UpdateFlightFormController'
            };
        });

    angular
        .module('adminportal')
        .controller('UpdateFlightFormController', UpdateFlightFormController);

    UpdateFlightFormController.$inject = ['$scope', 'dialogService', 'eventBus', 'adminApi'];

    function UpdateFlightFormController($scope, dialogService, eventBus, adminApi) {

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
            adminApi.readFlight(request).then((data) => {
                $scope.errorCode = null;
                $scope.model.id = data.id;
                $scope.model.aircraftId = data.aircraftId;
                $scope.model.timestamp = data.timestamp === null ? null : new Date(data.timestamp);
                $scope.model.numberOfEconomySeats = data.numberOfEconomySeats;
                $scope.model.priceOfEconomySeat = data.priceOfEconomySeat;
                $scope.model.numberOfBusinessSeats = data.numberOfBusinessSeats;
                $scope.model.priceOfBusinessSeats = data.priceOfBusinessSeats;
                $scope.model.fromAirport = data.fromAirport;
                $scope.model.toAirport = data.toAirport;
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
                aircraftId: $scope.model.aircraftId,
                timestamp: $scope.model.timestamp,
                numberOfEconomySeats: $scope.model.numberOfEconomySeats,
                priceOfEconomySeat: $scope.model.priceOfEconomySeat,
                numberOfBusinessSeats: $scope.model.numberOfBusinessSeats,
                priceOfBusinessSeats: $scope.model.priceOfBusinessSeats,
                fromAirport: $scope.model.fromAirport,
                toAirport: $scope.model.toAirport
            };
            adminApi.updateFlight(request).then((data) => {
                $scope.submitDisabled = false;
                $scope.errorCode = null;
                eventBus.emitEvent('FlightUpdated', {});
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