package org.cadeau.projectManager.web.converters;


import org.cadeau.projectManager.domain.Product;
import org.cadeau.projectManager.service.ProductService;
import org.springframework.binding.convert.converters.TwoWayConverter;

/**
 * A editor which allows the translation between {@link String} and
 * {@link Product}.
 * 
 * 
 * @author Stefan Schmidt
 * @since 0.3
 * 
 */
public class StringToProduct implements TwoWayConverter {

	private ProductService productService;

	public StringToProduct(ProductService productService) {
		this.productService = productService;
	}	

	public Object convertTargetToSourceClass(Object target, Class sourceClass)
			throws Exception {
		String stringSource = (String) target;
		if (stringSource != null && stringSource.length() > 0) {
			return productService.find(Long.valueOf(stringSource));
		} else {
			return null;
		}

	}

	public Object convertSourceToTargetClass(Object source, Class targetClass)
			throws Exception {
		if(source != null)
			return ((Product) source).toString();
		else return null;
	}

	public Class getSourceClass() {
		return String.class;
	}

	public Class getTargetClass() {
		return Product.class;
	}

}
