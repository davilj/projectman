package org.cadeau.projectManager.service.impl;


import org.cadeau.projectManager.dao.GenericDAOWithJPA;
import org.cadeau.projectManager.domain.Product;
import org.cadeau.projectManager.service.ProductService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * This is a service layer implementation for {@link ProductService}. Note that
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
@Service("productService")
@Repository
public class ProductServiceImpl extends GenericDAOWithJPA<Product, Long> implements ProductService {

}
