describe('Consulta de matrícula de aluno regular', () => {
    it('Deve consultar os dados da matrícula ao informar um número válido', () => {
        cy.request({
            method: 'GET',
            url: 'http://localhost:8080/v1/matriculas/4653421',
            headers: {
                'X-API-KEY': 'unime-qualidade-oficial2'
            }
        }).then((response) => {
            expect(response.status).to.eq(200);
            expect(response.body.matricula).to.eq(4653421);
            expect(response.body.aluno.nome).to.eq('Mariana');
            expect(response.body.curso).to.eq('Sistemas de Informação');
        });
    });
});
