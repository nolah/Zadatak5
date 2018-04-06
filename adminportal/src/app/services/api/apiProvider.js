(function () {
    'use strict';

    angular
        .module('adminportal')
        .provider('authenticationApi', authenticationApi)
        .config(authenticationApiProvider);

    function authenticationApi() {
        var isMocked = false;

        var $get = ['authenticationApiService', 'authenticationApiMockService', 'clientConfigurationValues', function (authenticationApiService, authenticationApiMockService, clientConfigurationValues) {
            if (this.isMocked) {
                return authenticationApiMockService;
            } else {
                if (clientConfigurationValues.remoteBackendUrl) {
                    authenticationApiService.init(clientConfigurationValues.remoteBackendUrl);
                }
                return authenticationApiService;
            }
        }];

        return {
            isMocked: isMocked,
            $get: $get
        };
    }

    function authenticationApiProvider(clientConfigurationValues, authenticationApiProvider) {
        authenticationApiProvider.isMocked = clientConfigurationValues.useServerMock;
    }

    angular
        .module('adminportal')
        .provider('adminApi', adminApi)
        .config(adminApiProvider);

    function adminApi() {
        var isMocked = false;

        var $get = ['adminApiService', 'adminApiMockService', 'clientConfigurationValues', function (adminApiService, adminApiMockService, clientConfigurationValues) {
            if (this.isMocked) {
                return adminApiMockService;
            } else {
                if (clientConfigurationValues.remoteBackendUrl) {
                    adminApiService.init(clientConfigurationValues.remoteBackendUrl);
                }
                return adminApiService;
            }
        }];

        return {
            isMocked: isMocked,
            $get: $get
        };
    }

    function adminApiProvider(clientConfigurationValues, adminApiProvider) {
        adminApiProvider.isMocked = clientConfigurationValues.useServerMock;
    }

    angular
        .module('adminportal')
        .provider('fileApi', fileApi)
        .config(fileApiProvider);

    function fileApi() {
        var isMocked = false;

        var $get = ['fileApiService', 'fileApiMockService', 'clientConfigurationValues', function (fileApiService, fileApiMockService, clientConfigurationValues) {
            if (this.isMocked) {
                return fileApiMockService;
            } else {
                if (clientConfigurationValues.remoteBackendUrl) {
                    fileApiService.init(clientConfigurationValues.remoteBackendUrl);
                }
                return fileApiService;
            }
        }];

        return {
            isMocked: isMocked,
            $get: $get
        };
    }

    function fileApiProvider(clientConfigurationValues, fileApiProvider) {
        fileApiProvider.isMocked = clientConfigurationValues.useServerMock;
    }

})();