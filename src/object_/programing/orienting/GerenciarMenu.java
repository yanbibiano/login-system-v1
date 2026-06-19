package object_.programing.orienting;

import java.util.Scanner;

public class GerenciarMenu {
    public FormaGeometrica escolherEcriarForma(Scanner tuba) {
        System.out.println("Escolha o calculo desejado:");
        System.out.println("[ 1 ] Triângulo retangulo");
        System.out.println("[ 2 ] Retangulo");
        int geo = tuba.nextInt();

        if (geo == 1) {
            System.out.println("----------Calculadora de triâgulo retangulo----------");
            System.out.println("Adicione o primeiro cateto: ");
            double cateto1Digitado = tuba.nextDouble();

            System.out.println("Adicione o segundo cateto: ");
            double cateto2Digitado = tuba.nextDouble();

            return new TrianguloRetangulo(cateto1Digitado, cateto2Digitado);

        } else if (geo == 2) {

            System.out.println("------------------------------------------------------");
            System.out.println("Calculo do retangulo");

            System.out.println("Digite a base: ");
            double base1 = tuba.nextDouble();

            System.out.println("Digite a  altura: ");
            double altura1 = tuba.nextDouble();

            return new Retangulo(base1, altura1);

        } else {
            System.out.println("ERRO!");
            return null;
        }
    }
}
