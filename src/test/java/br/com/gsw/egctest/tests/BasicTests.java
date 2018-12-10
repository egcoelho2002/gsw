package br.com.gsw.egctest.tests;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.gsw.egctest.controller.RestApiController;
import br.com.gsw.egctest.model.Cliente;
import br.com.gsw.egctest.service.ClienteService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RestApiController.class, secure = false)
public class BasicTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    Cliente clienteTeste = new Cliente("Evandro Coelho", 1000.00);

    
    static String BASE  = "http://localhost:8080/gswegctest";
    static String USER_SERVICE_API = "http://localhost:8080/gswegctest/api/cliente/";
    static String SAQUE_SERVICE_API = "http://localhost:8080/gswegctest/api/saque/";
	
	
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetAllClientes() throws Exception {
	List<Cliente> retorno = new ArrayList<Cliente>();
	retorno.add(clienteTeste);
	Mockito.when(clienteService.getAllClientes()).thenReturn(retorno);

	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/cliente")
		.accept(MediaType.APPLICATION_JSON);

	MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	System.out.println(result.getResponse());
	String expected = "{id:1,nome:Evandro Coelho,valor:1000.00}";
	JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void testGetCliente() {
	fail("Not yet implemented");
    }

    @Test
    public void testCreateCliente() {
	fail("Not yet implemented");
    }

    @Test
    public void testUpdateCliente() {
	fail("Not yet implemented");
    }

    @Test
    public void testDeleteCliente() {
	fail("Not yet implemented");
    }

    @Test
    public void testDeleteAllClientes() {
	fail("Not yet implemented");
    }

    @Test
    public void testEfetuarSaqueCliente() {
	fail("Not yet implemented");
    }

}
