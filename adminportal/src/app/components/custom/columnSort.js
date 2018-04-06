(function () {
    'use strict';

    angular
        .module('adminportal')
        .directive('columnSort', function () {
            return {
                restrict: 'E',
                scope: {
                    column: '@',
                    currentColumn: '=?',
                    defaultDirection: '=?',
                    label: '@'
                },
                templateUrl: 'src/app/components/custom/columnSort.html',
                controller: 'ColumnSortController'
            };
        });

    angular
        .module('adminportal')
        .controller('ColumnSortController', ColumnSortController);

    ColumnSortController.$inject = ['$scope', 'eventBus'];

    function ColumnSortController($scope, eventBus) {
        $scope.sort = sort;
        $scope.direction = $scope.defaultDirection ? $scope.defaultDirection : 'ASC';

        function sort() {
            if ($scope.column === $scope.currentColumn[0]) {
                $scope.direction = $scope.direction === "ASC" ? "DESC" : "ASC";
                eventBus.emitEvent('ColumnOrdered', {
                    fields: [$scope.column],
                    directions: [$scope.direction]
                });
            } else {
                $scope.direction = "ASC";
                eventBus.emitEvent('ColumnOrdered', {
                    fields: [$scope.column],
                    directions: [$scope.direction]
                });
            }
        }

    }
})();