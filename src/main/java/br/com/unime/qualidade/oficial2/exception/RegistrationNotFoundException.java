package br.com.unime.qualidade.oficial2.exception;

import br.com.unime.qualidade.oficial2.enums.RegistrationBusinessMessagesEnum;

public class RegistrationNotFoundException extends Exception {
    public RegistrationNotFoundException() {
        super(RegistrationBusinessMessagesEnum.NOT_FOUND.getMessage());
    }
}
