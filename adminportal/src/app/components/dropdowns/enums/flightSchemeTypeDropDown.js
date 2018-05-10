(function () {
    'use strict';

    angular
        .module('adminportal')
        .directive('flightSchemeTypeDropDown', function () {
            return {
                restrict: 'E',
                scope: {
                    htmlName: '@',
                    htmlRequired: '@',
                    htmlId: '@',
                    htmlPlaceholder: '@',
                    selectedId: '='
                },
                templateUrl: 'src/app/components/dropdowns/enums/flightSchemeTypeDropDown.html',
                controller: 'FlightSchemeTypeDropDownController'
            };
        });

    angular
        .module('adminportal')
        .controller('FlightSchemeTypeDropDownController', FlightSchemeTypeDropDownController);

    FlightSchemeTypeDropDownController.$inject = ['$scope', 'flightSchemeType'];

    function FlightSchemeTypeDropDownController($scope, flightSchemeType) {

        $scope.model = flightSchemeType;

    }
})();