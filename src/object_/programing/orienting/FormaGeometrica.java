package object_.programing.orienting;

public abstract class FormaGeometrica {
    private double dimensao1;
    private double dimensao2;

    public FormaGeometrica(double d1, double d2){
        setDimensao1(d1);
        setDimensao2(d2);
    }

    public void setDimensao1(double valor) {
        if (valor > 0){
            this.dimensao1 = valor;
        } else {
            System.out.println("ERRO! Digite um número maior que 0");
        }

    }

    public double getDimensao1() {
        return this.dimensao1;
    }

    public void setDimensao2(double valor) {
        if (valor > 0) {
            this.dimensao2 = valor;
        } else {
            System.out.println("ERRO! Digite um número maior que 0");
        }
    }

    public double getDimensao2() {
        return this.dimensao2;
    }


    public abstract double calcularArea();
    public abstract double calcularPerimetro();
}
