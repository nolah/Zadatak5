(function () {
    'use strict';

    angular
        .module('adminportal')
        .service('adminApiService', adminApiService);

    adminApiService.$inject = ['$http', 'sessionService', 'securityService'];

    function adminApiService($http, sessionService, securityService) {

        var backendApiUrl = '';

        return {
            init: init,
            aircrafts: aircrafts,
            readAircraft: readAircraft,
            createAircraft: createAircraft,
            updateAircraft: updateAircraft
        };

        function init(backendUrl) {
            backendApiUrl = backendUrl;
        }

        /** aircrafts (secured)
         * request - Unit
         *
         * response - List [
         *   AircraftsResponse {
         *     id: Int
         *     maker: String
         *     type: String
         *   }
         * ]
         *
         */
        function aircrafts() {
            function httpRequest() {
                return $http({
                    method: 'GET',
                    url: backendApiUrl + '/api/aircrafts',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "Bearer " + sessionService.getSessionData().accessToken
                    }
                });
            }
            return securityService.securedCall(httpRequest).then(onSuccess);
        }

        /** readAircraft (secured)
         * request - Unit
         *
         * response - ReadAircraftResponse {
         *   id: Int
         *   maker: String
         *   type: String
         * }
         *
         */
        function readAircraft(model) {
            function httpRequest() {
                return $http({
                    method: 'GET',
                    url: backendApiUrl + '/api/read-aircraft',
                    params: {
                        id: model.id
                    },
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "Bearer " + sessionService.getSessionData().accessToken
                    }
                });
            }
            return securityService.securedCall(httpRequest).then(onSuccess);
        }

        /** createAircraft (secured)
         * request - CreateAircraftRequest {
         *   maker: String
         *   type: String
         * }
         *
         * response - Unit
         *
         */
        function createAircraft(model) {
            function httpRequest() {
                return $http({
                    method: 'POST',
                    url: backendApiUrl + '/api/create-aircraft',
                    data: {
                        maker: model.maker,
                        type: model.type
                    },
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "Bearer " + sessionService.getSessionData().accessToken
                    }
                });
            }
            return securityService.securedCall(httpRequest).then(onSuccess);
        }

        /** updateAircraft (secured)
         * request - UpdateAircraftRequest {
         *   id: Int
         *   maker: String
         *   type: String
         * }
         *
         * response - Unit
         *
         */
        function updateAircraft(model) {
            function httpRequest() {
                return $http({
                    method: 'PUT',
                    url: backendApiUrl + '/api/update-aircraft',
                    data: {
                        id: model.id,
                        maker: model.maker,
                        type: model.type
                    },
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "Bearer " + sessionService.getSessionData().accessToken
                    }
                });
            }
            return securityService.securedCall(httpRequest).then(onSuccess);
        }

        function onSuccess(response) {
            return response.data;
        }
    }
})();