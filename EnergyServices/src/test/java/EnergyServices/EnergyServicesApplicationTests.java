package EnergyServices;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class EnergyServicesApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	

	@Test
	void contextLoads() throws Exception {
		this.mockMvc
				.perform(post("http://localhost:3001/auth/login").contentType(MediaType.APPLICATION_JSON)
						.content("{\"email\":\"marco.mail@google.com\", \"password\":\"prova345\"}")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("accessToken")));
	}

}
