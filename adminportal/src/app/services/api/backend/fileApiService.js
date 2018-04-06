(function () {
    'use strict';

    angular
        .module('adminportal')
        .service('fileApiService', fileApiService);

    fileApiService.$inject = ['$http'];

    function fileApiService($http) {

        var backendApiUrl = '';

        return {
            init: init,
            findFile: findFile
        };

        function init(backendUrl) {
            backendApiUrl = backendUrl;
        }

        /** findFile 
         * request - Unit
         *
         * response - Unit
         *
         */
        function findFile(model) {
            return $http({
                method: 'GET',
                url: backendApiUrl + '/api/file/' + model.key + '/' + model.fileName + '',
                responseType: 'blob',
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(onSuccess);
        }

        function onSuccess(response) {
            return response.data;
        }
    }
})();