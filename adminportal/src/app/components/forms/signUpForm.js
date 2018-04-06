(function () {
    'use strict';

    angular
        .module('adminportal')
        .directive('signUpForm', function () {
            return {
                restrict: 'E',
                scope: {},
                templateUrl: 'src/app/components/forms/signUpForm.html',
                controller: 'SignUpFormController'
            };
        });

    angular
        .module('adminportal')
        .controller('SignUpFormController', SignUpFormController);

    SignUpFormController.$inject = ['$scope', 'dialogService', '$state', 'authenticationApi', 'conversionUtilityService'];

    function SignUpFormController($scope, dialogService, $state, authenticationApi, conversionUtilityService) {

        $scope.model = {};
        $scope.errorCode = null;
        $scope.submitDisabled = false;

        $scope.onClickSubmit = onClickSubmit;

        function onClickSubmit(form) {
            if (form.$invalid) {
                form.$setSubmitted();
                return false;
            }
            $scope.submitDisabled = true;
            let request = {
                setPasswordCode: conversionUtilityService.convertEmpty($scope.model.setPasswordCode),
                setPasswordTimestamp: $scope.model.setPasswordTimestamp,
                username: $scope.model.username,
                password: $scope.model.password
            };
            authenticationApi.signUp(request).then((data) => {
                $scope.submitDisabled = false;
                $scope.errorCode = null;
                $state.go('signInPage', {});
            }).catch(onError);

        }

        function onError(response) {
            $scope.submitDisabled = false;
            if (response.status && response.statusText) {
                $scope.errorCode = response.statusText;
            } else {
                $scope.errorCode = 'SOMETHING_WENT_WRONG';
            }

            let dialog = dialogService.openErrorDialog({
                title: 'OOPS',
                message: $scope.errorCode
            });
        }

    }
})();