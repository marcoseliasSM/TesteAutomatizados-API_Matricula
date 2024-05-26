package br.com.unime.qualidade.oficial2.exception;

import br.com.unime.qualidade.oficial2.enums.RegistrationBusinessMessagesEnum;

public class RegistrationInvalidException extends Exception {
    public RegistrationInvalidException() {
        super(RegistrationBusinessMessagesEnum.INVALID.getMessage());
    }
}
