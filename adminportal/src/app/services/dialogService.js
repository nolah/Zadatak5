(function () {
    'use strict';

    angular.module('adminportal').factory('dialogService', dialogService);

    dialogService.$inject = ['$mdDialog', '$translate'];

    function dialogService($mdDialog, $translate) {

        function openDialog(input, template, className) {
            return $mdDialog.show({
                template: '<md-dialog data-ng-class="{\'md-dialog--' + className + '\':' + !!className + '}">' + template + '</md-dialog>',
                controller: DialogController,
                locals: {
                    input: input
                },
                parent: angular.element(document.body),
                clickOutsideToClose: true,
                escapeToClose: true,
                fullscreen: true
            }, () => {});
        }

        function openConfirmationDialog(input) {
            let confirmDialog = $mdDialog.confirm({
                controller: DialogController,
                locals: {
                    input: input
                },
                multiple: true,
                template: '<md-dialog>' +
                    '<md-dialog-content class="u-padding-top-50">' +
                    '<h3 data-ng-bind-html="message"></h3>' +
                    '</md-dialog-content>' +
                    '<md-dialog-actions data-layout data-layout-align="end center">' +
                    '<md-button data-ng-if="input.cancelAction" data-ng-click="confirmAction(\'CANCEL\')">{{input.cancelAction | translate}}</md-button>' +
                    '<md-button data-ng-click="confirmAction(\'DISMISS\')">{{input.dismissAction || "CANCEL" | translate}}</md-button>' +
                    '<md-button class="md-raised md-primary" data-ng-click="confirmAction(\'CONFIRM\')">{{input.confirmAction || "YES" | translate}}</md-button>' +
                    '</md-dialog-actions>' +
                    '</md-dialog>',
            });
            $mdDialog.show(confirmDialog);
        }

        function openErrorDialog(input) {
            let errorDialog = {
                controller: DialogController,
                locals: {
                    input: input
                },
                multiple: true,
                template: '<md-dialog>' +
                    '<md-dialog-content class="u-padding-top-50">' +
                    '<div class="u-text-center">' +
                    '<i class="fa fa-exclamation-circle u-color-danger u-font-size-50"></i>' +
                    '<h3>{{title}}</h3>' +
                    '</div>' +
                    '<p class="u-text-center">{{message}}</p>' +
                    '<md-dialog-actions>' +
                    '<md-button class="md-raised md-primary" data-ng-click="dialog.close()">{{\'OK\' | translate}}</md-button>' +
                    '</md-dialog-actions>' +
                    '</md-dialog>',
            };
            $mdDialog.show(errorDialog);
        }

        return {

            openTextInfoDialog: (input) => {
                return openDialog(input, '<div class="md-dialog--header"><h3>' + input + '</h3></div><md-dialog-actions><md-button class="md-raised md-primary" data-ng-click="dialog.close()">' + $translate.instant('OK') + '</md-button></md-dialog-actions>')
            },

            openConfirmationDialog: (input) => {
                return openConfirmationDialog(input);
            },

            openErrorDialog: (input) => {
                return openErrorDialog(input);
            },

            openCreateAircraftForm: (input) => {
                return openDialog(input, '<create-aircraft-form  dialog="dialog"></create-aircraft-form>')
            },
            openUpdateAircraftForm: (input) => {
                return openDialog(input, '<update-aircraft-form id="input.id" dialog="dialog"></update-aircraft-form>')
            },
            openCreateAirlineForm: (input) => {
                return openDialog(input, '<create-airline-form  dialog="dialog"></create-airline-form>')
            },
            openUpdateAirlineForm: (input) => {
                return openDialog(input, '<update-airline-form id="input.id" dialog="dialog"></update-airline-form>')
            }

        };
    }

    angular.module('adminportal').controller('DialogController', DialogController);
    DialogController.$inject = ['$scope', '$mdDialog', 'input', '$translate'];

    function DialogController($scope, $mdDialog, input, $translate) {
        $scope.input = input;
        $scope.message = '';
        $scope.title = '';

        $scope.close = function () {
            $mdDialog.hide();
        };

        $scope.dialog = {
            close: $scope.close
        };

        if ($scope.input.title) {
            $translate($scope.input.title, $scope.input.titleData).then(
                translation => {
                    $scope.title = translation;
                }, translation => {
                    $scope.title = translation;
                }
            );
        }

        if ($scope.input.message) {
            $translate($scope.input.message, $scope.input.messageData).then(
                translation => {
                    $scope.message = translation;
                }, translation => {
                    $scope.message = translation;
                }
            );
        }

        $scope.confirmAction = answer => {
            if (answer === 'CONFIRM') {
                if (input.successCallback) input.successCallback();
            }
            if (answer === 'DISMISS') {
                if (input.errorCallback) input.errorCallback();
            }

            $scope.close();
        }
    }
})();