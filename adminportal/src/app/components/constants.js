(function () {
    'use strict';

    angular
        .module('adminportal')
        .constant('flightSchemeType', {
            ONE_OFF: 'ONE_OFF',
            WORK_DAYS: 'WORK_DAYS',
            EVERYDAY: 'EVERYDAY'
        });
})();