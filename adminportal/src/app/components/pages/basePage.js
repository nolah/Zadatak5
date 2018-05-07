(function () {
    'use strict';

    angular
        .module('adminportal')
        .controller('BasePageController', BasePageController);

    BasePageController.$inject = ['$scope'];

    function BasePageController($scope) {

        $scope.model = {};
        $scope.errorCode = null;

    }
})();