package teste.primeiro.aprendendo;

import java.util.Scanner;

public class Aula02Tiposprimitivos {
    public static void main(String[] args){
        Scanner teste = new Scanner(System.in);
        Scanner valor = new Scanner(System.in);
        System.out.println("Digite seu nome: ");
        String entrada = teste.nextLine();
        System.out.println("Digite um valor: ");
        int n1 = valor.nextInt();
        System.out.println("Olá " + entrada + "!, seu numero é: " + n1);
    }
}
