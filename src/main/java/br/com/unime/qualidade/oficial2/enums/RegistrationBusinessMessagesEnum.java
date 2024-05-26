package br.com.unime.qualidade.oficial2.enums;

public enum RegistrationBusinessMessagesEnum {

    PAYMENT_DELAYED("A matrícula informada possui débitos em aberto. Não é possível obter dados da mesma até a quitação!"),
    NOT_FOUND("A matrícula informada não foi localizada em nossa base de dados!"),
    DELETED("A matrícula informada foi excluída de nossa base de dados!"),
    INVALID("A matrícula informada não parece ser válida!");

    private final String message;

    RegistrationBusinessMessagesEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
