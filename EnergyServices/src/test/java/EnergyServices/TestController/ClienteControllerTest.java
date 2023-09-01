package EnergyServices.TestController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import EnergyServices.Controller.ClienteController;
import EnergyServices.Entities.Cliente;
import EnergyServices.PayLoad.ClientePayLoad;
import EnergyServices.Service.ClienteService;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private ClienteController clienteController;

	private ClienteService clienteService;

	String authToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MGE0OTczZi0zMWIwLTQ4ZTctYTc2Mi1mMjVlMDhjZGY5NzgiLCJpYXQiOjE2OTM1NTc1NTQsImV4cCI6MTY5NDE2MjM1NH0.WTIY25aCARGqJgmBj24uEvCMvpOdsfXkI5Ft4MHn3D8";

	@BeforeEach
	void setUp() {
		clienteService = mock(ClienteService.class);
		clienteController = new ClienteController(clienteService);
	}

	@Test
	void testGetClienteById() throws NotFoundException {
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		when(clienteService.getClienteByID(1L)).thenReturn(cliente);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, authToken);
		ResponseEntity<ClientePayLoad> response = clienteController.getClienteById(1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(1L, response.getBody().getId());
	}


	@Test
	public void testGetClienti() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MGE0OTczZi0zMWIwLTQ4ZTctYTc2Mi1mMjVlMDhjZGY5NzgiLCJpYXQiOjE2OTM1NTc1NTQsImV4cCI6MTY5NDE2MjM1NH0.WTIY25aCARGqJgmBj24uEvCMvpOdsfXkI5Ft4MHn3D8";
		mockMvc.perform(get("/cliente").header("Authorization", "Bearer " + token)).andExpect(status().isOk());
	}




}
