(function () {
    'use strict';

    angular
        .module('adminportal')
        .service('authenticationApiMockService', authenticationApiMockService);

    authenticationApiMockService.$inject = ['$timeout'];

    function authenticationApiMockService($timeout) {

        return {
            refreshToken: refreshToken,
            signUp: signUp,
            signIn: signIn,
            changePassword: changePassword
        };

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
            return $timeout(function () {
                return {
                    data: {
                        //TODO fill up mocked data values
                    }
                };
            }, 500);
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
            return $timeout(function () {
                return {
                    data: {
                        //TODO fill up mocked data values
                    }
                };
            }, 500);
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
            return $timeout(function () {
                return {
                    data: {
                        //TODO fill up mocked data values
                    }
                };
            }, 500);
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