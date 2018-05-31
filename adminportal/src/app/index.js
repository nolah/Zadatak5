(function () {
    'use strict';

    angular
        .module('adminportal', ['ngAnimate', 'ngCookies', 'ngTouch', 'ngSanitize', 'ui.router',
            'ui.bootstrap', 'ngMessages', 'pascalprecht.translate', 'app.config', 'angular-jwt', 'ng.deviceDetector', 'angular-loading-bar', 'angularjs-dropdown-multiselect', 'ngMaterial', 'md.time.picker', 'textAngular'
        ])
        .config(appConfig)
        .config(loadingBarConfig)
        .config(tooltipConfig)
        .config(textAngularConfig)
        .config(angularMaterialConfig)
        .run(run);

    appConfig.$inject = ['$stateProvider', '$urlRouterProvider', '$locationProvider'];

    function appConfig($stateProvider, $urlRouterProvider, $locationProvider) {
        $stateProvider
            .state('basePage', {
                url: '',
                templateUrl: 'src/app/components/pages/basePage.html',
                controller: 'BasePageController'
            })
            .state('basePage.aircraftsPage', {
                url: '/aircrafts',
                templateUrl: 'src/app/components/pages/aircraftsPage.html',
                controller: 'AircraftsPageController'
            })
            .state('basePage.airlinesPage', {
                url: '/airlines',
                templateUrl: 'src/app/components/pages/airlinesPage.html',
                controller: 'AirlinesPageController'
            })
            .state('basePage.flightsPage', {
                url: '/flights',
                templateUrl: 'src/app/components/pages/flightsPage.html',
                controller: 'FlightsPageController'
            })
            .state('signInPage', {
                url: '/sign-in',
                templateUrl: 'src/app/components/pages/signInPage.html',
                controller: 'SignInPageController'
            })
            .state('signUpPage', {
                url: '/sign-up',
                templateUrl: 'src/app/components/pages/signUpPage.html',
                controller: 'SignUpPageController'
            });

        $urlRouterProvider.otherwise('/sign-in');
        $locationProvider.html5Mode(true);
    }

    // Loader config
    loadingBarConfig.$inject = ['cfpLoadingBarProvider'];

    function loadingBarConfig(cfpLoadingBarProvider) {
        cfpLoadingBarProvider.latencyThreshold = 2500;
        cfpLoadingBarProvider.spinnerTemplate = '<div class="loader-wrapper"><div class="loader"></div></div>';
        cfpLoadingBarProvider.includeBar = false;
    }
    // End of Loader config

    // Tooltip config
    tooltipConfig.$inject = ['$uibTooltipProvider'];

    function tooltipConfig($uibTooltipProvider) {
        var tooltipFactory = $uibTooltipProvider.$get[$uibTooltipProvider.$get.length - 1];

        $uibTooltipProvider.$get = ['$window', '$compile', '$timeout', '$document', '$uibPosition', '$interpolate', '$rootScope', '$parse', '$$stackedMap',
            function ($window, $compile, $timeout, $document, $position, $interpolate, $rootScope, $parse, $$stackedMap) {
                // for touch devices, don't return tooltips
                if ('ontouchstart' in $window) {
                    return function () {
                        return {
                            compile: function () {}
                        };
                    };
                }
                // run the default behavior
                return tooltipFactory($window, $compile, $timeout, $document, $position, $interpolate, $rootScope, $parse, $$stackedMap);
            }
        ];
    }
    // End of tooltip config

    // Angular material config
    angularMaterialConfig.$inject = ['$mdThemingProvider', '$mdAriaProvider'];

    function angularMaterialConfig($mdThemingProvider, $mdAriaProvider) {
        $mdThemingProvider.definePalette('primaryPallet', {
            '50': 'e5f2fc',
            '100': 'bedff9',
            '200': '93caf5',
            '300': '68b5f0',
            '400': '47a5ed',
            '500': '2795ea',
            '600': '238de7',
            '700': '1d82e4',
            '800': '1778e1',
            '900': '0e67db',
            'A100': 'ffffff',
            'A200': 'd4e5ff',
            'A400': 'a1c6ff',
            'A700': '87b6ff',
            'contrastDefaultColor': 'light',
            'contrastDarkColors': [
                '50',
                '100',
                '200',
                '300',
                '400',
                'A100',
                'A200',
                'A400',
                'A700'
            ],
            'contrastLightColors': [
                '500',
                '600',
                '700',
                '800',
                '900'
            ]
        });

        $mdThemingProvider.theme('default')
            .primaryPalette('primaryPallet');
        // .accentPalette('orange')
        // .backgroundPalette('yellow');

        $mdAriaProvider.disableWarnings();
    }
    // End of Angular material config

    //Text angular config
    textAngularConfig.$inject = ['$provide'];

    function textAngularConfig($provide) {
        $provide.decorator('taOptions', ['taRegisterTool', '$delegate', function (taRegisterTool, taOptions) { // $delegate is the taOptions we are decorating
            taRegisterTool('sub', {
                iconclass: "fa fa-subscript",
                tooltiptext: 'Subscript',
                action: function () {
                    return this.$editor().wrapSelection("subScript", null);
                },
                activeState: function () {
                    return this.$editor().queryCommandState('subScript');
                }
            });
            // add the button to the default toolbar definition
            taOptions.toolbar[1].push('sub');

            taRegisterTool('sup', {
                iconclass: "fa fa-superscript",
                tooltiptext: 'Superscript',
                action: function () {
                    return this.$editor().wrapSelection("superScript", null);
                },
                activeState: function () {
                    return this.$editor().queryCommandState('superScript');
                }
            });
            // add the button to the default toolbar definition
            taOptions.toolbar[1].push('sup');

            return taOptions;
        }]);
    }
    // End of textangular config

    run.$inject = ['$rootScope', '$state', 'sessionService', '$log'];

    function run($rootScope, $state, sessionService, $log) {
        $rootScope.$on('$stateChangeStart', function (ev, to) {
            if (to.name === 'signInPage') {
                sessionService.clear();
            } else if (!sessionService.canUserAccessState(to.name)) {
                $log.warn('Unauthorized access to secured page, redirecting to signIn.');
                ev.preventDefault();
                $state.go('signInPage');
            }
            $rootScope.pageTitle = to.title;
        });
    }

})();