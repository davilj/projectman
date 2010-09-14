package org.cadeau.projectManager.service.impl;

import java.util.List;

import org.cadeau.projectManager.dao.GenericDAOWithJPA;
import org.cadeau.projectManager.domain.Person;
import org.cadeau.projectManager.service.PersonService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * This is a service layer implementation for {@link PersonService}. Note that
 * most of these methods from the interface are implemented by
 * {@link GenericDAOWithJPA}.
 * 
 * The findByLastName method has been added to demonstrate how a custom method
 * is implemented in {@link PersonServiceImpl}.
 * 
 * This class also provides the functionality of the repository. More
 * information about this in my blog article entitled 'Spring Finance > Part 3:
 * DDD, JPA & Transactions' published at StSMedia.net
 * 
 * @author Stefan Schmidt
 * @since 0.1
 * 
 */
@Service("personService")
@Repository
public class PersonServiceImpl extends GenericDAOWithJPA<Person, Long> implements PersonService {

	// custom repository method
	public List<Person> findByLastName(String lastName) {
		return super.entityManager.createQuery("Select p from Person p where p.lastName = :lastName").setParameter("lastName", lastName).getResultList();
	}

  @Override
  public Person findByEmail(String email) {
    List<Person> listOfPerson = super.entityManager.createQuery("Select p from Person p where p.email = :email").setParameter("email", email).getResultList();
    if (listOfPerson.size()!=1) {
      System.err.println(listOfPerson.size() + " result for " + email);
      for (Person person : listOfPerson) {
        System.err.println(person);
      }
    }
    return listOfPerson.size()==0?Person.emptyPerson:listOfPerson.get(0);
  }
}
