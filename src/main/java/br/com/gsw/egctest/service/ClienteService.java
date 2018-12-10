package br.com.gsw.egctest.service;


import br.com.gsw.egctest.model.Cliente;

import java.util.List;

public interface ClienteService {
	
	Cliente findById(Long id);

	Cliente findByNome(String nome);

	void saveCliente(Cliente cliente);

	void updateCliente(Cliente cliente);

	void deleteClienteById(Long id);

	void deleteAllClientes();

	List<Cliente> getAllClientes();
	
	boolean isClienteExist(Cliente cliente);	

}