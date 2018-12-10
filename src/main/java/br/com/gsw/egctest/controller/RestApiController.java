package br.com.gsw.egctest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gsw.egctest.model.Cliente;
import br.com.gsw.egctest.service.ClienteService;
import br.com.gsw.egctest.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    ClienteService clienteService; // Service which will do all data retrieval/manipulation work

    // -------------------Retrieve All
    // Clientes---------------------------------------------

    @RequestMapping(value = "/cliente/", method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> getAllClientes() {
	List<Cliente> clientes = clienteService.getAllClientes();
	if (clientes.isEmpty()) {
	    return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
    }

    // -------------------Retrieve Single
    // Cliente------------------------------------------

    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCliente(@PathVariable("id") long id) {
	logger.info("Localizando Cliente id {}", id);
	Cliente cliente = clienteService.findById(id);
	if (cliente == null) {
	    logger.error("Cliente com id {} não encontrado.", id);
	    return new ResponseEntity(new CustomErrorType("Cliente  id " + id + " não encontrado"), HttpStatus.NOT_FOUND);
	}
	return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    // -------------------Create a Cliente-------------------------------------------

    @RequestMapping(value = "/cliente/", method = RequestMethod.POST)
    public ResponseEntity<?> createCliente(@RequestBody Cliente cliente, UriComponentsBuilder ucBuilder) {
	logger.info("Criando Cliente : {}", cliente);

	if (clienteService.isClienteExist(cliente)) {
	    logger.error("Não foi possivel criar. Já existe um cliente Cliente com nome {} ", cliente.getNome());
	    return new ResponseEntity( new CustomErrorType("Não foi possivel criar. Já existe um cliente Cliente com nome " + cliente.getNome()), HttpStatus.CONFLICT);
	}
	clienteService.saveCliente(cliente);

	HttpHeaders headers = new HttpHeaders();
	headers.setLocation(ucBuilder.path("/api/cliente/{id}").buildAndExpand(cliente.getId()).toUri());
	return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Cliente
    // ------------------------------------------------

    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCliente(@PathVariable("id") long id, @RequestBody Cliente cliente) {
	logger.info("Atualizando Cliente id {}", id);

	Cliente currentCliente = clienteService.findById(id);

	if (currentCliente == null) {
	    logger.error("Não foi possivel alterar o Cliente id {}, pois não existe.", id);
	    return new ResponseEntity(new CustomErrorType("Não foi possivel alterar o Cliente id  " + id + " , pois não existe."),HttpStatus.NOT_FOUND);
	}

	currentCliente.setNome(cliente.getNome());
	currentCliente.setValor(cliente.getValor());

	clienteService.updateCliente(currentCliente);
	return new ResponseEntity<Cliente>(currentCliente, HttpStatus.OK);
    }

    // ------------------- Delete a Cliente-----------------------------------------

    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCliente(@PathVariable("id") long id) {
	logger.info("Localizando e removendo Cliente id {}", id);

	Cliente cliente = clienteService.findById(id);
	if (cliente == null) {
	    logger.error("Não foi possivel remover, pois o Cliente id {} não foi encontrado.", id);
	    return new ResponseEntity(new CustomErrorType("Não foi possivel remover, pois o Cliente id " + id + " não foi encontrado."),HttpStatus.NOT_FOUND);
	}
	clienteService.deleteClienteById(id);
	return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Clientes-----------------------------

    @RequestMapping(value = "/cliente/", method = RequestMethod.DELETE)
    public ResponseEntity<Cliente> deleteAllClientes() {
	logger.info("Removendo todos os Clientes");

	clienteService.deleteAllClientes();
	return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
    }

    
    
    @RequestMapping(value = "/saque/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> efetuarSaqueCliente(@PathVariable("id") long id, @RequestBody Double valorSaque) {
	logger.info("Efetuando saque para o Cliente id {"+id+"} valor " + valorSaque);

	Cliente currentCliente = clienteService.findById(id);
	
	if (currentCliente == null) {
	    logger.error("Não foi possivel localizar o Cliente id {}, pois não existe.", id);
	    return new ResponseEntity(new CustomErrorType("Não foi possivel localizar o Cliente id  " + id + " , pois não existe."),HttpStatus.NOT_FOUND);
	}
	
	double valorDisponivel = currentCliente.getValor();
	if (valorDisponivel < valorSaque) {
	    logger.error("Valor para Saque maior que o saldo disponivel", id);
	    return new ResponseEntity(new CustomErrorType("Valor para Saque maior que o saldo disponivel."),HttpStatus.NOT_FOUND);
	}
	
	if (valorSaque % 10 != 0) {
	    logger.error("Valor não divisivel por 10", id);
	    return new ResponseEntity(new CustomErrorType("O valor para saque precisa ser divisivel por 10."),HttpStatus.NOT_FOUND);
	}

	
	double valor = valorSaque;

	int qtd_nota100;
	int qtd_nota50;
	int qtd_nota20;
	int qtd_nota10;
      
 
        
        qtd_nota100 =   new Double (valor / 100).intValue();  
        valor = valor - (qtd_nota100 * 100);
 
        qtd_nota50 = new Double (valor / 50).intValue();  
        valor = valor - (qtd_nota50 * 50);
 
        
        qtd_nota20 = new Double (valor / 20).intValue();  
        valor = valor - (qtd_nota20 * 20);
        
        
        qtd_nota10 = new Double (valor / 10).intValue();  
        valor = valor - (qtd_nota10 * 10);
 
        
        String resultado = "Seu saque resultou em:";
        
        if (qtd_nota100 > 0 ) {
            resultado += " ["+qtd_nota100 + " notas de 100. ] ";
        }
        if (qtd_nota50 > 0 ) {
            resultado += " [ "+qtd_nota50 + " notas de 50. ] ";
        }
        if (qtd_nota20 > 0 ) {
            resultado += " [ "+qtd_nota20 + " notas de 20. ] ";
        }
        if (qtd_nota10 > 0 ) {
            resultado += " [ "+qtd_nota10 + " notas de 10. ] ";
        }
	
	
	// atualiza o saldo do cliente apos o saque
	currentCliente.setValor(currentCliente.getValor() - valorSaque);
	clienteService.updateCliente(currentCliente);
	
	
	Map<String, Object> result = new HashMap<String,Object>();
	result.put("resultado",resultado);
	result.put("cliente",currentCliente);

	
	logger.info("SUCESSO - Efetuando saque para o Cliente id {"+id+"} valor " + valorSaque);
	return new ResponseEntity(result, HttpStatus.OK);
    }
}