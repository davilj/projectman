package org.cadeau.projectManager.service;

import java.util.List;


import org.cadeau.projectManager.dao.GenericDAOWithJPA;
import org.cadeau.projectManager.domain.Investment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is a service layer interface (actually more of a simple facade). Note
 * that most of these methods are implemented by {@link GenericDAOWithJPA}.
 * 
 * @author Stefan Schmidt
 * @since 0.2
 * 
 */
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public interface InvestmentService {

	List<Investment> findAll();

	@Transactional
	void persist(Investment entity);
}
