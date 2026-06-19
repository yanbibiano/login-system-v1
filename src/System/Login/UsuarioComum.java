package System.Login;

public class UsuarioComum extends Usuario implements Registravel {
    public UsuarioComum(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    @Override
    public void exibirPainel() {
        System.out.println("\n PAINEL DO UTILIZADOR: Bem-vindo, " + getNome() + "! Aqui podes ver o teu perfil.");
    }

    @Override
    public void registrarAcesso() {
        System.out.println("AUDITORIA: Login de utilizador padrão registado.");
    }

}
