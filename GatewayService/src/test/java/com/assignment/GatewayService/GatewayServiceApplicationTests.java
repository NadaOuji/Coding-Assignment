package com.assignment.GatewayService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GatewayServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private WebTestClient webClient;

	@BeforeEach
	public void setUp() {
		webClient = webClient.mutate().baseUrl("http://localhost:" + port).build();
	}

	@Test
	void contextLoads() {
	}
/*	@Test
	public void testGetCustomer() {
		webClient.get().uri("/customer/1")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Customer.class);
	}

	@Configuration
	@EnableAutoConfiguration
	@Import(GatewayServiceApplication.class)
	static class TestConfiguration {

		@Bean
		public CustomerServiceClient customerServiceClient() {
			return new CustomerServiceClient("http://localhost:8081");
		}
	}
 */
}
