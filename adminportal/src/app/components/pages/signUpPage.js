(function () {
    'use strict';

    angular
        .module('adminportal')
        .controller('SignUpPageController', SignUpPageController);

    SignUpPageController.$inject = ['$scope'];

    function SignUpPageController($scope) {

        $scope.model = {};
        $scope.errorCode = null;

    }
})();