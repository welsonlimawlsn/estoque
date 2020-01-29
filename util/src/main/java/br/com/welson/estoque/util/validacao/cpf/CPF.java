package br.com.welson.estoque.util.validacao.cpf;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CpfConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CPF {

    String message() default "Número de CPF inválido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
