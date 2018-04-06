(function () {
    'use strict';

    angular
        .module('adminportal')
        .controller('AircraftsPageController', AircraftsPageController);

    AircraftsPageController.$inject = ['$scope', 'dialogService', 'eventBus'];

    function AircraftsPageController($scope, dialogService, eventBus) {

        $scope.model = {};
        $scope.errorCode = null;

        $scope.onClickAddAircraft = onClickAddAircraft;

        function onClickAddAircraft() {
            dialogService.openCreateAircraftForm({});
        }

    }
})();