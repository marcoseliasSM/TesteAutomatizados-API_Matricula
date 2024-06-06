# Oficial 2 - Matrículas API

# Descrição
Este repositório contém os testes automatizados para a API de matrículas. Os testes aqui presentes fazem parte do requisito de avaliação da Oficial II da disciplina de Gerenciamento e Qualidade de Software do curso de Sistemas de Informação da UNIME de Lauro de Freitas. Este projeto tem como objetivo validar e garantir a qualidade das funcionalidades relacionadas à matrícula de alunos na aplicação.

# Requisitos de Instalação
Certifique-se de ter as seguintes ferramentas e dependências instaladas em seu ambiente de desenvolvimento:
- Node.js 
- Cypress
- IntelliJ IDEA

# Observação
- Os teste automatizados estão disponíveis no seguinte caminho ‘\oficial2-matriculas-api\cypress\e2e\testes_das_storys’ com os seguintes arquivos:
- consulta_matricula_atrasada.cy.js
- consulta_matricula_bolsista_50.cy.js
- consulta_matricula_bolsista_100.cy.js
- consulta_matricula_excluida.cy.js
- consulta_matricula_invalida.cy.js
- consulta_matricula_quitadas.cy.js
- consulta_matricula_regular.cy.js

# Executar os testes
- Clonar repositório:
git clone https://github.com/seu-usuario/seu-repositorio.git
- Instalar as dependências do projeto, importante ressaltar para executar o comando é necessário está no diretorio do projeto:
npm install
- Abrir o Cypress:
 npx cypress open

# Bugs e Relatório de Erros
  - Os prints dos erros encontrados durante os testes estão localizados no diretório oficial2-matriculas-api\PrintERRO

  - Os relatórios de bugs estão disponíveis no arquivo oficial2-matriculas-api\BugReport\BugReport.xlsx
