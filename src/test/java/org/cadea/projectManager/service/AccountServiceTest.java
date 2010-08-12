package org.cadea.projectManager.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.cadeau.projectManager.domain.Account;
import org.cadeau.projectManager.domain.Investment;
import org.cadeau.projectManager.domain.Loan;
import org.cadeau.projectManager.domain.Person;
import org.cadeau.projectManager.service.AccountService;
import org.cadeau.projectManager.service.InvestmentService;
import org.cadeau.projectManager.service.PersonService;
import org.cadeau.projectManager.service.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * A simple integration test for {@link AccountService}.
 * 
 * @author Stefan Schmidt
 * @since 0.2
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager")
@ContextConfiguration(locations = { "classpath:ProjectManager-test.xml" })
public class AccountServiceTest {

	@Autowired
	private AccountService accountService;

	@Autowired
	private ProductService productService;

	@Autowired
	private PersonService personService;
	
	@Autowired
	private InvestmentService investmentService;

	private Account account;

	@Before
	public void setup() {
	    Authentication authRequest = new UsernamePasswordAuthenticationToken("ignored", "ignored", AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
	    SecurityContextHolder.getContext().setAuthentication(authRequest);

		account = DataSeeder.generateAccount();

		for (Person person : account.getOwners())
			personService.persist(person);

		personService.persist(account.getManager());

		for (Investment investment : account.getInvestments()) {
			if (investment.getProduct() instanceof Loan) {
				for (Person person : ((Loan) investment.getProduct()).getLenders()) {
					personService.persist(person);
				}
			}
			productService.persist(investment.getProduct());
		}
	}

	@Test
	@Rollback
	public void testFindAll() {
		accountService.persist(account);
		assertEquals(1, accountService.findAll().size());
	}

	@Test
	@Rollback
	public void testPersist() {
		accountService.persist(account);
		assertEquals("Homer Simpson House Loan", accountService.findAll().get(0).getName());
	}

	@Test
	@Rollback
	public void testMerge() {
		accountService.persist(account);
		long id = account.getId();
		Account renamedAccount = accountService.find(id);
		renamedAccount.setName("New Name");
		accountService.merge(renamedAccount);
		assertEquals("New Name", accountService.find(id).getName());
	}

	@Test
	@Rollback
	public void testRemove() {
		accountService.persist(account);
		long id = account.getId();
		accountService.remove(accountService.find(id));
		assertEquals(0, accountService.findAll().size());
	}

	@Test
	@Rollback
	public void testFind() {
		accountService.persist(account);
		long id = account.getId();
		assertNotNull(accountService.find(id));
	}
	
	
	@Test
  @Rollback
  public void testArray() {
    accountService.persist(account);
    long id = account.getId();
    Account renamedAccount = accountService.find(id);
    renamedAccount.setName("TestArray");
    assertEquals(2,renamedAccount.getInvestments().size());
    
    Investment inv = new Investment();
    
    investmentService.persist(inv);
    renamedAccount.addInvestment(inv);
    
    accountService.merge(renamedAccount);
    assertEquals("TestArray", accountService.find(id).getName());
    assertEquals(3,renamedAccount.getInvestments().size());
    
  }
	
	
	@After
	public void tearDown(){
		SecurityContextHolder.clearContext();
	}
}
