package br.com.welson.estoque.util.validacao.cpf;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.welson.estoque.util.validacao.ValidacaoUtil;

public class CpfConstraintValidator implements ConstraintValidator<CPF, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && !value.isEmpty())
            return ValidacaoUtil.validaCpf(value);
        return true;
    }

}
