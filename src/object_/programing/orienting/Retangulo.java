package object_.programing.orienting;

public class Retangulo extends FormaGeometrica implements Imprimivel {
    public Retangulo(double base, double altura) {
        super(base, altura);
    }
    @Override
    public double calcularArea() {
        return getDimensao1() * getDimensao2();
    }
    @Override
    public double calcularPerimetro() {
        return 2 * (getDimensao1() + getDimensao2());
    }
    @Override
    public void imprimirResumo() {
        System.out.println("\n=================================");
        System.out.println("🧱 RELATÓRIO DO RETÂNGULO");
        System.out.println("-> Base: " + getDimensao1());
        System.out.println("-> Altura: " + getDimensao2());
        System.out.println("-> Área: " + calcularArea());
        System.out.println("-> Perímetro: " + calcularPerimetro());
        System.out.println("=================================");

    }
}
