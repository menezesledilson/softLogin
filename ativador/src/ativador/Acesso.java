 
package ativador;
  
public class Acesso {
    
    private Long id;
    private String nomeUsuario;
    private String nomeLogin;
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeLogin() {
        return nomeLogin;
    }

    public void setNomeLogin(String nomeLogin) {
        this.nomeLogin = nomeLogin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
