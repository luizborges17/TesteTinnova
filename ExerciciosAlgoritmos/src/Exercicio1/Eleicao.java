package Exercicio1;

public class Eleicao {

    private final int totalEleitores = 1000;
    private final int votosValidos = 800;
    private final int votosBrancos = 150;
    private final int votosNulos = 50;

    public Eleicao() {
    }

    public double calculaPercentualVotosValidos() {
        return ((votosValidos / (double) totalEleitores) * 100);
    }

    public double calculaPercentualVotosBrancos() {
        return ((votosBrancos / (double) totalEleitores) * 100);
    }

    public double calculaPercentualVotosNulos() {
        return ((votosNulos / (double) totalEleitores) * 100);
    }

    public static void main(String[] args) {
        Eleicao eleicao = new Eleicao();
        System.out.println(eleicao.calculaPercentualVotosValidos());
        System.out.println(eleicao.calculaPercentualVotosBrancos());
        System.out.println(eleicao.calculaPercentualVotosNulos());
    }

}
