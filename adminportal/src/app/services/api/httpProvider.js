(function () {
    'use strict';

    angular
        .module('adminportal').factory('httpInterceptor', httpInterceptor);

    httpInterceptor.$inject = ['$q', '$location', '$log'];

    function httpInterceptor($q, $location, $log) {
        return {
            responseError: function (rejection) {
                if (rejection && rejection.status) {
                    switch (rejection.status) {
                        case 401:
                        case 403:
                            $log.error('Unauthorized');
                            $location.path('/sign-in');
                            break;
                    }
                }
                return $q.reject(rejection);
            }
        };
    }

    angular
        .module('adminportal').config(['$httpProvider', function ($httpProvider) {
            $httpProvider.interceptors.push('httpInterceptor');
        }]);
})();