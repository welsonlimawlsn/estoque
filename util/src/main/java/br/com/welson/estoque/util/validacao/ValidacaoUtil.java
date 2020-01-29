package br.com.welson.estoque.util.validacao;

import static java.lang.Character.getNumericValue;
import static java.lang.Integer.parseInt;

public class ValidacaoUtil {

    private ValidacaoUtil() {
    }

    public static boolean validaCpf(String cpf) {
        cpf = cpf.replace(".", "").replace("-", "");
        if (cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            return false;
        }
        int primeiroDigito = calculaDigitoMod11(cpf, 10, 2);
        int segundoDigito = calculaDigitoMod11(cpf, 11, 2);
        return primeiroDigito == parseInt(cpf.substring(9, 10)) && segundoDigito == parseInt(cpf.substring(10, 11));
    }

    private static int calculaDigitoMod11(String s, int inicioContador, int fimContador) {
        char[] caracteres = s.toCharArray();
        int index = 0;
        int soma = 0;
        for (int i = inicioContador; i >= fimContador; i--) {
            soma += getNumericValue(caracteres[index++]) * i;
        }
        int resto = soma % 11;
        return resto <= 1 ? 0 : 11 - resto;
    }

}
