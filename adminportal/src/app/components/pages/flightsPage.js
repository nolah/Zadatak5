(function () {
    'use strict';

    angular
        .module('adminportal')
        .controller('FlightsPageController', FlightsPageController);

    FlightsPageController.$inject = ['$scope', 'dialogService', 'eventBus'];

    function FlightsPageController($scope, dialogService, eventBus) {

        $scope.model = {};
        $scope.errorCode = null;

        $scope.onClickCreateFlightScheme = onClickCreateFlightScheme;

        function onClickCreateFlightScheme() {
            dialogService.openCreateFlightsForm({});
        }

    }
})();