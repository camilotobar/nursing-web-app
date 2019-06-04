package com.icesi.webapp.controllers;

import com.icesi.webapp.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String index(){
        return "redirect:/dashboard/";
    }

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model){
        model.addAttribute("username", principal.getName());
        return "dashboard";
    }

    @GetMapping("/entrega-medicamento")
    public String entregaMedicamentos(Usuario usuario, Model model){
        model.addAttribute("name", usuario.getNombre());
        return "Medicines/entrega-medicamento";
    }

}
