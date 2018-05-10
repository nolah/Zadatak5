(function () {
    'use strict';

    angular
        .module('adminportal')
        .directive('aircraftDropdown', function () {
            return {
                restrict: 'E',
                scope: {
                    htmlName: '@',
                    htmlRequired: '@',
                    htmlId: '@',
                    htmlPlaceholder: '@',
                    selectedId: '=?'
                },
                templateUrl: 'src/app/components/dropdowns/entities/aircraftDropdown.html',
                controller: 'AircraftDropdownController'
            };
        });

    angular
        .module('adminportal')
        .controller('AircraftDropdownController', AircraftDropdownController);

    AircraftDropdownController.$inject = ['$scope', 'dialogService', 'adminApi'];

    function AircraftDropdownController($scope, dialogService, adminApi) {

        $scope.model = [];
        $scope.errorCode = null;

        onInit();

        function onInit() {

            adminApi.aircrafts().then((data) => {
                $scope.errorCode = null;
                $scope.model = data.map((item) => {
                    return {
                        id: item.id,
                        maker: item.maker,
                        type: item.type,
                        airlineId: item.airlineId,
                        airlineName: item.airlineName,
                        airlineDescription: item.airlineDescription,
                        airlineLuggageDetails: item.airlineLuggageDetails,
                        label: item.maker + ', ' + item.type
                    }
                });
            }).catch(onError);

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