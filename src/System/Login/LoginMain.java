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

                    boolean cadastrou = usuarioDAO.cadastroUsuario(nome, emailCadastro, senhaCadastro);

                    if (cadastrou) {
                        System.out.println("Usuário cadastrado");
                    } else {
                    System.out.println("Falha no cadastro. O e-mail já pode estar em uso.");
                    }
                    break;

                case 2:
                    System.out.print("\n--- TELA DE LOGIN ---");
                    System.out.print("E-mail: ");
                    String emailLogin = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senhaLogin = scanner.nextLine();

                    boolean logado = usuarioDAO.verificarLogin(emailLogin, senhaLogin);

                    if (logado) {
                        System.out.print("Login Efetuado com sucesso");
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
}
