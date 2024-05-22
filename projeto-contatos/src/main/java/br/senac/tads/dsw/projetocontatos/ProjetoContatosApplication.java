package br.senac.tads.dsw.projetocontatos;

import br.senac.tads.dsw.projetocontatos.security.Permissao;
import br.senac.tads.dsw.projetocontatos.security.Usuario;
import br.senac.tads.dsw.projetocontatos.security.UsuarioRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ProjetoContatosApplication {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoContatosApplication.class, args);
	}
    
    @EventListener
    public void handleContextRefreshdEvent(ContextRefreshedEvent context) {
        // Se não houver permissao cadastrada, inclui permissões
  
        if (usuarioRepository.count() == 0) {
            Usuario u = new Usuario();
            u.setUsername("bryan");
            u.setNome("Bryan Santos");
            u.setEmail("bryan@email.com.br");
            u.setHashSenha("{noop}Abcd1234");
            u.setPermissoes(Set.of(new Permissao("OPERADOR")));
            usuarioRepository.save(u);
            
            u = new Usuario();
            u.setUsername("mika");
            u.setNome("Mika Silva");
            u.setEmail("mika@email.com.br");
            u.setHashSenha("{noop}Abcd1234");
            u.setPermissoes(Set.of(new Permissao("ADMIN")));
            usuarioRepository.save(u);
            
            u = new Usuario();
            u.setUsername("ryu");
            u.setNome("Ryu de Souza");
            u.setEmail("ryu@email.com.br");
            u.setHashSenha("{noop}Abcd1234");
            u.setPermissoes(Set.of(new Permissao("DEUS")));
            usuarioRepository.save(u);
        }
    }
}
