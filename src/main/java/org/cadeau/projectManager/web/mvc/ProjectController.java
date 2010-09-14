package org.cadeau.projectManager.web.mvc;

import java.security.Principal;

import javax.validation.Validation;
import javax.validation.Validator;

import org.cadeau.projectManager.domain.Comment;
import org.cadeau.projectManager.domain.Person;
import org.cadeau.projectManager.domain.Project;
import org.cadeau.projectManager.service.PersonService;
import org.cadeau.projectManager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/project/**")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private PersonService personService;

	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public String list(ModelMap modelMap) {
	  
	  User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  Person person = personService.findByEmail(principal.getUsername());
	  modelMap.addAttribute("projects", projectService.findMyProjects(person));
		return "project/list";
	}

	@RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, ModelMap modelMap) {
		Assert.notNull(id, "Identifier must be provided.");
		modelMap.addAttribute("project", projectService.find(id));
		modelMap.addAttribute("comment", new Comment());
		return "project/show";
	}

	@RequestMapping(value = "/project/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id, ModelMap modelMap) {
		Assert.notNull(id, "Identifier must be provided.");
		try {
			projectService.remove(projectService.find(id));
		} catch (DataIntegrityViolationException e) {
			modelMap.addAttribute("integrityViolation", "Could not delete ");
		}
		return "redirect:/project";
	}
	
	@RequestMapping(value = "/project/form", method = RequestMethod.GET)
  public String form(ModelMap modelMap) {
    modelMap.addAttribute("project", new Project());
    return "project/create";
  }
	
	@RequestMapping(value = "/project/{id}/comment", method = RequestMethod.POST)
  public String createComment(@PathVariable("id") Long id, @ModelAttribute("comment") Comment comment, BindingResult result, ModelMap modelMap) {
	  Project project = projectService.find(id);
	  projectService.addComment(project, comment);
	  
	  modelMap.addAttribute("project", project);
	  //clear current comment
	  modelMap.addAttribute("comment", new Comment());
	  return "project/show";
  }
	
	@RequestMapping(value = "/project", method = RequestMethod.POST)
  public String create(@ModelAttribute("project")  Project project, BindingResult result) {
    Assert.notNull(project, "Project can not be null.");
    
    projectService.persist(project);
    return "redirect:/project/" + project.getId();
  }

	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		
	}
}
