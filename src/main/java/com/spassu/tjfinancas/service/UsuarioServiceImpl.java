package com.spassu.tjfinancas.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spassu.tjfinancas.model.Usuario;
import com.spassu.tjfinancas.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> getAllUsuarios() {		
		return usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> getUsuarioById(Long usuarioId) {		
		return usuarioRepository.findById(usuarioId);
	}

	@Override
	public Usuario saveUsuario(Usuario usuario) {
		usuario.setCreatedAt(LocalDate.now());
		usuario.setUpdatedAt(LocalDate.now());
		return usuarioRepository.save(usuario);
	}

	@Override
	public void updateUsuario(Usuario usuario, Long usuarioId) {
		
		Optional<Usuario> usuarioExists = usuarioRepository.findById(usuarioId);
		if(usuarioExists.isPresent()) {
			Usuario usuarioNew = usuarioExists.get();
			usuarioNew.setNome(usuario.getNome());
			usuarioNew.setDataNascimento(usuario.getDataNascimento());
			usuarioNew.setCpf(usuario.getCpf());
			usuarioNew.setSexo(usuario.getSexo());
			usuarioNew.setEmail(usuario.getEmail());
			usuarioNew.setNaturalidade(usuario.getNaturalidade());
			usuarioNew.setNacionalidade(usuario.getNacionalidade());
			usuarioNew.setUpdatedAt(LocalDate.now());
			
			usuarioRepository.saveAndFlush(usuarioNew);
		}		
		
	}

	@Override
	public Boolean existeUsuarioById(Long usuarioId) {
		return usuarioRepository.existsById(usuarioId);
	}

	@Override
	public void deleteUsuarioById(Long usuarioId) {
		usuarioRepository.deleteById(usuarioId);		
	}

	@Override
	public Optional<Usuario> existeUsuarioByCPF(Usuario usuario) {		
		if(usuario.getCpf().length() == 11 && (usuario.getEmail().indexOf('@') > 0)) {
			return usuarioRepository.findByCpf(usuario.getCpf());		
		}
		return null;		
	}

}
