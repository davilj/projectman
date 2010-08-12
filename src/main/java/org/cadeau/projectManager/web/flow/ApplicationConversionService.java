package org.cadeau.projectManager.web.flow;


import org.cadeau.projectManager.service.PersonService;
import org.cadeau.projectManager.service.ProductService;
import org.cadeau.projectManager.web.converters.LongToPerson;
import org.cadeau.projectManager.web.converters.LongToProduct;
import org.cadeau.projectManager.web.converters.StringToPerson;
import org.cadeau.projectManager.web.converters.StringToProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.convert.converters.StringToDate;
import org.springframework.binding.convert.service.DefaultConversionService;
import org.springframework.stereotype.Component;

/**
 * Conversion service registration point for Spring Web Flow.
 * 
 * @author Stefan Schmidt
 * @since 0.3
 *
 */
@Component("conversionService")
public class ApplicationConversionService extends DefaultConversionService {
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ProductService productService;

    @Override
    protected void addDefaultConverters() {
		super.addDefaultConverters();
		StringToDate dateConverter = new StringToDate();
		dateConverter.setPattern("MM/dd/yyyy");
		addConverter("shortDate", dateConverter);
		addConverter(new LongToPerson(personService));
		addConverter(new StringToProduct(productService));
		addConverter(new StringToPerson(personService));
		addConverter(new LongToProduct(productService));
    }

}