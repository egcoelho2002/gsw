'use strict';

angular
		.module('crudApp')
		.controller(
				'ClienteController',
				[
						'ClienteService',
						'$scope',
						function(ClienteService, $scope) {

							var self = this;
							self.cliente = {};
							self.clientes = [];
							self.sacar = false;
							self.valorSaque = 0;

							self.submit = submit;
							self.getAllClientes = getAllClientes;
							self.createCliente = createCliente;
							self.updateCliente = updateCliente;
							self.removeCliente = removeCliente;
							self.editCliente = editCliente;
							self.sacarCliente = sacarCliente;
							
							self.reset = reset;

							self.successMessage = '';
							self.errorMessage = '';
							self.done = false;

							self.onlyIntegers = /^\d+$/;
							self.onlyNumbers = /^\d+([,.]\d+)?$/;

							function submit() {
								console.log('Enviando');
								if (self.sacar){
									console.log('Efetuando Saque cliente id '+self.cliente.id, self.cliente.id);
									efetuarSaqueCliente(self.cliente.id, self.valorSaque);
								} else if (self.cliente.id === undefined || self.cliente.id === null) {
									console.log('Salvando novo Cliente', self.cliente);
									createCliente(self.cliente);
								} else {
									updateCliente(self.cliente, self.cliente.id);
									console.log('Alterando Cliente com id ', self.cliente.id);
								}
							}

							function efetuarSaqueCliente(id, valorSaque){
								console.log('Processando o saque Cliente '+id +' valorSaque '+valorSaque);
								
								ClienteService
										.efetuarSaqueCliente(id, valorSaque)
										.then(
												function(response) {
													console.log('Saque efetuado com sucesso VALOR:'+valorSaque);
													self.successMessage = response.resultado;
													self.errorMessage = '';
													self.done = true;
													self.saque = false;
													self.cliente = {};
													$scope.myForm.$setPristine();
												},
												function(errResponse) {
													console.error('Erro ao efetuar o saque para o Cliente');
													self.errorMessage = 'Erro ao efetuar o saque para o Cliente : ' + errResponse.data.errorMessage;
													self.successMessage = '';
												});
							}
							function createCliente(cliente) {
								console.log('Criando cliente');
								ClienteService
										.createCliente(cliente)
										.then(
												function(response) {
													console.log('Cliente criado com sucesso');
													self.successMessage = 'Cliente criado com sucesso';
													self.errorMessage = '';
													self.done = true;
													self.cliente = {};
													$scope.myForm.$setPristine();
												},
												function(errResponse) {
													console.error('Erro ao criar o Cliente');
													self.errorMessage = 'Erro ao criar o Cliente: '+ errResponse.data.errorMessage;
													self.successMessage = '';
												});
							}

							function updateCliente(cliente, id) {
								console.log('Alteração de cliente');
								ClienteService
										.updateCliente(cliente, id)
										.then(
												function(response) {
													console.log('Cliente alterado com sucesso');
													self.successMessage = 'Cliente alterado com sucesso';
													self.errorMessage = '';
													self.done = true;
													$scope.myForm.$setPristine();
												},
												function(errResponse) {
													console.error('Erro na alteração do Cliente');
													self.errorMessage = 'Erro na alteração do Cliente '+ errResponse.data;
													self.successMessage = '';
												});
							}

							function removeCliente(id) {
								console.log('Removendo Cliente with id ' + id);
								ClienteService.removeCliente(id)
										.then(
												function() {
													console.log('Cliente ' + id + ' removido com sucesso');
												},
												function(errResponse) {
													console.error('Erro ao remover o cliente  '+ id+ ', Erro :'+ errResponse.data);
												});
							}

							function getAllClientes() {
								return ClienteService.getAllClientes();
							}

							function editCliente(id) {
								self.successMessage = '';
								self.errorMessage = '';
								self.sacar = false;
								ClienteService
										.getCliente(id)
										.then(
												function(cliente) {
													self.cliente = cliente;
												},
												function(errResponse) {
													console.error('Erro carregar o cliente  '+ id+ ', Erro :'+ errResponse.data);
												});
							}
							function sacarCliente(id) {
								self.successMessage = '';
								self.errorMessage = '';
								self.sacar = true;
								ClienteService
										.getCliente(id)
										.then(
												function(cliente) {
													self.cliente = cliente;
												},
												function(errResponse) {
													console.error('Erro carregar  o cliente  '+ id+ ', Erro :'+ errResponse.data);
												});
							}
							function reset() {
								self.successMessage = '';
								self.errorMessage = '';
								self.cliente = {};
								$scope.myForm.$setPristine(); // reset Form
							}
						}

				]);