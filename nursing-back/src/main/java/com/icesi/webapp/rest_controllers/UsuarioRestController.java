package com.icesi.webapp.rest_controllers;

import com.icesi.webapp.model.Usuario;
import com.icesi.webapp.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioRestController {

    @Autowired private UsuarioService usuarioService;

    @GetMapping("/get-user/{username}")
    public Usuario GetUser(@PathVariable("username") String username)
    {
        Usuario usuario = usuarioService.findByIdUsuario(username);
        return usuario;
    }
}
