package br.com.unime.qualidade.oficial2.enums;

public enum Status {
    EM_DIA(),
    BOLSISTA_100(),
    CONTRATO_QUITADO(),
    PAGAMENTO_EM_ATRASO(),
    MATRICULA_EXCLUIDA();

    private String status;

    Status() {
    }
}
