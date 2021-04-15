package com.spassu.tjfinancas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spassu.tjfinancas.model.Usuario;
import com.spassu.tjfinancas.service.UsuarioService;


/**
 * 
 * @author Mario Santos
 * @version 1.0
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	/**
	 * EndPoint para retornar os usuarios do sistema.
	 * @return Todos os usuarios do sistema.
	 */
	@GetMapping("/usuarios")
	public List<Usuario> getAllUsuarios() {
		return usuarioService.getAllUsuarios();
	}

	/**
	 * EndPoint para pesquisar um usuario pelo id.
	 * @param usuarioId
	 * @return um usuario fazendo a pesquisa pelo id
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable(value = "id") Long usuarioId) {
		Optional<Usuario> usuario = usuarioService.getUsuarioById(usuarioId);
		if (usuario.isPresent()) {
			return ResponseEntity.ok().body(usuario.get());
		} else {
			return (ResponseEntity<Usuario>) ResponseEntity.notFound();
		}
	}

	/**
	 * EndPoint para cadastra um usuario.
	 * @param usuario
	 * @return o usuario que foi cadastrado.
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/usuarios")
	public ResponseEntity<Usuario> createUsuario(@Validated @RequestBody Usuario usuario) {
		Optional<Usuario> usuarioComCPF = usuarioService.existeUsuarioByCPF(usuario);
		if (usuarioComCPF.isEmpty()) {
			Usuario usuarioCreated = usuarioService.saveUsuario(usuario);
			return ResponseEntity.ok().body(usuarioCreated);
		} else {
			return (ResponseEntity<Usuario>) ResponseEntity.noContent();
		}

	}

	/**
	 * EndPoint para atualizar as informações do usuario.
	 * @param usuarioId
	 * @param usuario
	 * @return sucesso ou não encontrado para atualização do usuario 
	 */
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<?> updateUsuario(@PathVariable(value = "id") Long usuarioId,
			@Validated @RequestBody Usuario usuario) {

		Boolean existeUsuario = usuarioService.existeUsuarioById(usuarioId);
		if (Boolean.TRUE.equals(existeUsuario)) {
			usuarioService.updateUsuario(usuario, usuarioId);
			return ResponseEntity.ok("");
		} else {
			return (ResponseEntity<?>) ResponseEntity.notFound();
		}

	}
	
	/**
	 * EndPoint para excluir usuario.
	 * @param usuarioId
	 * @return sucesso ou não encontrado para atualização do usuario 
	 */
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long usuarioId) {
		Boolean existeUsuario = usuarioService.existeUsuarioById(usuarioId);
		if (Boolean.TRUE.equals(existeUsuario)) {
			usuarioService.deleteUsuarioById(usuarioId);
			return ResponseEntity.ok("");
		} else {
			return (ResponseEntity<?>) ResponseEntity.notFound();
		}
	}
}
