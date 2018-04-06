(function () {
    'use strict';

    angular
        .module('adminportal')
        .directive('changePasswordForm', function () {
            return {
                restrict: 'E',
                scope: {},
                templateUrl: 'src/app/components/forms/changePasswordForm.html',
                controller: 'ChangePasswordFormController'
            };
        });

    angular
        .module('adminportal')
        .controller('ChangePasswordFormController', ChangePasswordFormController);

    ChangePasswordFormController.$inject = ['$scope', 'dialogService', '$state', 'authenticationApi'];

    function ChangePasswordFormController($scope, dialogService, $state, authenticationApi) {

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
                oldPassword: $scope.model.oldPassword,
                newPassword: $scope.model.newPassword
            };
            authenticationApi.changePassword(request).then((data) => {
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