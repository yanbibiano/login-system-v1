package System.Login;

import java.util.Scanner;

public class LoginMain {
    public static void main(String[] args) {
        Scanner tuba = new Scanner(System.in);
        GerenciadorLogin sistema = new GerenciadorLogin();
        boolean servidorRodando = true;

        System.out.println("Tentando conectar ao MySQL...");
        java.sql.Connection conexaoTeste = FabricaConexao.obterConexao();
        System.out.println("Conexão estabelecida com sucesso absoluto!");

        System.out.println("Bem-vindo ao Sistema de Gestão V1.0");

        while (servidorRodando) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("[ 1 ] Cadastrar Novo Utilizador");
            System.out.println("[ 2 ] Fazer Login");
            System.out.println("[ 3 ] Ver relatório geral (Requer admin) ");
            System.out.println("[ 4 ] Desligar Servidor");
            System.out.print("Escolha uma ação: ");

            try {
                int acao = tuba.nextInt();

                if (acao == 1) {
                    sistema.cadastrarUsuario(tuba);

                } else if (acao == 2) {
                    Usuario usuarioLogado = sistema.fazerLogin(tuba);

                    if (usuarioLogado != null) {
                        System.out.println("\n------------------------------------");
                        System.out.println("LOGIN EFETUADO COM SUCESSO");

                        usuarioLogado.exibirPainel();

                        if (usuarioLogado instanceof Registravel) {
                            Registravel auditado = (Registravel) usuarioLogado;
                            auditado.registrarAcesso();
                        }
                        System.out.println("------------------------------------");
                    }

                } else if (acao == 3){
                    sistema.exibirRelatorioAdmin(tuba);

                } else if (acao == 4) {
                    servidorRodando = false;
                    System.out.println("A desligar o servidor... Até logo!");
                } else {
                    System.out.println("Opção desconhecida.");
                }

            } catch (Exception e) {
                System.out.println("ERRO: Por favor, digite apenas números no menu.");
                tuba.next();
            }
        }

        tuba.close();
    }
}
