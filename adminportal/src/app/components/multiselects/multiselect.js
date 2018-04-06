(function () {
    'use strict';

    /*
     * Directive for multi-select fields - if there are predefined values (model) md-chips with md-autocomplete is rendered,
     * if there are no predefined values md-chips is rendered.
     *
     *
     * Parameters:
     *   * model - predefined values
     *   * selectedIds
     *   * valueProperty - property of each item that will be used for ng-value
     *                     and will be set in selectedIds (eg. 'id', 'value1'...)
     *   * displayProperty - property of each item that will be used for display (eg. 'name', 'label'...)
     *   * htmlId - used for id attribute
     *   * htmlName - used for name attribute
     *   * required - used for required attribute
     *   * disabled - used for disabled attribute
     *   * onSelectCallback - callback function that will be called after item is selected (added)
     *   * onErrorCallback - callback function that will be called after item is removed
     *   * simpleType - used for generating regex pattern
     *   * form - used for setting validity of the field
     */

    angular
        .module('adminportal')
        .directive('multiselect', function () {
            return {
                restrict: 'E',
                replace: false,
                templateUrl: 'src/app/components/multiselects/multiselect.html',
                scope: {
                    model: '=',
                    selectedIds: '=',
                    valueProperty: '@?',
                    displayProperty: '@?',
                    htmlId: '=?',
                    htmlName: '<?',
                    required: '<?',
                    onSelectCallback: '=?',
                    onRemoveCallback: '=?',
                    simpleType: '<?',
                    form: '=?'
                },
                controller: 'MultiselectController'
            };
        });

    angular
        .module('adminportal')
        .controller('MultiselectController', MultiselectController);

    MultiselectController.$inject = ['$scope', '$timeout', '$element'];

    function MultiselectController($scope, $timeout, $element) {
        $scope.selectedIds = $scope.selectedIds || [];
        $scope.displayPatterError = false;
        $scope.valueProperty = $scope.valueProperty || 'value1';
        $scope.displayProperty = $scope.displayProperty || 'label';

        let chipId = 0,
            firstLoad = true;

        if ($scope.simpleType) {
            $scope.inputPattern = simpleTypeToInputType($scope.simpleType) === 'number' ? ($scope.simpleType === 'INT' ? /^[0-9]+$/ : /^[0-9.,]+$/) : null;
        }

        $scope.onRemoveChip = onRemoveChip;
        $scope.onAddChip = onAddChip;
        $scope.onTransformChip = onTransformChip;

        $scope.$watch('model', (newVal, oldVal) => {
            if (firstLoad || newVal !== oldVal) {
                firstLoad = false;
                onInit();
            }
        });

        function onInit() {
            $scope.items = angular.copy($scope.model);
            $scope.hasPredefinedValues = $scope.model && $scope.model.length;

            if ($scope.selectedIds.length && $scope.model && $scope.model.length) {

                $scope.selectedIds.forEach(modelItem => {

                    $scope.items = $scope.items.filter(i => {
                        return modelItem[$scope.valueProperty] != i[$scope.valueProperty];
                    });
                })
            }

            $timeout(() => {
                setValidity();
            }, 0);
        }

        function onTransformChip(chip) {

            if ($scope.hasPredefinedValues) {
                $scope.items = $scope.items.filter(item =>
                    item[$scope.valueProperty] !== chip[$scope.valueProperty]
                );

                $timeout(() => {
                    closeMenu();
                }, 500);
            }

            if ((typeof chip) === 'undefined') {
                return null
            }

            if (angular.isObject(chip)) {
                return chip;
            }

            if ($scope.inputPattern && !$scope.inputPattern.test(chip)) {
                $scope.displayPatterError = true;
                return null;
            }

            let createdChip = {};
            createdChip[$scope.valueProperty] = chip;
            createdChip[$scope.displayProperty] = chip;
            createdChip['chipId'] = chipId++;

            return createdChip;
        }

        function onAddChip(chip) {
            setValidity();

            if ($scope.onSelectCallback) {
                let payload = {};
                payload[$scope.valueProperty] = chip[$scope.valueProperty];
                payload[$scope.displayProperty] = chip[$scope.displayProperty];
                payload.elementId = $scope.htmlId;
                $scope.onSelectCallback(payload);
            }
        }

        function onRemoveChip(chip) {
            if ($scope.hasPredefinedValues) {
                $scope.items.push(chip);
                $scope.items.sort(
                    (a, b) => {
                        let sortA = a[$scope.displayProperty].toLowerCase();
                        let sortB = b[$scope.displayProperty].toLowerCase();

                        if (sortA < sortB) {
                            return -1;
                        }
                        if (sortA > sortB) {
                            return 1;
                        }
                        return 0;
                    }
                );

                $timeout(() => {
                    closeMenu();
                }, 10);
            } else {
                $scope.selectedIds = $scope.selectedIds.filter(item => item[$scope.valueProperty] !== chip);
            }

            setValidity();

            if ($scope.onRemoveCallback) {
                let payload = {};
                payload[$scope.valueProperty] = chip[$scope.valueProperty];
                payload[$scope.displayProperty] = chip[$scope.displayProperty];
                payload.elementId = $scope.htmlId;
                $scope.onRemoveCallback(payload);
            }
        }

        function setValidity() {
            if (!$scope.form) return;

            if ($scope.required && !$scope.selectedIds.length) {
                $scope.form[$scope.htmlName].$setValidity('required', false);
            } else {
                $scope.form[$scope.htmlName].$setValidity('required', true);
            }
        }

        function simpleTypeToInputType(simpleType) {

            if (simpleType.includes('TEXT') || simpleType === 'LINK') {
                return 'text';
            }
            return 'number'
        }

        function closeMenu() {
            let autoComplete = angular.element($element[0].querySelector('md-autocomplete')),
                input = angular.element($element[0].querySelector('input'));

            if (autoComplete) {
                input.blur();
            }
        }
    }
})();