// https://docs.cypress.io/api/introduction/api.html




describe("Submit", () => {
  beforeEach(()=>{
    cy.visit('/calculator-form')
    cy.get('#name').type('Karo')
    cy.get('#email').type('karofm')
    cy.get('#message').type('My message')
  })
  it("Test whether submit button is disabled or not when form is validated", () => {
    cy.get('[data-cy="submit"]').should('be.disabled');
    cy.get('#email').type('@ntnu.no')
    cy.get('[data-cy="submit"]').should('not.be.disabled');

  });
  it("Check if the correct response is given when the submit button is clicked",()=>{
    cy.get('#email').type('@ntnu.no')
    cy.get('[data-cy="submit"]').click()

    cy.on('window:alert', (message) => {
      expect(message).to.equal('201: Created');
    });
  })
  
  
});

