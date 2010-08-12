package org.cadeau.projectManager.web.editors;

import java.beans.PropertyEditorSupport;


import org.cadeau.projectManager.domain.Product;
import org.cadeau.projectManager.service.ProductService;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * A editor which allows the translation between {@link String} and
 * {@link Product}.
 * 
 * Needs to be {@link Configurable} to allow for injection of {@link Autowired}
 * fields such as {@link ProductService}.
 * 
 * @author Stefan Schmidt
 * @since 0.2
 * 
 */
@Configurable
public class ProductEditor extends PropertyEditorSupport {

	private SimpleTypeConverter typeConverter = new SimpleTypeConverter();

	private ProductService productService;

	public ProductEditor(ProductService productService) {
		super();
		this.productService = productService;
	}

	public String getAsText() {
		Object obj = getValue();
		if (obj == null) {
			return null;
		}
		return (String) typeConverter.convertIfNecessary(((Product) obj).getId(), String.class);
	}

	public void setAsText(String text) {
		if (text == null || text.length() == 0) {
			setValue(null);
			return;
		}

		Long identifier = (Long) typeConverter.convertIfNecessary(text, Long.class);
		if (identifier == null) {
			setValue(null);
			return;
		}

		setValue(productService.find(identifier));
	}
}
