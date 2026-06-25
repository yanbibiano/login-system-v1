package System.Login;

import java.util.Scanner;

public class LoginMain {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int opcao = 0;

        while (opcao != 3) {
            System.out.println("\n=== SISTEMA DE AUTENTICAÇÃO ===");
            System.out.println("1. Cadastrar novo Usuário");
            System.out.println("2. Realizar Login");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n--- TELA  DE CADASTRO ---");
                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o e-mail: ");
                    String emailCadastro = scanner.nextLine();
                    System.out.println("Digite a senha: ");
                    String senhaCadastro = scanner.nextLine();

                    if (senhaCadastro.length() < 8) {
                        System.out.println("A senha deve ter no mínimo 8 caracteres");
                        break;
                    }

                    boolean cadastrou = usuarioDAO.cadastroUsuario(nome, emailCadastro, senhaCadastro);

                    if (cadastrou) {
                        System.out.println("Usuário cadastrado");
                    } else {
                        System.out.println("Falha no cadastro. O e-mail já pode estar em uso.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- TELA DE LOGIN ---");
                    System.out.print("E-mail: ");
                    String emailLogin = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senhaLogin = scanner.nextLine();

                    boolean logado = usuarioDAO.verificarLogin(emailLogin, senhaLogin);

                    if (logado) {
                        System.out.print("Login Efetuado com sucesso");
                        abrirAreaLogada(scanner, usuarioDAO, emailLogin);
                    } else {
                        System.out.println("Acesso Negado");
                    }
                    break;

                case 3:
                    System.out.println("Encerrado o sistema");
                    break;
            }
        }
        scanner.close();
    }

    private static void abrirAreaLogada(Scanner scanner, UsuarioDAO usuarioDAO, String emailLogado) {
        String nomeUsuario = usuarioDAO.buscarNomePorEmail(emailLogado);

        int opcaoLogado = 0;

        while (opcaoLogado != 3) {
            System.out.println("\n=================================");
            System.out.println("ÁREA DO USUÁRIO");
            System.out.println("=================================");
            System.out.println("1. Ver Meus Dados");
            System.out.println("2. Alterar Minha Senha");
            System.out.println("3. Fazer Logout (Sair)");
            System.out.println("Escolha uma opção: ");
            opcaoLogado = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoLogado) {
                case 1:
                    System.out.println("\n--- SEUS DADOS ---");
                    System.out.println("Nome: " + nomeUsuario);
                    System.out.println("E-mail: " + emailLogado);
                    break;

                case 2:
                    System.out.println("\n--- ALTERAR SENHA ---");
                    System.out.println("Digite Sua NOVA senha (mínimo 8 caracteres): ");
                    String novaSenha = scanner.nextLine();

                    if (novaSenha.length() < 8) {
                        System.out.println("Erro: A nova senha deve ter no mínimo 8 caracteres");
                        break;
                    }

                    boolean alterou = usuarioDAO.alterarSenha(emailLogado, novaSenha);
                    if (alterou) {
                        System.out.println("Senha alterada com sucesso");
                    } else {
                        System.out.println("Erro ao atualizar a senha.");
                    }
                    break;

                case 3:
                    System.out.println("Fazendo logout... Voltando ao menu principal.");
                    break;

                default:
                    System.out.println("Opção inválida");

            }
        }
    }
}
