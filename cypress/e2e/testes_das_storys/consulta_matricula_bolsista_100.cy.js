describe('Consulta de matrícula de aluno bolsista 100%', () => {
    it('Deve consultar os dados da matrícula ao informar um número de matrícula de aluno bolsista 100%', () => {
        cy.request({
            method: 'GET',
            url: 'http://localhost:8080/v1/matriculas/7890123',
            headers: {
                'X-API-KEY': 'unime-qualidade-oficial2'
            }
        }).then((response) => {
            expect(response.status).to.eq(200);
            expect(response.body.matricula).to.eq(7890123);
            expect(response.body.aluno.nome).to.eq('Mariana');
            expect(response.body.curso).to.eq('Sistemas de Informação');
            expect(response.body.mensalidade).to.be.undefined;
        });
    });
});
