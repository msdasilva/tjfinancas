package com.spassu.tjfinancas.service;

import java.util.List;
import java.util.Optional;

import com.spassu.tjfinancas.model.Usuario;

public interface UsuarioService {
	
	List<Usuario> getAllUsuarios();
	Optional<Usuario> getUsuarioById(Long usuarioId);
	Usuario saveUsuario(Usuario usuario);
	void updateUsuario(Usuario usuario, Long usuarioId);
	Boolean existeUsuarioById(Long usuarioId);
	void deleteUsuarioById(Long usuarioId);
	Optional<Usuario> existeUsuarioByCPF(Usuario usuario);

}
