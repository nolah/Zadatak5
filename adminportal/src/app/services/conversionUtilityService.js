(function () {
    'use strict';

    angular
        .module('adminportal')
        .factory('conversionUtilityService', conversionUtilityService);

    function conversionUtilityService() {

        return {
            isNull: isNull,
            isNullOrEmpty: isNullOrEmpty,
            convertEmpty: convertEmpty,
            convertValues: convertValues
        };

        function isNull(value) {
            return value === null || value === undefined;
        }

        function isNullOrEmpty(value) {
            return value === '' || isNull(value);
        }

        function convertEmpty(value) {
            if (isNullOrEmpty(value))
                return undefined;

            return value;
        }

        function convertValues(values, converter) {
            if (values) {
                return values.map(value => {
                    return converter(value);
                });
            } else {
                return undefined;
            }
        }

    }

})();