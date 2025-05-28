package com.veterinaria.petly;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class PetlyApplicationTests {

//	@Autowired
//	private MockMvc mockMvc;
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	@Test
//	void testCRUDCliente() throws Exception {
//
//		ClienteDTO nuevoCliente = ClienteDTO.builder()
//				.nombre("Test")
//				.apellido("User")
//				.email("test@example.com")
//				.telefono("123456789")
//				.build();
//
//		String clienteJson = objectMapper.writeValueAsString(nuevoCliente);
//
//		MvcResult result = mockMvc.perform(post("/api/clientes")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(clienteJson))
//				.andExpect(status().isCreated())
//				.andReturn();
//
//		ClienteDTO createdCliente = objectMapper.readValue(
//				result.getResponse().getContentAsString(), ClienteDTO.class);
//
//		// 2. Obtener Cliente
//		mockMvc.perform(get("/api/clientes/" + createdCliente.getId()))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.nombre").value("Test"));
//
//		// 3. Actualizar Cliente
//		createdCliente.setNombre("UpdatedName");
//		mockMvc.perform(put("/api/clientes/" + createdCliente.getId())
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsString(createdCliente)))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.nombre").value("UpdatedName"));
//
//		// 4. Eliminar Cliente
//		mockMvc.perform(delete("/api/clientes/" + createdCliente.getId()))
//				.andExpect(status().isNoContent());
//	}

}
