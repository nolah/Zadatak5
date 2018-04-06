(function () {
    'use strict';

    angular
        .module('adminportal')
        .service('adminApiMockService', adminApiMockService);

    adminApiMockService.$inject = ['$timeout'];

    function adminApiMockService($timeout) {

        return {
            aircrafts: aircrafts,
            readAircraft: readAircraft,
            createAircraft: createAircraft,
            updateAircraft: updateAircraft
        };

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
            return $timeout(function () {
                return {
                    data: [
                        //TODO fill up mocked data values
                    ]
                };
            }, 500);
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
            return $timeout(function () {
                return {
                    data: {
                        //TODO fill up mocked data values
                    }
                };
            }, 500);
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
            return $timeout(function () {
                return {
                    data: {
                        //TODO fill up mocked data values
                    }
                };
            }, 500);
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