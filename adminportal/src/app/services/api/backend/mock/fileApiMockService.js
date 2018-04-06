(function () {
    'use strict';

    angular
        .module('adminportal')
        .service('fileApiMockService', fileApiMockService);

    fileApiMockService.$inject = ['$timeout'];

    function fileApiMockService($timeout) {

        return {
            findFile: findFile
        };

        /** findFile 
         * request - Unit
         *
         * response - Unit
         *
         */
        function findFile(model) {
            return $timeout(function () {
                return {
                    data: {
                        //TODO fill up mocked data values
                    }
                };
            }, 500);
        }
    }
})();