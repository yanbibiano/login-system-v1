package System.Login;

public class Administrador extends Usuario implements Registravel {
    public Administrador(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    @Override
    public void exibirPainel() {
        System.out.println("\n⚙PAINEL ADMIN: Acesso total ao banco de dados liberado para \"" + getNome());
    }

    @Override
    public void registrarAcesso() {
        System.out.println(" AUDITORIA: Acesso de Administrador registado com sucesso.");
    }
}
