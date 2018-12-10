'use strict';

angular.module('crudApp').factory('ClienteService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllClientes: loadAllClientes,
                getAllClientes: getAllClientes,
                getCliente: getCliente,
                createCliente: createCliente,
                updateCliente: updateCliente,
                removeCliente: removeCliente,
                efetuarSaqueCliente: efetuarSaqueCliente
            };

            return factory;

            function loadAllClientes() {
                console.log('Listando todos os clientes');
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Clientes listados com sucesso');
                            $localStorage.clientes = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Erro ao carregar todos os clientes');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllClientes(){
                return $localStorage.clientes;
            }

            function getCliente(id) {
                console.log('Carregar Cliente id :'+id);
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Cliente id :'+id+' carregado com sucesso');
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erro ao carrregar cliente id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createCliente(cliente) {
                console.log('Criando Cliente');
                var deferred = $q.defer();
                $http.post(urls.USER_SERVICE_API, cliente)
                    .then(
                        function (response) {
                            loadAllClientes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Erro ao criar Cliente : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateCliente(cliente, id) {
                console.log('Alterando Cliente id '+id);
                var deferred = $q.defer();
                $http.put(urls.USER_SERVICE_API + id, cliente)
                    .then(
                        function (response) {
                            loadAllClientes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erro ao alterar Cliente id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function efetuarSaqueCliente(id, valorSaque){
            	 console.log('Efetuando o saque Cliente id:: '+id + ' valorSaque:: '+valorSaque);
                 var deferred = $q.defer();
                 $http.put(urls.SAQUE_SERVICE_API + id, valorSaque)
                     .then(
                         function (response) {
                        	 console.log(response);
                             loadAllClientes();
                             getCliente(id);
                             deferred.resolve(response.data);
                         },
                         function (errResponse) {
                             console.error('Erro ao efetuar saque Cliente');
                             deferred.reject(errResponse);
                         }
                     );
                 return deferred.promise;
            }

            function removeCliente(id) {
                console.log('Removendo Cliente id '+id);
                var deferred = $q.defer();
                $http.delete(urls.USER_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllClientes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erro ao remover Cliente id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);