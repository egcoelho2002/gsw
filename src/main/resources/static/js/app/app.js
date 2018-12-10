var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/gswegctest',
    USER_SERVICE_API : 'http://localhost:8080/gswegctest/api/cliente/',
    SAQUE_SERVICE_API : 'http://localhost:8080/gswegctest/api/saque/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'ClienteController',
                controllerAs:'ctrl',
                resolve: {
                    clientes: function ($q, ClienteService) {
                        console.log('Listando todos os clientes');
                        var deferred = $q.defer();
                        ClienteService.loadAllClientes().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

