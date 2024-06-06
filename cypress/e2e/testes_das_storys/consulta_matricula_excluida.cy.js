describe('Consulta de matrícula excluída', () => {
    it('Deve retornar mensagem de erro ao informar um número de matrícula excluída', () => {
        cy.request({
            method: 'GET',
            url: 'http://localhost:8080/v1/matriculas/4653499',
            headers: {
                'X-API-KEY': 'unime-qualidade-oficial2'
            },
            failOnStatusCode: false
        }).then((response) => {
            expect(response.status).to.eq(404);
            expect(response.body.mensagem).to.eq('A matrícula informada foi excluída de nossa base de dados!');
        });
    });
});
