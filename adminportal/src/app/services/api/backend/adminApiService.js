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
            updateAircraft: updateAircraft,
            airlines: airlines,
            readAirline: readAirline,
            createAirline: createAirline,
            updateAirline: updateAirline,
            flights: flights,
            readFlight: readFlight,
            createFlights: createFlights,
            updateFlight: updateFlight
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
         *     airlineId: Int
         *     airlineName: String
         *     airlineDescription: String
         *     airlineLuggageDetails: Optional[String]
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
         *   airlineId: Int
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
         *   airlineId: Int
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
                        type: model.type,
                        airlineId: model.airlineId
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
         *   airlineId: Int
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
                        type: model.type,
                        airlineId: model.airlineId
                    },
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "Bearer " + sessionService.getSessionData().accessToken
                    }
                });
            }
            return securityService.securedCall(httpRequest).then(onSuccess);
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
            function httpRequest() {
                return $http({
                    method: 'GET',
                    url: backendApiUrl + '/api/airlines',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "Bearer " + sessionService.getSessionData().accessToken
                    }
                });
            }
            return securityService.securedCall(httpRequest).then(onSuccess);
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
            function httpRequest() {
                return $http({
                    method: 'GET',
                    url: backendApiUrl + '/api/read-airline',
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
            function httpRequest() {
                return $http({
                    method: 'POST',
                    url: backendApiUrl + '/api/create-airline',
                    data: {
                        name: model.name,
                        description: model.description,
                        luggageDetails: model.luggageDetails
                    },
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "Bearer " + sessionService.getSessionData().accessToken
                    }
                });
            }
            return securityService.securedCall(httpRequest).then(onSuccess);
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
            function httpRequest() {
                return $http({
                    method: 'PUT',
                    url: backendApiUrl + '/api/update-airline',
                    data: {
                        id: model.id,
                        name: model.name,
                        description: model.description,
                        luggageDetails: model.luggageDetails
                    },
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "Bearer " + sessionService.getSessionData().accessToken
                    }
                });
            }
            return securityService.securedCall(httpRequest).then(onSuccess);
        }

        /** flights (secured)
         * request - Unit
         *
         * response - List [
         *   FlightsResponse {
         *     id: Int
         *     aircraftId: Int
         *     timestamp: DateTime
         *     numberOfEconomySeats: Int
         *     freeEconomySeats: Int
         *     priceOfEconomySeat: Decimal(20, 4)
         *     numberOfBusinessSeats: Int
         *     freeBusinessSeats: Int
         *     priceOfBusinessSeats: Decimal(20, 4)
         *     fromAirport: String
         *     toAirport: String
         *     aircraftMaker: String
         *     aircraftType: String
         *     aircraftAirlineId: Int
         *     airlineId: Int
         *     airlineName: String
         *     airlineDescription: String
         *     airlineLuggageDetails: Optional[String]
         *   }
         * ]
         *
         */
        function flights() {
            function httpRequest() {
                return $http({
                    method: 'GET',
                    url: backendApiUrl + '/api/flights',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "Bearer " + sessionService.getSessionData().accessToken
                    }
                });
            }
            return securityService.securedCall(httpRequest).then(onSuccess);
        }

        /** readFlight (secured)
         * request - Unit
         *
         * response - ReadFlightResponse {
         *   id: Int
         *   aircraftId: Int
         *   timestamp: DateTime
         *   numberOfEconomySeats: Int
         *   freeEconomySeats: Int
         *   priceOfEconomySeat: Decimal(20, 4)
         *   numberOfBusinessSeats: Int
         *   freeBusinessSeats: Int
         *   priceOfBusinessSeats: Decimal(20, 4)
         *   fromAirport: String
         *   toAirport: String
         * }
         *
         */
        function readFlight(model) {
            function httpRequest() {
                return $http({
                    method: 'GET',
                    url: backendApiUrl + '/api/read-flight',
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

        /** createFlights (secured)
         * request - CreateFlightsRequest {
         *   aircraftId: Int
         *   fromDate: DateTime
         *   toDate: Optional[DateTime]
         *   schemeType: FlightSchemeType
         *   numberOfEconomySeats: Int
         *   priceOfEconomySeat: Decimal(20, 4)
         *   numberOfBusinessSeats: Int
         *   priceOfBusinessSeats: Decimal(20, 4)
         *   fromAirport: String
         *   toAirport: String
         * }
         *
         * response - Unit
         *
         */
        function createFlights(model) {
            function httpRequest() {
                return $http({
                    method: 'POST',
                    url: backendApiUrl + '/api/create-flights',
                    data: {
                        aircraftId: model.aircraftId,
                        fromDate: model.fromDate,
                        toDate: model.toDate,
                        schemeType: model.schemeType,
                        numberOfEconomySeats: model.numberOfEconomySeats,
                        priceOfEconomySeat: model.priceOfEconomySeat,
                        numberOfBusinessSeats: model.numberOfBusinessSeats,
                        priceOfBusinessSeats: model.priceOfBusinessSeats,
                        fromAirport: model.fromAirport,
                        toAirport: model.toAirport
                    },
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "Bearer " + sessionService.getSessionData().accessToken
                    }
                });
            }
            return securityService.securedCall(httpRequest).then(onSuccess);
        }

        /** updateFlight (secured)
         * request - UpdateFlightRequest {
         *   id: Int
         *   aircraftId: Int
         *   timestamp: DateTime
         *   numberOfEconomySeats: Int
         *   priceOfEconomySeat: Decimal(20, 4)
         *   numberOfBusinessSeats: Int
         *   priceOfBusinessSeats: Decimal(20, 4)
         *   fromAirport: String
         *   toAirport: String
         * }
         *
         * response - Unit
         *
         */
        function updateFlight(model) {
            function httpRequest() {
                return $http({
                    method: 'PUT',
                    url: backendApiUrl + '/api/update-flight',
                    data: {
                        id: model.id,
                        aircraftId: model.aircraftId,
                        timestamp: model.timestamp,
                        numberOfEconomySeats: model.numberOfEconomySeats,
                        priceOfEconomySeat: model.priceOfEconomySeat,
                        numberOfBusinessSeats: model.numberOfBusinessSeats,
                        priceOfBusinessSeats: model.priceOfBusinessSeats,
                        fromAirport: model.fromAirport,
                        toAirport: model.toAirport
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