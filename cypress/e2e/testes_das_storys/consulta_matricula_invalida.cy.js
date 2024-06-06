describe('Consulta de matrícula inválida', () => {
    it('Deve retornar mensagem de erro ao informar um número de matrícula inválida', () => {
        cy.request({
            method: 'GET',
            url: 'http://localhost:8080/v1/matriculas/99',
            headers: {
                'X-API-KEY': 'unime-qualidade-oficial2'
            },
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(400);
            expect(response.body.mensagem).to.eq('A matrícula informada não parece ser válida!');
        });
    });
});
