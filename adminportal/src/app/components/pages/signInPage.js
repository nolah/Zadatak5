(function () {
    'use strict';

    angular
        .module('adminportal')
        .controller('SignInPageController', SignInPageController);

    SignInPageController.$inject = ['$scope'];

    function SignInPageController($scope) {

        $scope.model = {};
        $scope.errorCode = null;

    }
})();