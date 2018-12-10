<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Cliente </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.cliente.id" />
	                <input type="hidden" ng-model="ctrl.sacar" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">Nome</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-disabled="ctrl.sacar"  ng-model="ctrl.cliente.nome" id="uname" class="clientename form-control input-sm" placeholder="Informe o Nome" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                
	                
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="valor">Saldo Disponivel</label>
	                        <div class="col-md-7">
                       				<input type="text" ng-disabled="ctrl.sacar" ng-model="ctrl.cliente.valor" id="valor" class="form-control input-sm" placeholder="Informe Saldo da Conta." required ng-pattern="ctrl.onlyNumbers"/>
	                        </div>
	                    </div>
	                </div>
	                
	            	<div class="row" ng-if="ctrl.sacar">
	                  <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="valorSaque">Valor do Saque</label>
	                        <div class="col-md-7">
	                            <input type="text"  ng-model="ctrl.valorSaque" id="valorSaque" class="form-control input-sm" placeholder="Informe o Valor para saque." required ng-pattern="ctrl.onlyNumbers"/>
	                        </div>
	                    </div>
	              	 </div>

	                

	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.cliente.id ? 'Adicionar' : ctrl.cliente.id && ctrl.sacar? 'CONFIRMAR SAQUE	' : 'Alterar'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Limpar Formulario</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Lista de Clientes </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>NOME</th>
		                <th>VALOR NA CONTA</th>
		                <th width="100"></th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in ctrl.getAllClientes()">
		                <td>{{u.id}}</td>
		                <td>{{u.nome}}</td>
		                <td>{{u.valor}}</td>
		                <td><button type="button" ng-click="ctrl.sacarCliente(u.id)" class="btn btn-success">Efetuar Saque</button></td>
		                <td><button type="button" ng-click="ctrl.editCliente(u.id)" class="btn btn-success custom-width">Editar</button></td>
		                <td><button type="button" ng-click="ctrl.removeCliente(u.id)" class="btn btn-danger custom-width">Remover</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>