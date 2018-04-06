(function () {
    'use strict';
    /*
     * Directive for drop downs - if there are less than 5 items display md-select,
     * if more than 5 items display md-autocomplete.
     *
     * Parameters:
     *   * model - predefined items in drop down
     *   * selectedId
     *   * valueProperty - property of each item that will be used for ng-value
     *                     and will be set in selectedId (eg. 'id', 'value1'...)
     *   * displayProperty - property of each item that will be used for display (eg. 'name', 'label'...)
     *   * htmlId - used for id attribute
     *   * htmlName - used for name attribute
     *   * htmlPlaceholder - used for placeholder attribute
     *   * required - used for required attribute
     *   * disabled - used for disabled attribute
     *   * onSelectCallback - callback function that will be called after item is selected
     *   * orderBy - property of each item that will be used for ordering items
     *   * namePrefix - name prefix for creating translate for display name, label
     */
    angular
        .module('adminportal')
        .directive('dropDown', function () {
            return {
                restrict: 'E',
                scope: {
                    model: '=',
                    selectedId: '=',
                    valueProperty: '@?',
                    displayProperty: '@?',
                    htmlId: '@?',
                    htmlName: '@?',
                    htmlPlaceholder: '@?',
                    required: '<?',
                    disabled: '<?',
                    onSelectCallback: '=?',
                    orderBy: '@?',
                    namePrefix: '@?',
                    hideLabel: '=?'
                },
                templateUrl: 'src/app/components/dropdowns/dropDown.html',
                controller: 'DropDownController'
            }
        });
    angular
        .module('adminportal')
        .controller('DropDownController', DropDownController);
    DropDownController.$inject = ['$scope', '$element', '$translate', '$timeout'];

    function DropDownController($scope, $element, $translate, $timeout) {
        $element.find('input').on('keydown', function (ev) {
            ev.stopPropagation();
        });
        $scope.model = $scope.model || [];
        // $scope.htmlPlaceholder = $scope.htmlPlaceholder || $translate.instant('PLEASE_SELECT');
        $scope.valueProperty = $scope.valueProperty || 'id';
        $scope.displayProperty = $scope.displayProperty || 'name';
        $scope.selected = {
            text: '',
            item: null,
            orderBy: $scope.orderBy ? $scope.orderBy : 'name'
        };
        $scope.onSelect = onSelect;
        onInit();

        function onInit() {
            // Enum drop-down
            if (!Array.isArray($scope.model)) {
                $scope.items = $scope.items || [];
                angular.forEach($scope.model, item => {
                    $scope.items.push({
                        id: item,
                        name: $translate.instant($scope.namePrefix ? $scope.namePrefix + item : item)
                    });
                    $scope.model = $scope.items;
                });
            }
            if ($scope.model.length) {
                $scope.items = $scope.model;
                // Initially selected
                if ($scope.selectedId) {
                    $scope.selected.item = $scope.items.filter(item => {
                        return item[$scope.valueProperty] === $scope.selectedId;
                    })[0];
                }
            }
            $scope.showSelect = !$scope.model.length || $scope.model.length <= 5;
        }
        $scope.$watch('[selectedId, model]', (newVal, oldVal) => {
            if (newVal !== oldVal) {
                $scope.showSelect = false;
                $timeout(() => {
                    onInit();
                }, 0);
            }
        });

        function onSelect(item) {
            let selected;
            if (!item && !$scope.selected.item) {
                $scope.selectedId = undefined;
            } else {
                selected = item || $scope.selected.item;
                $scope.selectedId = selected[$scope.valueProperty];
            }
            if ($scope.onSelectCallback) {
                $scope.onSelectCallback(selected);
            }
        }
    }
})();