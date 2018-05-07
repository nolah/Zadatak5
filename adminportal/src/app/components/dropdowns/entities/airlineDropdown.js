(function () {
    'use strict';

    angular
        .module('adminportal')
        .directive('airlineDropdown', function () {
            return {
                restrict: 'E',
                scope: {
                    htmlName: '@',
                    htmlRequired: '@',
                    htmlId: '@',
                    htmlPlaceholder: '@',
                    selectedId: '=?'
                },
                templateUrl: 'src/app/components/dropdowns/entities/airlineDropdown.html',
                controller: 'AirlineDropdownController'
            };
        });

    angular
        .module('adminportal')
        .controller('AirlineDropdownController', AirlineDropdownController);

    AirlineDropdownController.$inject = ['$scope', 'dialogService', 'adminApi'];

    function AirlineDropdownController($scope, dialogService, adminApi) {

        $scope.model = [];
        $scope.errorCode = null;

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