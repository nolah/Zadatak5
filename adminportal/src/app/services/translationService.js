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
                DATA_SET_EMPTY_NO_DATA: 'No data',
                ERROR_MESSAGE: 'There was an error executing operation.',
                ERROR_TITLE: 'Error',
                NAVIGATION_GOTO_AIRCRAFTS: 'Goto aircrafts',
                NAVIGATION_GOTO_AIRLINES: 'Goto airlines',
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
                YES: 'Yes'
            });

            $translateProvider.preferredLanguage('en');

            $translateProvider.useSanitizeValueStrategy('sanitizeParameters');

            $translateProvider.useLocalStorage();
        });
})();