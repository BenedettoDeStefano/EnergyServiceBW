package EnergyServices.fatturaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import EnergyServices.Controller.FatturaController;
import EnergyServices.Entities.Fattura;
import EnergyServices.PayLoad.FatturaPayLoad;
import EnergyServices.Service.FatturaService;

@SpringBootTest
@AutoConfigureMockMvc
public class FatturaControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FatturaService fatturaService;

	@Autowired
	private FatturaController fatturaController;

	String authToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2Njk4Y2VhYS00M2MzLTQ0ZDItOWNhZS05NzA1NDBlNDkxZDIiLCJpYXQiOjE2OTM1NzI5NzAsImV4cCI6MTY5NDE3Nzc3MH0.5MFyZFBRbPS-D4tqjmV117YPWSoN8bWBvl3niORQFZY";

	@BeforeEach
	void setUp() throws NotFoundException {

		Fattura fattura = new Fattura();
		fattura.setId(1L);
		when(fatturaService.getFatturaByID(1L)).thenReturn(fattura);
	}

	@Test
	void testGetFatturaById() throws NotFoundException {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, authToken);

		ResponseEntity<FatturaPayLoad> response = null;
		try {
			response = fatturaController.getFatturaById(1L);
		} catch (NotFoundException e) {

		}

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(1L, response.getBody().getId());
	}

	@Test
	public void testGetFattura() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2Njk4Y2VhYS00M2MzLTQ0ZDItOWNhZS05NzA1NDBlNDkxZDIiLCJpYXQiOjE2OTM1NzI5NzAsImV4cCI6MTY5NDE3Nzc3MH0.5MFyZFBRbPS-D4tqjmV117YPWSoN8bWBvl3niORQFZY";
		mockMvc.perform(get("/fattura").header("Authorization", "Bearer " + token)).andExpect(status().isOk());
	}

}


