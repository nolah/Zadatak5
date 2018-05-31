(function () {
    'use strict';

    angular
        .module('adminportal')
        .service('webApiMockService', webApiMockService);

    webApiMockService.$inject = ['$timeout'];

    function webApiMockService($timeout) {

        return {
            searchFlights: searchFlights,
            bookFlight: bookFlight
        };

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
            return $timeout(function () {
                return {
                    data: {
                        //TODO fill up mocked data values
                    }
                };
            }, 500);
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