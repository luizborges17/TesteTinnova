package Exercicio3;

import java.math.BigInteger;

public class Fatorial {

    public static BigInteger calcularFatorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Numero não natural");
        }

        BigInteger fatorial = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            fatorial = fatorial.multiply(BigInteger.valueOf(i));
        }

        return fatorial;
    }


    public static void main(String[] args) {
        System.out.println(calcularFatorial(500));
    }
}
