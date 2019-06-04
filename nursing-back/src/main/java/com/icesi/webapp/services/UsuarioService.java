package com.icesi.webapp.services;

import javax.annotation.PostConstruct;

import com.icesi.webapp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.icesi.webapp.model.*;

@Service
public class UsuarioService
{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostConstruct
    public void createUsers()
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuarioRepository.save(new Usuario("camilo", "Juan Camilo", "Tobar", passwordEncoder.encode("12345")));
        usuarioRepository.save(new Usuario("maria", "Maria Alejandra", "Tobar", passwordEncoder.encode("12345")));
        usuarioRepository.save(new Usuario("jeison", "Jeison", "Mejía", passwordEncoder.encode("12345")));
        usuarioRepository.save(new Usuario("andres", "Andrés David", "Paredes", passwordEncoder.encode("12345")));
    }

    public void saveUsuario(Usuario user) {
        usuarioRepository.save(user);
    }
    public Usuario findByIdUsuario(String login) {
        return usuarioRepository.findById(login).get();
    }
    public void removeUsuario(Usuario user) {
        usuarioRepository.delete(user);
    }
}
