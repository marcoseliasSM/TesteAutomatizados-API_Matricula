describe('Consulta de matrícula de aluno com mensalidade atrasada', () => {
    it('Deve retornar mensagem de erro ao informar um número de matrícula com pagamento atrasado', () => {
        cy.request({
            method: 'GET',
            url: 'http://localhost:8080/v1/matriculas/5566778',
            headers: {
                'X-API-KEY': 'unime-qualidade-oficial2'
            },
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(409);
            expect(response.body.mensagem).to.eq('A matrícula informada possui débitos em aberto. Não é possível obter dados da mesma até a quitação!');
        });
    });
});
