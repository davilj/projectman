package org.cadeau.projectManager.web.mvc;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;


import org.cadeau.projectManager.domain.Account;
import org.cadeau.projectManager.domain.Investment;
import org.cadeau.projectManager.domain.Person;
import org.cadeau.projectManager.domain.Product;
import org.cadeau.projectManager.service.AccountService;
import org.cadeau.projectManager.service.InvestmentService;
import org.cadeau.projectManager.service.PersonService;
import org.cadeau.projectManager.service.ProductService;
import org.cadeau.projectManager.web.editors.PersonEditor;
import org.cadeau.projectManager.web.editors.ProductEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This is a Controller for the {@link Account} domain object. It demonstrates
 * the use of the new Spring 3.0 REST support.
 * 
 * @author Stefan Schmidt
 * @since 0.2
 * 
 */
@Controller
@RequestMapping("/account/**")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private PersonService personService;

	@Autowired
	private InvestmentService investmentService;

	@Autowired
	private ProductService productService;

	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String list(ModelMap modelMap) {
		modelMap.addAttribute("accounts", accountService.findAll());
		return "account/list";
	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, ModelMap modelMap) {
		Assert.notNull(id, "Identifier must be provided.");
		modelMap.addAttribute("account", accountService.find(id));
		modelMap.addAttribute("investment", new Investment());
		modelMap.addAttribute("products", productService.findAll());
		return "account/show";
	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {
		Assert.notNull(id, "Identifier must be provided.");
		accountService.remove(accountService.find(id));
		return "redirect:/account";
	}

	@RequestMapping(value = "/account/form", method = RequestMethod.GET)
	public String form(ModelMap modelMap) {
		modelMap.addAttribute("people", personService.findAll());
		modelMap.addAttribute("account", new Account());
		return "account/create";
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String create(@ModelAttribute("account") Account account, BindingResult result) {
		Assert.notNull(account, "Account must be provided.");
		for (ConstraintViolation<Account> constraint : validator.validate(account)) {
			result.rejectValue(constraint.getPropertyPath().toString(), "", constraint.getMessage());
		}
		if (result.hasErrors())
			return "account/create";
		accountService.persist(account);
		return "redirect:/account/" + account.getId();
	}

	@RequestMapping(value = "/account/{id}/form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, ModelMap modelMap) {
		Assert.notNull(id, "Identifier must be provided.");
		modelMap.addAttribute("people", personService.findAll());
		modelMap.addAttribute("investments", investmentService.findAll());
		modelMap.addAttribute("account", accountService.find(id));
		return "account/update";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@ModelAttribute("account") Account account, BindingResult result) {
		Assert.notNull(account, "Account must be provided.");
		for (ConstraintViolation<Account> constraint : validator.validate(account)) {
			result.rejectValue(constraint.getPropertyPath().toString(), "", constraint.getMessage());
		}
		if (result.hasErrors())
			return "account/update";
		accountService.merge(account);
		return "redirect:/account/" + account.getId();
	}

	@RequestMapping(value = "/{accountId}/investment", method = RequestMethod.POST)
	public String create(@PathVariable("accountId") Long accountId, @ModelAttribute("investment") Investment investment) {
		Assert.notNull(investment, "Investment must be provided.");
		Assert.notNull(accountId, "Account ID be provided.");
		investmentService.persist(investment);
		Account account = accountService.find(accountId);
		account.addInvestment(investment);
		accountService.merge(account);
		return "redirect:/account/" + accountId;
	}

	@InitBinder
	void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), false));
		binder.registerCustomEditor(Product.class, new ProductEditor(productService));
		binder.registerCustomEditor(Person.class, new PersonEditor(personService));
	}
}
