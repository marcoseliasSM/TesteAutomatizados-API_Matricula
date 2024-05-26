package br.com.unime.qualidade.oficial2.exception;

import br.com.unime.qualidade.oficial2.enums.RegistrationBusinessMessagesEnum;

public class RegistrationPaidDelayedException extends Exception {
    public RegistrationPaidDelayedException() {
        super(RegistrationBusinessMessagesEnum.PAYMENT_DELAYED.getMessage());
    }
}
