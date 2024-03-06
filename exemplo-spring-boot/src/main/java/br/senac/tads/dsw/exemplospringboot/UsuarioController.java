package br.senac.tads.dsw.exemplospringboot;

import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;
    
    @GetMapping("/incluir")
    public String incluirUsuario(Model model) {
        model.addAttribute("usuario", new UsuarioDto());
        return "usuarios/form";
    }
    
    @PostMapping("/incluir")
    public String salvarIncluirUsuario(
            @ModelAttribute("usuario") @Valid UsuarioDto usuario,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "usuarios/form";
        }
        if (!usuario.getSenha().equals(usuario.getConfirmarSenha())) {
            return "usuarios/form";
        }
        service.save(usuario);
        redirectAttributes.addAttribute("msgSucesso", "Usuário incluido com sucesso");
        return "redirect:/usuarios/incluir";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(Model model,
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {
        
        Optional<UsuarioDto> optUsuario = service.findById(id);
        if (optUsuario.isEmpty()) {
            redirectAttributes.addAttribute("msgErro", "Usuário não encontrado");
            return "redirect:/usuarios/incluir";
        }
        UsuarioDto usuario = optUsuario.get();
        model.addAttribute("usuario", usuario);
        return "usuarios/form";
    }
    
    @PostMapping("/editar")
    public String salvarEditarUsuario(@ModelAttribute("usuario") UsuarioDto usuario, 
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "usuarios/form";
        }
        service.save(usuario);
        redirectAttributes.addAttribute("msgSucesso", "Usuário editado com sucesso");
        return "redirect:/usuarios/editar/" + usuario.getId();
    }
    
    
    
    
    
}
