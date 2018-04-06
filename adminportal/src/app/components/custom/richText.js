(function () {
    'use strict';

    angular
        .module('adminportal')
        .directive('richText', function () {
            return {
                restrict: 'E',
                scope: {
                    model: '=',
                    htmlRequired: '=?',
                    htmlName: '@?',
                    htmlId: '@?',
                    htmlMinLength: '=',
                    htmlMaxLength: '=',
                    htmlPlaceholder: '@?',
                    form: '=' // form is necessary for validations to work, as they are attached to form object
                },
                templateUrl: 'src/app/components/custom/richText.html',
                controller: 'RichTextController'
            }
        });

    angular
        .module('adminportal')
        .controller('RichTextController', RichTextController);

    RichTextController.$inject = ['$scope', '$element'];

    function RichTextController($scope, $element) {

        // Editor options.
        $scope.options = [
            ['bold', 'italics', 'underline', 'strikeThrough', 'ul', 'ol', 'clear'],
            ['sub', 'sup'],
            ['justifyLeft', 'justifyCenter', 'justifyRight'],
            ['insertLink']
        ];

        $scope.onChange = () => {
            if ($scope.form && ($scope.htmlMinLength || $scope.htmlMaxLength)) {
                let div = document.createElement("div");
                div.innerHTML = $scope.model;
                // replace empty character that shows up sometimes adding length to string
                let string = (div.textContent || div.innerText || "").replace(/﻿/, "");
                let charCount = string.length;

                if ($scope.htmlMinLength) {
                    $scope.form[$scope.htmlName].$setValidity("minlength", charCount >= $scope.htmlMinLength);
                }
                if ($scope.htmlMaxLength) {
                    $scope.form[$scope.htmlName].$setValidity("maxlength", charCount <= $scope.htmlMaxLength);
                }

            }
        }
    }
})();