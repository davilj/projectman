package org.cadeau.projectManager.web.converters;


import org.cadeau.projectManager.domain.Product;
import org.cadeau.projectManager.service.ProductService;
import org.springframework.binding.convert.converters.TwoWayConverter;

/**
 * A editor which allows the translation between {@link Long} and
 * {@link Product}.
 * 
 * 
 * @author Stefan Schmidt
 * @since 0.3
 * 
 */
public class LongToProduct implements TwoWayConverter {

	private ProductService productService;

	public LongToProduct(ProductService productService) {
		this.productService = productService;
	}	

	public Object convertTargetToSourceClass(Object target, Class sourceClass)
			throws Exception {
		Product person = (Product) target;
		return person.toString();
	}

	public Object convertSourceToTargetClass(Object source, Class targetClass)
			throws Exception {
		Long longSource = (Long) source;
		if (longSource != null && longSource > 0) {
			return productService.find(longSource);
		} else {
			return null;
		}
	}

	public Class getSourceClass() {
		return Long.class;
	}

	public Class getTargetClass() {
		return Product.class;
	}

}
