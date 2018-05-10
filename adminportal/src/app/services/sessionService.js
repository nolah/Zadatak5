(function () {
    'use strict';

    angular
        .module('adminportal')
        .service('sessionService', sessionService);

    sessionService.$inject = ['jwtHelper'];

    function sessionService(jwtHelper) {

        /* jshint ignore:start */
        let publicStates = [];
        publicStates['signInPage'] = true;
        publicStates['signUpPage'] = true;

        let stateAccessRights = [];
        stateAccessRights['basePage'] = [];
        stateAccessRights['basePage']['USER'] = false;
        stateAccessRights['basePage']['ADMIN'] = true;

        stateAccessRights['basePage.aircraftsPage'] = [];
        stateAccessRights['basePage.aircraftsPage']['USER'] = false;
        stateAccessRights['basePage.aircraftsPage']['ADMIN'] = true;

        stateAccessRights['basePage.airlinesPage'] = [];
        stateAccessRights['basePage.airlinesPage']['USER'] = false;
        stateAccessRights['basePage.airlinesPage']['ADMIN'] = true;

        stateAccessRights['basePage.flightsPage'] = [];
        stateAccessRights['basePage.flightsPage']['USER'] = false;
        stateAccessRights['basePage.flightsPage']['ADMIN'] = true;

        /* jshint ignore:end */

        return {
            save: save,
            clear: clear,
            getSessionData: getSessionData,
            isLoggedIn: isLoggedIn,
            canUserAccessState: canUserAccessState,
            isValidAccessToken: isValidAccessToken,
            isValidRefreshToken: isValidRefreshToken
        };

        function save(sessionData) {
            localStorage.setItem('accessToken', sessionData.accessToken);
            localStorage.setItem('refreshToken', sessionData.refreshToken);
            localStorage.setItem('id', sessionData.id);
            localStorage.setItem('username', sessionData.username);
            localStorage.setItem('role', sessionData.role);
        }

        function clear() {
            localStorage.removeItem('accessToken');
            localStorage.removeItem('refreshToken');
            localStorage.removeItem('id');
            localStorage.removeItem('username');
            localStorage.removeItem('role');
        }

        function getSessionData() {
            return {
                accessToken: localStorage.getItem('accessToken'),
                refreshToken: localStorage.getItem('refreshToken'),
                id: localStorage.getItem('id'),
                username: localStorage.getItem('username'),
                role: localStorage.getItem('role')
            };
        }

        function isValidAccessToken() {
            let accessToken = localStorage.getItem('accessToken');
            if (accessToken === undefined) return false;

            let validUntil = jwtHelper.getTokenExpirationDate(accessToken);
            return validUntil.getTime() > new Date().getTime() + 5000;
        }

        function isValidRefreshToken() {
            let refreshToken = localStorage.getItem('refreshToken');
            if (refreshToken === undefined) return false;

            let validUntil = jwtHelper.getTokenExpirationDate(refreshToken);
            return validUntil.getTime() > new Date().getTime();
        }

        function isLoggedIn() {
            return localStorage.getItem("accessToken") !== null;
        }

        function canUserAccessState(stateName) {
            if (publicStates[stateName]) {
                return true;
            }

            let user = getSessionData();
            if (user.role === null) return false;
            return canAccess(stateName, user.role);
        }

        function canAccess(stateName, permission) {
            return isValidRefreshToken() && stateAccessRights[stateName][permission];
        }

    }

})();