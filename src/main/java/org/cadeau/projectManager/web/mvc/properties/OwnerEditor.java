package org.cadeau.projectManager.web.mvc.properties;

import java.beans.PropertyEditorSupport;

import org.cadeau.projectManager.domain.Person;
import org.cadeau.projectManager.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

public class OwnerEditor extends PropertyEditorSupport {
  @Autowired
  private PersonService personService;

  @Override
  public void setAsText(String idAsString) throws IllegalArgumentException {
    System.err.println("Find the owner for " + idAsString);
    try {
      Long id = new Long(idAsString);
      Person person = personService.find(id);
      System.err.println("Find the following owner: " + person);
      setValue(person);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("Could not map " + idAsString + " to an owner: " + e.getMessage());
    }
  }

  public PersonService getPersonService() {
    return personService;
  }

  public void setPersonService(PersonService personService) {
    this.personService = personService;
  }

  
  

}
