var UUID = "";

var CODIGO = 999;
var NOME = "BANCO TESTE 999";
var TIPO = "MULTIPLO";

var CODIGO_ALTERADO = 888;
var NOME_ALTERADO = "BANCO TESTE 888";
var TIPO_ALTERADO = "INVESTIMENTO";

describe('API Banco CRUD', () => {

  it('API - CADASTRA banco', () => {
    
    cy.request({
      method: 'POST',
      url: '/banco',
      form: false,
      body: {
        codigo : CODIGO,
        nome: NOME,
        tipo: TIPO
      },
    }).then(
      (response) => {
        expect(response).property('status').to.equal(200);
        expect(response.body).to.have.property('codigo', CODIGO);
        expect(response.body).to.have.property('nome', NOME);
        expect(response.body).to.have.property('tipo', TIPO);
        const respBody = response.body;
        UUID = respBody['id'];
      }
    );

  }) 

  it('API - CONSULTA por ID', () => {
    console.log("UUID: " + UUID);
    cy.request('GET', '/banco/' + UUID).then(
      (response) => {
        console.log(response.body);
        expect(response).property('status').to.equal(200);
        expect(response.body).to.have.property('codigo', CODIGO);
        expect(response.body).to.have.property('nome', NOME);
        expect(response.body).to.have.property('tipo', TIPO);
      }
    );

  }); 

  it('API - CONSULTA todos os bancos', () => {
    
    cy.request('GET', '/banco').then(
      (response) => {
        expect(response).property('status').to.equal(200);
        expect(response.body.length).eq(9);
      }
    );

  });

  it('API - CONSULTA banco por termo', () => {
    
    cy.request('GET', '/banco?termo-busca=Santander').then(
      (response) => {
        console.log(response.body);
        expect(response).property('status').to.equal(200);
        expect(response.body.length).eq(1);
      }
    );

  }); 

  it('API - ALTERA banco', () => {
    
    cy.request({
      method: 'PUT',
      url: '/banco/' + UUID,
      form: false,
      body: {
        ID : UUID,
        codigo : CODIGO_ALTERADO,
        nome: NOME_ALTERADO,
        tipo: TIPO_ALTERADO
      },
    }).then(
      (response) => {
        expect(response).property('status').to.equal(200);
        expect(response.body).to.have.property('codigo', CODIGO_ALTERADO);
        expect(response.body).to.have.property('nome', NOME_ALTERADO);
        expect(response.body).to.have.property('tipo', TIPO_ALTERADO);
      }
    );

  })
  
  it('API - DELETA banco', () => {
    
    cy.request({
      method: 'DELETE',
      url: '/banco/' + UUID,
      form: false,
      body: {
      },
    }).then(
      (response) => {
        expect(response).property('status').to.equal(200);
      }
    );

  })     
})