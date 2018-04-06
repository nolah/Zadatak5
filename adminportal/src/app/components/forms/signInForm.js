(function () {
    'use strict';

    angular
        .module('adminportal')
        .directive('signInForm', function () {
            return {
                restrict: 'E',
                scope: {},
                templateUrl: 'src/app/components/forms/signInForm.html',
                controller: 'SignInFormController'
            };
        });

    angular
        .module('adminportal')
        .controller('SignInFormController', SignInFormController);

    SignInFormController.$inject = ['$scope', 'dialogService', '$state', 'authenticationApi', 'sessionService'];

    function SignInFormController($scope, dialogService, $state, authenticationApi, sessionService) {

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
                username: $scope.model.username,
                password: $scope.model.password
            };
            authenticationApi.signIn(request).then((data) => {
                $scope.submitDisabled = false;
                $scope.errorCode = null;
                sessionService.save(data);
                if (data.role == 'ADMIN') {
                    $state.go('aircraftsPage', {});
                }
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