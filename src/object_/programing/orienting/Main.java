package object_.programing.orienting;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner tuba = new Scanner(System.in);
        GerenciarMenu menu = new GerenciarMenu();
        boolean rodando = true;

        while (rodando) {
            System.out.println("=== Sistema geométrico interativo ===");

            FormaGeometrica formaEscolhida = menu.escolherEcriarForma(tuba);

            if (formaEscolhida !=null) {

                if (formaEscolhida instanceof Imprimivel) {
                    Imprimivel objetoComImpressao = (Imprimivel) formaEscolhida;
                    objetoComImpressao.imprimirResumo();
                }
            }
            System.out.println("\n==============================================");
            System.out.println("O que deseja fazer agora? ");
            System.out.println("[ 1 ] Reiniciar o programa (Calcular outro)");
            System.out.println("[ 2 ] Fechar o programa");
            System.out.print("Sua escolha: ");
            int escolha = tuba.nextInt();

            if (escolha == 2) {
                System.out.println(" Encerrando");

                rodando = false;
            } else {
                System.out.println(" Reiniciando o Sistema");
            }
        }
        tuba.close();

    }
}