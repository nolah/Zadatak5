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
            updateAircraft: updateAircraft,
            airlines: airlines,
            readAirline: readAirline,
            createAirline: createAirline,
            updateAirline: updateAirline
        };

        /** aircrafts (secured)
         * request - Unit
         *
         * response - List [
         *   AircraftsResponse {
         *     id: Int
         *     maker: String
         *     type: String
         *     airlineId: Int
         *     airlineName: String
         *     airlineDescription: String
         *     airlineLuggageDetails: Optional[String]
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
         *   airlineId: Int
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
         *   airlineId: Int
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
         *   airlineId: Int
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

        /** airlines (secured)
         * request - Unit
         *
         * response - List [
         *   AirlinesResponse {
         *     id: Int
         *     name: String
         *     description: String
         *     luggageDetails: Optional[String]
         *   }
         * ]
         *
         */
        function airlines() {
            return $timeout(function () {
                return {
                    data: [
                        //TODO fill up mocked data values
                    ]
                };
            }, 500);
        }

        /** readAirline (secured)
         * request - Unit
         *
         * response - ReadAirlineResponse {
         *   id: Int
         *   name: String
         *   description: String
         *   luggageDetails: Optional[String]
         * }
         *
         */
        function readAirline(model) {
            return $timeout(function () {
                return {
                    data: {
                        //TODO fill up mocked data values
                    }
                };
            }, 500);
        }

        /** createAirline (secured)
         * request - CreateAirlineRequest {
         *   name: String
         *   description: String
         *   luggageDetails: Optional[String]
         * }
         *
         * response - Unit
         *
         */
        function createAirline(model) {
            return $timeout(function () {
                return {
                    data: {
                        //TODO fill up mocked data values
                    }
                };
            }, 500);
        }

        /** updateAirline (secured)
         * request - UpdateAirlineRequest {
         *   id: Int
         *   name: String
         *   description: String
         *   luggageDetails: Optional[String]
         * }
         *
         * response - Unit
         *
         */
        function updateAirline(model) {
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