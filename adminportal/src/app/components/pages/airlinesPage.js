(function () {
    'use strict';

    angular
        .module('adminportal')
        .controller('AirlinesPageController', AirlinesPageController);

    AirlinesPageController.$inject = ['$scope', 'dialogService', 'eventBus'];

    function AirlinesPageController($scope, dialogService, eventBus) {

        $scope.model = {};
        $scope.errorCode = null;

        $scope.onClickAddAirline = onClickAddAirline;

        function onClickAddAirline() {
            dialogService.openCreateAirlineForm({});
        }

    }
})();