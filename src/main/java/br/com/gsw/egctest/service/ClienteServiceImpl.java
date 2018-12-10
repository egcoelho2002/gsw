package br.com.gsw.egctest.service;

import java.util.List;

import br.com.gsw.egctest.model.Cliente;
import br.com.gsw.egctest.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("clienteService")
@Transactional
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente findById(Long id) {
		return clienteRepository.findOne(id);
	}

	public Cliente findByNome(String nome) {
		return clienteRepository.findByNome(nome);
	}

	public void saveCliente(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	public void updateCliente(Cliente cliente){
		saveCliente(cliente);
	}
	
	public void deleteClienteById(Long id){
		clienteRepository.delete(id);
	}

	public void deleteAllClientes(){
		clienteRepository.deleteAll();
	}

	
	public List<Cliente> getAllClientes(){
		return clienteRepository.findAll();
	}

	public boolean isClienteExist(Cliente cliente) {
		return findByNome(cliente.getNome()) != null;
	}

}
