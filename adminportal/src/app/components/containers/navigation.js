(function () {
    'use strict';

    angular
        .module('adminportal')
        .directive('navigation', function () {
            return {
                restrict: 'E',
                scope: {},
                templateUrl: 'src/app/components/containers/navigation.html',
                controller: 'NavigationController'
            };
        });

    angular
        .module('adminportal')
        .controller('NavigationController', NavigationController);

    NavigationController.$inject = ['$scope'];

    function NavigationController($scope) {

        $scope.model = {};
        $scope.errorCode = null;

    }
})();