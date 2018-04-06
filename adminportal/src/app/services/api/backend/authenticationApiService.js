(function () {
    'use strict';

    angular
        .module('adminportal')
        .service('authenticationApiService', authenticationApiService);

    authenticationApiService.$inject = ['$http', 'sessionService', 'securityService'];

    function authenticationApiService($http, sessionService, securityService) {

        var backendApiUrl = '';

        return {
            init: init,
            signUp: signUp,
            signIn: signIn,
            changePassword: changePassword
        };

        function init(backendUrl) {
            backendApiUrl = backendUrl;
        }

        /** signUp 
         * request - SignUpRequest {
         *   setPasswordCode: Optional[String]
         *   setPasswordTimestamp: DateTime
         *   username: String
         *   password: String
         * }
         *
         * response - Unit
         *
         */
        function signUp(model) {
            return $http({
                method: 'POST',
                url: backendApiUrl + '/api/sign-up',
                data: {
                    setPasswordCode: model.setPasswordCode,
                    setPasswordTimestamp: model.setPasswordTimestamp,
                    username: model.username,
                    password: model.password
                },
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(onSuccess);
        }

        /** signIn 
         * request - SignInRequest {
         *   username: String
         *   password: String
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
        function signIn(model) {
            return $http({
                method: 'POST',
                url: backendApiUrl + '/api/sign-in',
                data: {
                    username: model.username,
                    password: model.password
                },
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(onSuccess);
        }

        /** changePassword (secured)
         * request - ChangePasswordRequest {
         *   oldPassword: String
         *   newPassword: String
         * }
         *
         * response - Unit
         *
         */
        function changePassword(model) {
            function httpRequest() {
                return $http({
                    method: 'POST',
                    url: backendApiUrl + '/api/change-password',
                    data: {
                        oldPassword: model.oldPassword,
                        newPassword: model.newPassword
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