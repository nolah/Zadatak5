(function () {
    'use strict';

    angular
        .module('adminportal')
        .factory('translationService', translationService);

    translationService.$inject = ['$translate'];

    function translationService($translate) {
        return {
            listLanguages: listLanguages,
            currentLanguage: currentLanguage,
            changeLanguage: changeLanguage
        };

        function listLanguages() {
            return ['en'];
        }

        function currentLanguage() {
            return $translate.use();
        }

        function changeLanguage(key) {
            $translate.use(key);
        }
    }

    angular
        .module('adminportal')
        .config(function ($translateProvider) {

            $translateProvider.translations('en', {
                AIRCRAFTS_AIRLINE_NAME: 'Airline name',
                AIRCRAFTS_EDIT_AIRCRAFT: 'Edit aircraft',
                AIRCRAFTS_MAKER: 'Maker',
                AIRCRAFTS_PAGE_ADD_AIRCRAFT: 'Add aircraft',
                AIRCRAFTS_TYPE: 'Type',
                AIRLINES_DESCRIPTION: 'Description',
                AIRLINES_EDIT_AIRLINES: 'Edit airlines',
                AIRLINES_LUGGAGE_DETAILS: 'Luggage details',
                AIRLINES_NAME: 'Name',
                AIRLINES_PAGE_ADD_AIRLINE: 'Add airline',
                CANCEL: 'Cancel',
                CHANGE_PASSWORD_FORM_NEW_PASSWORD: 'New password',
                CHANGE_PASSWORD_FORM_NEW_PASSWORD_IS_REQUIRED: 'New password is required',
                CHANGE_PASSWORD_FORM_NEW_PASSWORD_MAX: 'New password max {{max}} characters',
                CHANGE_PASSWORD_FORM_NEW_PASSWORD_MIN: 'New password min {{min}} characters',
                CHANGE_PASSWORD_FORM_NEW_PASSWORD_PATTERN: 'New password pattern',
                CHANGE_PASSWORD_FORM_OLD_PASSWORD: 'Old password',
                CHANGE_PASSWORD_FORM_OLD_PASSWORD_IS_REQUIRED: 'Old password is required',
                CHANGE_PASSWORD_FORM_OLD_PASSWORD_MAX: 'Old password max {{max}} characters',
                CHANGE_PASSWORD_FORM_OLD_PASSWORD_MIN: 'Old password min {{min}} characters',
                CHANGE_PASSWORD_FORM_OLD_PASSWORD_PATTERN: 'Old password pattern',
                CHANGE_PASSWORD_FORM_SUBMIT: 'Submit',
                CREATE_AIRCRAFT_FORM: 'Create aircraft form',
                CREATE_AIRCRAFT_FORM_AIRLINE_DROPDOWN: 'Airline dropdown',
                CREATE_AIRCRAFT_FORM_AIRLINE_DROPDOWN_IS_REQUIRED: 'Airline dropdown is required',
                CREATE_AIRCRAFT_FORM_MAKER: 'Maker',
                CREATE_AIRCRAFT_FORM_MAKER_IS_REQUIRED: 'Maker is required',
                CREATE_AIRCRAFT_FORM_MAKER_MAX: 'Maker max {{max}} characters',
                CREATE_AIRCRAFT_FORM_MAKER_MIN: 'Maker min {{min}} characters',
                CREATE_AIRCRAFT_FORM_SUBMIT: 'Submit',
                CREATE_AIRCRAFT_FORM_TYPE: 'Type',
                CREATE_AIRCRAFT_FORM_TYPE_IS_REQUIRED: 'Type is required',
                CREATE_AIRCRAFT_FORM_TYPE_MAX: 'Type max {{max}} characters',
                CREATE_AIRCRAFT_FORM_TYPE_MIN: 'Type min {{min}} characters',
                CREATE_AIRLINE_FORM: 'Create airline form',
                CREATE_AIRLINE_FORM_DESCRIPTION: 'Description',
                CREATE_AIRLINE_FORM_DESCRIPTION_IS_REQUIRED: 'Description is required',
                CREATE_AIRLINE_FORM_DESCRIPTION_MAX: 'Description max {{max}} characters',
                CREATE_AIRLINE_FORM_LUGGAGE_DETAILS: 'Luggage details',
                CREATE_AIRLINE_FORM_LUGGAGE_DETAILS_MAX: 'Luggage details max {{max}} characters',
                CREATE_AIRLINE_FORM_NAME: 'Name',
                CREATE_AIRLINE_FORM_NAME_IS_REQUIRED: 'Name is required',
                CREATE_AIRLINE_FORM_NAME_MAX: 'Name max {{max}} characters',
                CREATE_AIRLINE_FORM_NAME_MIN: 'Name min {{min}} characters',
                CREATE_AIRLINE_FORM_SUBMIT: 'Submit',
                CREATE_FLIGHTS_FORM: 'Create flights form',
                CREATE_FLIGHTS_FORM_AIRCRAFT_DROPDOWN: 'Aircraft dropdown',
                CREATE_FLIGHTS_FORM_AIRCRAFT_DROPDOWN_IS_REQUIRED: 'Aircraft dropdown is required',
                CREATE_FLIGHTS_FORM_FROM_AIRPORT: 'From airport',
                CREATE_FLIGHTS_FORM_FROM_AIRPORT_IS_REQUIRED: 'From airport is required',
                CREATE_FLIGHTS_FORM_FROM_AIRPORT_MAX: 'From airport max {{max}} characters',
                CREATE_FLIGHTS_FORM_FROM_AIRPORT_MIN: 'From airport min {{min}} characters',
                CREATE_FLIGHTS_FORM_FROM_DATE: 'From date',
                CREATE_FLIGHTS_FORM_FROM_DATE_IS_REQUIRED: 'From date is required',
                CREATE_FLIGHTS_FORM_NUMBER_OF_BUSINESS_SEATS: 'Number of business seats',
                CREATE_FLIGHTS_FORM_NUMBER_OF_BUSINESS_SEATS_IS_REQUIRED: 'Number of business seats is required',
                CREATE_FLIGHTS_FORM_NUMBER_OF_ECONOMY_SEATS: 'Number of economy seats',
                CREATE_FLIGHTS_FORM_NUMBER_OF_ECONOMY_SEATS_IS_REQUIRED: 'Number of economy seats is required',
                CREATE_FLIGHTS_FORM_PRICE_OF_BUSINESS_SEATS: 'Price of business seats',
                CREATE_FLIGHTS_FORM_PRICE_OF_BUSINESS_SEATS_IS_REQUIRED: 'Price of business seats is required',
                CREATE_FLIGHTS_FORM_PRICE_OF_ECONOMY_SEAT: 'Price of economy seat',
                CREATE_FLIGHTS_FORM_PRICE_OF_ECONOMY_SEAT_IS_REQUIRED: 'Price of economy seat is required',
                CREATE_FLIGHTS_FORM_SCHEME_TYPE: 'Scheme type',
                CREATE_FLIGHTS_FORM_SCHEME_TYPE_IS_REQUIRED: 'Scheme type is required',
                CREATE_FLIGHTS_FORM_SUBMIT: 'Submit',
                CREATE_FLIGHTS_FORM_TO_AIRPORT: 'To airport',
                CREATE_FLIGHTS_FORM_TO_AIRPORT_IS_REQUIRED: 'To airport is required',
                CREATE_FLIGHTS_FORM_TO_AIRPORT_MAX: 'To airport max {{max}} characters',
                CREATE_FLIGHTS_FORM_TO_AIRPORT_MIN: 'To airport min {{min}} characters',
                CREATE_FLIGHTS_FORM_TO_DATE: 'To date',
                DATA_SET_EMPTY_NO_DATA: 'No data',
                ERROR_MESSAGE: 'There was an error executing operation.',
                ERROR_TITLE: 'Error',
                FLIGHTS_AIRCRAFT_MAKER: 'Maker',
                FLIGHTS_AIRCRAFT_TYPE: 'Type',
                FLIGHTS_AIRLINE_NAME: 'Name',
                FLIGHTS_FROM_AIRPORT: 'From airport',
                FLIGHTS_ID: 'Id',
                FLIGHTS_NUMBER_OF_BUSINESS_SEATS: '# business seats',
                FLIGHTS_NUMBER_OF_ECONOMY_SEATS: '# economy seats',
                FLIGHTS_PAGE_CREATE_FLIGHT_SCHEME: 'Create flight scheme',
                FLIGHTS_PRICE_OF_BUSINESS_SEATS: 'business seats $$',
                FLIGHTS_PRICE_OF_ECONOMY_SEAT: 'economy seat $$',
                FLIGHTS_TIMESTAMP: 'Timestamp',
                FLIGHTS_TO_AIRPORT: 'To airport',
                FLIGHTS_UPDATE_FLIGHT: 'Update flight',
                NAVIGATION_GOTO_AIRCRAFTS: 'Goto aircrafts',
                NAVIGATION_GOTO_AIRLINES: 'Goto airlines',
                NAVIGATION_GOTO_FLIGHTS: 'Goto flights',
                NO: 'No',
                OK: 'Ok',
                OOPS: 'Oops!',
                SIGN_IN_FORM_PASSWORD: 'Password',
                SIGN_IN_FORM_PASSWORD_IS_REQUIRED: 'Password is required',
                SIGN_IN_FORM_PASSWORD_MAX: 'Password max {{max}} characters',
                SIGN_IN_FORM_PASSWORD_MIN: 'Password min {{min}} characters',
                SIGN_IN_FORM_PASSWORD_PATTERN: 'Password pattern',
                SIGN_IN_FORM_SUBMIT: 'Submit',
                SIGN_IN_FORM_USERNAME: 'Username',
                SIGN_IN_FORM_USERNAME_IS_REQUIRED: 'Username is required',
                SIGN_IN_FORM_USERNAME_MAX: 'Username max {{max}} characters',
                SIGN_IN_FORM_USERNAME_MIN: 'Username min {{min}} characters',
                SIGN_IN_PAGE_SIGN_UP: 'Sign up',
                SIGN_UP_FORM_PASSWORD: 'Password',
                SIGN_UP_FORM_PASSWORD_IS_REQUIRED: 'Password is required',
                SIGN_UP_FORM_PASSWORD_MAX: 'Password max {{max}} characters',
                SIGN_UP_FORM_PASSWORD_MIN: 'Password min {{min}} characters',
                SIGN_UP_FORM_PASSWORD_PATTERN: 'Password pattern',
                SIGN_UP_FORM_SET_PASSWORD_CODE: 'Set password code',
                SIGN_UP_FORM_SET_PASSWORD_CODE_MAX: 'Set password code max {{max}} characters',
                SIGN_UP_FORM_SET_PASSWORD_TIMESTAMP: 'Set password timestamp',
                SIGN_UP_FORM_SET_PASSWORD_TIMESTAMP_IS_REQUIRED: 'Set password timestamp is required',
                SIGN_UP_FORM_SUBMIT: 'Submit',
                SIGN_UP_FORM_USERNAME: 'Username',
                SIGN_UP_FORM_USERNAME_IS_REQUIRED: 'Username is required',
                SIGN_UP_FORM_USERNAME_MAX: 'Username max {{max}} characters',
                SIGN_UP_FORM_USERNAME_MIN: 'Username min {{min}} characters',
                SIGN_UP_PAGE_SIGN_IN: 'Sign in',
                SOMETHING_WENT_WRONG: 'Something went wrong, please contact support.',
                UPDATE_AIRCRAFT_FORM: 'Update aircraft form',
                UPDATE_AIRCRAFT_FORM_AIRLINE_DROPDOWN: 'Airline dropdown',
                UPDATE_AIRCRAFT_FORM_AIRLINE_DROPDOWN_IS_REQUIRED: 'Airline dropdown is required',
                UPDATE_AIRCRAFT_FORM_MAKER: 'Maker',
                UPDATE_AIRCRAFT_FORM_MAKER_IS_REQUIRED: 'Maker is required',
                UPDATE_AIRCRAFT_FORM_MAKER_MAX: 'Maker max {{max}} characters',
                UPDATE_AIRCRAFT_FORM_MAKER_MIN: 'Maker min {{min}} characters',
                UPDATE_AIRCRAFT_FORM_SUBMIT: 'Submit',
                UPDATE_AIRCRAFT_FORM_TYPE: 'Type',
                UPDATE_AIRCRAFT_FORM_TYPE_IS_REQUIRED: 'Type is required',
                UPDATE_AIRCRAFT_FORM_TYPE_MAX: 'Type max {{max}} characters',
                UPDATE_AIRCRAFT_FORM_TYPE_MIN: 'Type min {{min}} characters',
                UPDATE_AIRLINE_FORM: 'Update airline form',
                UPDATE_AIRLINE_FORM_DESCRIPTION: 'Description',
                UPDATE_AIRLINE_FORM_DESCRIPTION_IS_REQUIRED: 'Description is required',
                UPDATE_AIRLINE_FORM_DESCRIPTION_MAX: 'Description max {{max}} characters',
                UPDATE_AIRLINE_FORM_LUGGAGE_DETAILS: 'Luggage details',
                UPDATE_AIRLINE_FORM_LUGGAGE_DETAILS_MAX: 'Luggage details max {{max}} characters',
                UPDATE_AIRLINE_FORM_NAME: 'Name',
                UPDATE_AIRLINE_FORM_NAME_IS_REQUIRED: 'Name is required',
                UPDATE_AIRLINE_FORM_NAME_MAX: 'Name max {{max}} characters',
                UPDATE_AIRLINE_FORM_NAME_MIN: 'Name min {{min}} characters',
                UPDATE_AIRLINE_FORM_SUBMIT: 'Submit',
                UPDATE_FLIGHT_FORM: 'Update flight form',
                UPDATE_FLIGHT_FORM_AIRCRAFT_DROPDOWN: 'Aircraft dropdown',
                UPDATE_FLIGHT_FORM_AIRCRAFT_DROPDOWN_IS_REQUIRED: 'Aircraft dropdown is required',
                UPDATE_FLIGHT_FORM_FREE_BUSINESS_SEATS: 'Free business seats',
                UPDATE_FLIGHT_FORM_FREE_BUSINESS_SEATS_IS_REQUIRED: 'Free business seats is required',
                UPDATE_FLIGHT_FORM_FREE_ECONOMY_SEATS: 'Free economy seats',
                UPDATE_FLIGHT_FORM_FREE_ECONOMY_SEATS_IS_REQUIRED: 'Free economy seats is required',
                UPDATE_FLIGHT_FORM_FROM_AIRPORT: 'From airport',
                UPDATE_FLIGHT_FORM_FROM_AIRPORT_IS_REQUIRED: 'From airport is required',
                UPDATE_FLIGHT_FORM_FROM_AIRPORT_MAX: 'From airport max {{max}} characters',
                UPDATE_FLIGHT_FORM_FROM_AIRPORT_MIN: 'From airport min {{min}} characters',
                UPDATE_FLIGHT_FORM_NUMBER_OF_BUSINESS_SEATS: 'Number of business seats',
                UPDATE_FLIGHT_FORM_NUMBER_OF_BUSINESS_SEATS_IS_REQUIRED: 'Number of business seats is required',
                UPDATE_FLIGHT_FORM_NUMBER_OF_ECONOMY_SEATS: 'Number of economy seats',
                UPDATE_FLIGHT_FORM_NUMBER_OF_ECONOMY_SEATS_IS_REQUIRED: 'Number of economy seats is required',
                UPDATE_FLIGHT_FORM_PRICE_OF_BUSINESS_SEATS: 'Price of business seats',
                UPDATE_FLIGHT_FORM_PRICE_OF_BUSINESS_SEATS_IS_REQUIRED: 'Price of business seats is required',
                UPDATE_FLIGHT_FORM_PRICE_OF_ECONOMY_SEAT: 'Price of economy seat',
                UPDATE_FLIGHT_FORM_PRICE_OF_ECONOMY_SEAT_IS_REQUIRED: 'Price of economy seat is required',
                UPDATE_FLIGHT_FORM_SUBMIT: 'Submit',
                UPDATE_FLIGHT_FORM_TIMESTAMP: 'Timestamp',
                UPDATE_FLIGHT_FORM_TIMESTAMP_IS_REQUIRED: 'Timestamp is required',
                UPDATE_FLIGHT_FORM_TO_AIRPORT: 'To airport',
                UPDATE_FLIGHT_FORM_TO_AIRPORT_IS_REQUIRED: 'To airport is required',
                UPDATE_FLIGHT_FORM_TO_AIRPORT_MAX: 'To airport max {{max}} characters',
                UPDATE_FLIGHT_FORM_TO_AIRPORT_MIN: 'To airport min {{min}} characters',
                YES: 'Yes'
            });

            $translateProvider.preferredLanguage('en');

            $translateProvider.useSanitizeValueStrategy('sanitizeParameters');

            $translateProvider.useLocalStorage();
        });
})();