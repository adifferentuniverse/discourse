// create the module and name it scotchApp
var discourseApp = angular.module('discourseApp', [
    'ngRoute'
]);

// configure our routes
discourseApp.config([ '$locationProvider', '$routeProvider',
    function config($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('!');

        $routeProvider
            .when('/', {
                templateUrl: 'pages/root.html',
                controller: 'rootController'
            })

            // route for the about page
            .when('/conferences', {
                templateUrl: 'pages/conferences.html',
                controller: 'conferenceController'
            })
            .otherwise("/");
    }
]);

discourseApp.controller('rootController', function ($scope) {

    // create a message to display in our view
    $scope.message = 'Everyone come and see how good I look!';
});

discourseApp.controller('conferenceController', function ($scope) {

    // create a message to display in our view
    $scope.message = 'Blah';
});
