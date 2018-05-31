(function () {
    'use strict';

    angular
        .module('adminportal')
        .service('webApiService', webApiService);

    webApiService.$inject = ['$http', 'sessionService', 'securityService'];

    function webApiService($http, sessionService, securityService) {

        var backendApiUrl = '';

        return {
            init: init,
            searchFlights: searchFlights,
            bookFlight: bookFlight
        };

        function init(backendUrl) {
            backendApiUrl = backendUrl;
        }

        /** searchFlights 
         * request - Unit
         *
         * response - SearchFlightsResponse {
         *   departingFlights: List [
         *     SearchFlightDto {
         *       id: Int
         *       timestamp: DateTime
         *       numberOfEconomySeats: Int
         *       freeEconomySeats: Int
         *       priceOfEconomySeat: Decimal(20, 4)
         *       numberOfBusinessSeats: Int
         *       freeBusinessSeats: Int
         *       priceOfBusinessSeats: Decimal(20, 4)
         *       fromAirport: String
         *       toAirport: String
         *       maker: String
         *       type: String
         *       name: String
         *       hasEnoughSeats: Boolean
         *     }
         *   ]
         *   returningFlights: List [
         *     SearchFlightDto {
         *       id: Int
         *       timestamp: DateTime
         *       numberOfEconomySeats: Int
         *       freeEconomySeats: Int
         *       priceOfEconomySeat: Decimal(20, 4)
         *       numberOfBusinessSeats: Int
         *       freeBusinessSeats: Int
         *       priceOfBusinessSeats: Decimal(20, 4)
         *       fromAirport: String
         *       toAirport: String
         *       maker: String
         *       type: String
         *       name: String
         *       hasEnoughSeats: Boolean
         *     }
         *   ]
         * }
         *
         */
        function searchFlights(model) {
            return $http({
                method: 'GET',
                url: backendApiUrl + '/api/search-flights',
                params: {
                    departingDate: model.departingDate,
                    returning: model.returning,
                    fromAirport: model.fromAirport,
                    toAirport: model.toAirport,
                    travelers: model.travelers,
                    seatType: model.seatType
                },
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(onSuccess);
        }

        /** bookFlight (secured)
         * request - BookFlightRequest {
         *   departingFlight: Optional[Int]
         *   returningFlight: Optional[Int]
         *   paymentMethod: PaymentMethod
         *   passengers: List [
         *     BookFlightRequestPassengers {
         *       firstName: String
         *       lastName: String
         *       passportNumber: String
         *       smallCabinLuggage: Boolean
         *       largeCabinLuggage: Boolean
         *       checkedBag: Boolean
         *       seatType: SeatType
         *     }
         *   ]
         * }
         *
         * response - Unit
         *
         */
        function bookFlight(model) {
            function httpRequest() {
                return $http({
                    method: 'POST',
                    url: backendApiUrl + '/api/book-flight',
                    data: {
                        departingFlight: model.departingFlight,
                        returningFlight: model.returningFlight,
                        paymentMethod: model.paymentMethod,
                        passengers: model.passengers
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