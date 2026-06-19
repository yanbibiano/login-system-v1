package teste.primeiro.aprendendo;

import java.io.IOException;
import java.util.Scanner;

public class Calc {
    static void main(String[] args) throws IOException {
        Scanner tuba = new Scanner(System.in);

        System.out.println("Calculadora de Triângulo retangulo");
        System.out.println("seu triangulo retangulo aprece os dois catatos: S - 1 ou N - 2");
        int num = tuba.nextInt();

        if (num == 1) {
            System.out.println("Digite o valor do primeiro cateto");
            double cateto1 = tuba.nextDouble();
            System.out.println("Digite o valor do segundo cateto");
            double cateto2 = tuba.nextDouble();

            double hipotenusa = (cateto1 * cateto1) + (cateto2 * cateto2);

            double raiz = 0;
            double passo = 1;

            for (int casas = 0; casas < 6; casas++) {
                while ((raiz + passo) * (raiz + passo) <= hipotenusa) {
                    raiz += passo;
                }

                passo /= 10;
            }
            System.out.println(raiz);
            System.out.println("Perimetro: " + (raiz + cateto1 + cateto2));
            tuba.close();
        } else if (num == 2) {

            System.out.print("Digite o valor da hipotenusa: ");
            double hipotenusa = tuba.nextDouble();

            System.out.print("Digite o valor do cateto conhecido: ");
            double cateto = tuba.nextDouble();

            double valor = (hipotenusa * hipotenusa) - (cateto * cateto);

            if (valor < 0) {
                System.out.println("Valores inválidos. A hipotenusa deve ser maior que o cateto.");
                return;
            }

            double raiz = 0;
            double passo = 1;

            for (int casas = 0; casas < 10; casas++) {
                while ((raiz + passo) * (raiz + passo) <= valor) {
                    raiz += passo;
                }

                passo /= 10;
            }

            System.out.println("Outro cateto ≈ " + raiz);
            System.out.println("Perimetro: " + (raiz + cateto + hipotenusa));
            tuba.close();
        } else {
            System.out.println("Escolha um valor valido");
        }

    }
}
