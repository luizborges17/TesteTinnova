package Exercicio4;

public class SomaDosMultiplos {

    public static int calcularSomaMultiplos(int x) {
        int soma = 0;

        for (int i = 1; i <= x; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                soma += i;
            }
        }

        return soma;
    }

}
