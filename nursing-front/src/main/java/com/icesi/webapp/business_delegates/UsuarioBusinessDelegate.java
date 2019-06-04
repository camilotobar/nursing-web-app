package com.icesi.webapp.business_delegates;

import com.google.gson.Gson;
import com.icesi.webapp.model.Usuario;
import com.icesi.webapp.model.UsuarioPrincipal;
import com.icesi.webapp.repositories.UsuarioRepository;
import com.icesi.webapp.request.RequestModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class UsuarioBusinessDelegate implements UserDetailsService {

    @Autowired
    private RequestModule REQUEST_MODULE;

    @Override
    public UserDetails loadUserByUsername(String username){

        try {
            String plusPath = "get-user/" + username;
            Usuario user = new Gson().fromJson(REQUEST_MODULE.GETRequest(plusPath), Usuario.class);
            if (user == null) {
                throw new RuntimeException(username);
            }
            return new UsuarioPrincipal(user);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(username);
        }
    }
}
