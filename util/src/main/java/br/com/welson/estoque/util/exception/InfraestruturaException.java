package br.com.welson.estoque.util.exception;

public class InfraestruturaException extends Exception {

    public InfraestruturaException(String message) {
        super(message);
    }

    public InfraestruturaException(String message, Throwable cause) {
        super(message, cause);
    }

    public InfraestruturaException(Throwable cause) {
        super(cause);
    }

    public InfraestruturaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
