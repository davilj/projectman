package org.cadeau.projectManager.service.impl;



import javax.annotation.PostConstruct;

import org.cadeau.projectManager.dao.GenericDAOWithJPA;
import org.cadeau.projectManager.security.Authorities;
import org.cadeau.projectManager.service.AuthorityService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * This is a service layer implementation for {@link AuthorityService}. Note that
 * most of these methods from the interface are implemented by
 * {@link GenericDAOWithJPA}.
 * 
 * This class also provides the functionality of the repository. More
 * information about this in my blog article entitled 'Spring Finance > Part 3:
 * DDD, JPA & Transactions' published at StSMedia.net
 * 
 * @author Stefan Schmidt
 * @since 0.2
 * 
 */
@Service("authorityService")
@Repository
public class AuthorityServiceImpl extends GenericDAOWithJPA<Authorities, Long> implements AuthorityService {

	@PostConstruct
	public void initialize() throws Exception {
		Authorities authorities = new Authorities();
		authorities.setUsername("admin@email.com");
		authorities.setAuthority("ROLE_ADMIN");
		super.persist(authorities);		
	}
}
