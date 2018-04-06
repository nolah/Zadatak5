(function () {
    'use strict';

    angular
        .module('adminportal')
        .service('securityService', securityService);

    securityService.$inject = ['sessionService', '$location', '$q', '$http', 'clientConfigurationValues'];

    function securityService(sessionService, $location, $q, $http, clientConfigurationValues) {

        return {
            securedCall: securedCall
        };

        function securedCall(httpRequest) {
            if (!sessionService.isValidAccessToken()) {
                return refreshToken({
                    refreshToken: sessionService.getSessionData().refreshToken
                }).then(
                    function (data) {
                        sessionService.save(data);
                        return httpRequest();
                    },
                    function (response) {
                        $location.path('/signIn');
                        return $q.reject(response);
                    }
                );
            }
            return httpRequest();
        }

        /** refreshToken 
         * request - RefreshTokenRequest {
         *   refreshToken: String
         * }
         *
         * response - SignInResponse {
         *   accessToken: String
         *   refreshToken: String
         *   id: Int
         *   username: String
         *   role: Role
         * }
         *
         */
        function refreshToken(model) {
            return $http({
                method: 'POST',
                url: clientConfigurationValues.remoteBackendUrl + '/api/refresh-token',
                data: {
                    refreshToken: model.refreshToken
                },
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