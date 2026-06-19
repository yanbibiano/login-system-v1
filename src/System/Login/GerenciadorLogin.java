package System.Login;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GerenciadorLogin {
    private ArrayList<Usuario> bancoDeDados;

    private GerenciadorDeArquivo arquivoDeBackup = new GerenciadorDeArquivo();

    public GerenciadorLogin() {
        this.bancoDeDados = arquivoDeBackup.carregarUtilizadores();
    }

    public void cadastrarUsuario(Scanner tuba) {
        try {
            System.out.println("TELA DE LOGIN");

            tuba.nextLine();

            System.out.println("Digite seu nome: ");
            String nome = tuba.nextLine();

            System.out.println("Digite seu email: ");
            String email = tuba.next();

            System.out.println("Digite sua senha: ");
            String senha = tuba.next();

            for (Usuario U : bancoDeDados) {
                if (U.getEmail().equalsIgnoreCase(email)) {
                    System.out.println("ERRO: O e-mail '" + email + "' já está em uso por outro utilizador!");
                    System.out.println("Cadastro cancelado.");
                    return;
                }

            }

            System.out.println("Qual o seu nível de acesso?");
            System.out.println("[ 1 ]Administrador");
            System.out.println("[ 2 ] Utilizador comum");
            System.out.println("Escolha: ");
            int nivel = tuba.nextInt();


            Usuario novoUsuario = null;

            if (nivel == 1) {
                novoUsuario = new Administrador(nome, email, senha);
            } else if (nivel == 2) {
                novoUsuario = new UsuarioComum(nome, email, senha);
            } else {
                System.out.println("Nível de acesso inválido");
                return;
            }

            bancoDeDados.add(novoUsuario);
            System.out.println("Cadastro realizado com sucesso! Total de utilizadores: " + bancoDeDados.size());

            arquivoDeBackup.salvasUtilizadores(bancoDeDados);

        } catch (InputMismatchException e) {
            System.out.println("ERRO CRÍTICO: Entrada inválida. Esperava-se um número!");
            tuba.next();
        }


    }

    public Usuario fazerLogin(Scanner tuba) {
        if (bancoDeDados.isEmpty()) {
            System.out.println("Sistema vazio. Ninguém cadastrado ainda!");
            return null;
        }

        System.out.println("Digite seu E-mail de acesso: ");
        String emailDigitado = tuba.next();

        Usuario usuarioEncontrado = null;
        for (Usuario u : bancoDeDados) {
            if (u.getEmail().equalsIgnoreCase(emailDigitado)) {
                usuarioEncontrado = u;
                break;
            }
        }

        if (usuarioEncontrado == null) {

        System.out.println("Acesso Negado: E-mail não encontrado no sistema.");
        return null;
        }

        System.out.println("Digite sua senha: ");
        String senhaDigitada = tuba.next();

        if (usuarioEncontrado.getSenha().equals(senhaDigitada)) {
        System.out.println("Autenticação bem-sucedida");
        return usuarioEncontrado;
        } else {
            System.out.println("Acesso negado: senha incorreta.");
            return null;
        }
    }

    public void exibirRelatorioAdmin(Scanner tuba) {
        System.out.println("-------Área restrita: relatório geral --------");

        if ((bancoDeDados.isEmpty())) {
            System.out.println("O sistema está vazio. Não há o que listar.");
            return;
        }

        System.out.print("Credencial de segurança - Digite o seu E-mail de Admin: ");
        String emailDigitado = tuba.next();

        Usuario usuarioEncontrado = null;
        for (Usuario u : bancoDeDados) {
            if (u.getEmail().equalsIgnoreCase(emailDigitado)) {
                usuarioEncontrado = u;
                break;
            }
        }

        if (usuarioEncontrado == null){
            System.out.println("Acesso negado: Utilizador não existe");
            return;
        }

        if (usuarioEncontrado instanceof Administrador) {
            System.out.println("Autorização concedida. Bem-vindo, Administrador ");
            System.out.println("-------lista de cadastros-------");

            for (Usuario u : bancoDeDados) {
                System.out.println("- nome: " + u.getNome() + "| E-mail: " + u.getEmail());
            }
            System.out.println("-------------------------------------------");
        } else {
            System.out.println("'Erro: O utilizador '" + usuarioEncontrado.getNome() + "'Não tem provilégios de Administrador'");
        }
    }
}
