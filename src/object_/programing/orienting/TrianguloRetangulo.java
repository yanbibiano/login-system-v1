package object_.programing.orienting;

public class TrianguloRetangulo extends FormaGeometrica implements Imprimivel {
    public TrianguloRetangulo(double cateto1, double cateto2) {
        super(cateto1, cateto2);
    }

    public double calcularHipotenusa() {
        return Math.sqrt(
                Math.pow(getDimensao1(), 2) + Math.pow(getDimensao2(), 2)
        );
    }
    @Override
    public double calcularPerimetro() {
        return getDimensao1() + getDimensao2() + calcularHipotenusa();
    }
    @Override
    public double calcularArea() {
        return (getDimensao1() * getDimensao2()) / 2;
    }
    @Override
    public void imprimirResumo() {
        System.out.println("\n=================================");
        System.out.println("📐 RELATÓRIO DO TRIÂNGULO RETÂNGULO");
        System.out.println("-> Cateto 1: " + getDimensao1());
        System.out.println("-> Cateto 2: " + getDimensao2());
        System.out.println("-> Hipotenusa: " + calcularHipotenusa());
        System.out.println("-> Área: " + calcularArea());
        System.out.println("-> Perímetro: " + calcularPerimetro());
        System.out.println("=================================");
    }
}
