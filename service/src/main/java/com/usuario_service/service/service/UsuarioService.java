package com.usuario_service.service.service;

import java.util.List;
import java.util.Optional;

import com.usuario_service.service.entity.Usuario;

public interface UsuarioService {
    List<Usuario> listarTodos();
    Optional<Usuario> obtenerPorId(Long id);
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
}