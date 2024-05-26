package br.com.unime.qualidade.oficial2.exception;

import br.com.unime.qualidade.oficial2.enums.RegistrationBusinessMessagesEnum;

public class RegistrationDeletedException extends Exception {
    public RegistrationDeletedException() {
        super(RegistrationBusinessMessagesEnum.DELETED.getMessage());
    }
}
