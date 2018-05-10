(function () {
    'use strict';

    angular
        .module('adminportal')
        .directive('createFlightsForm', function () {
            return {
                restrict: 'E',
                replace: true,
                scope: {
                    dialog: '='
                },
                templateUrl: 'src/app/components/forms/createFlightsForm.html',
                controller: 'CreateFlightsFormController'
            };
        });

    angular
        .module('adminportal')
        .controller('CreateFlightsFormController', CreateFlightsFormController);

    CreateFlightsFormController.$inject = ['$scope', 'dialogService', 'eventBus', 'adminApi'];

    function CreateFlightsFormController($scope, dialogService, eventBus, adminApi) {

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
                aircraftId: $scope.model.aircraftId,
                fromDate: $scope.model.fromDate,
                toDate: $scope.model.toDate,
                schemeType: $scope.model.schemeType,
                numberOfEconomySeats: $scope.model.numberOfEconomySeats,
                priceOfEconomySeat: $scope.model.priceOfEconomySeat,
                numberOfBusinessSeats: $scope.model.numberOfBusinessSeats,
                priceOfBusinessSeats: $scope.model.priceOfBusinessSeats,
                fromAirport: $scope.model.fromAirport,
                toAirport: $scope.model.toAirport
            };
            adminApi.createFlights(request).then((data) => {
                $scope.submitDisabled = false;
                $scope.errorCode = null;
                eventBus.emitEvent('FlightCreated', {});
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