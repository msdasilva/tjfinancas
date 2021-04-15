package com.spassu.tjfinancas;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spassu.tjfinancas.model.Usuario;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void usuarioTestGetAll() throws Exception {
		mockMvc.perform(get("/api/v1/usuarios")).andExpect(status().isOk());
	}

	@Test
	void usuarioTestGetById() throws Exception {
		mockMvc.perform(get("/api/v1/usuarios/1")).andExpect(status().isOk());
	}

	@Test
	void usuarioTestSave() throws Exception {

		Date dataNascimento = new Date();
		LocalDate dataCriacao = LocalDate.now();

		Usuario usuario = new Usuario("fulano", "m", "fulano@spassu.com.br", dataNascimento, "Rio de Janeiro",
				"Brasileiro", "07589654212", dataCriacao);
		mockMvc.perform(post("/api/v1/usuarios").contentType("application/json")
				.content(objectMapper.writeValueAsString(usuario))).andExpect(status().isOk());

	}

	@Test
	void usuarioTestUpdate() throws Exception {

		Date dataNascimento = new Date();
		LocalDate dataCriacao = LocalDate.now();

		Usuario usuario = new Usuario("beltrano", "m", "beltrano@spassu.com.br", dataNascimento, "Rio de Janeiro",
				"Brasileiro", "07589654212", dataCriacao);
		mockMvc.perform(post("/api/v1/usuarios/1").contentType("application/json")
				.content(objectMapper.writeValueAsString(usuario))).andExpect(status().isOk());

	}

	@Test
	void usuarioTestDeleteById() throws Exception {
		mockMvc.perform(get("/api/v1/usuarios/1")).andExpect(status().isOk());
	}

}
