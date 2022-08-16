describe('Banco CRUD', () => {
  
  before(() => {
    cy.visit('http://localhost:4200/banco');
  });

  it('Criar banco', () => {
    cy.get("[data-test='banco-open-form']").click();
    cy.get("[data-test='banco-form-codigo']").type("999");
    cy.get("[data-test='banco-form-nome']").type("BANCO TESTE 999");
    cy.get("[data-test='banco-form-tipo']")
    .click()
    .find('ul li > span')
    .contains('Banco de Desenvolvimento')
    .click();
    cy.get("[data-test='banco-form-submit']").click();
    cy.get('.p-dialog-header-icon').click();
  })

  it('Alterar banco', () => {
    //cy.get('[presizablecolumn=""][ng-reflect-field="codigo"]').dblclick();
    cy.get(':nth-child(1) > .text-center > .mr-2').click();
    cy.get("[data-test='banco-form-codigo']").type("{selectall}888");
    cy.get("[data-test='banco-form-nome']").type("{selectall}BANCO TESTE 888");
    cy.get("[data-test='banco-form-tipo']")
    .click()
    .find('ul li > span')
    .contains('Banco Múltiplo')
    .click();
    cy.get("[data-test='banco-form-submit']").click();
    cy.get('.p-dialog-header-icon').click();
  })  

  it('Remover banco', () => {
    //cy.get('[presizablecolumn=""][ng-reflect-field="codigo"]').dblclick();
    cy.get(':nth-child(1) > .text-center > .ml-2').click();
    cy.get('.p-button-danger').click();
  })  

})