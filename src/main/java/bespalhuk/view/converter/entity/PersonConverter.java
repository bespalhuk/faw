package bespalhuk.view.converter.entity;

import bespalhuk.domain.model.person.Person;
import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.util.UUID;

@FacesConverter(forClass = Person.class)
public class PersonConverter implements Converter {

	private TypeToken<Person> typeToken = new TypeToken<Person>(getClass()) {
		private static final long serialVersionUID = 1L;
	};

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
		if (!Strings.isNullOrEmpty(s)) {
			try {
				return getConverterHelper(facesContext).find(typeToken.getRawType(), UUID.fromString(s));
			} catch (Exception ex) {
				throw new ConverterException("Couldn't convert to entity", ex);
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
		if (o != null) {
			try {
				return getConverterHelper(facesContext).getIdAsString(o);
			} catch (Exception ex) {
				throw new ConverterException("Couldn't get entity's id.", ex);
			}
		}
		return null;
	}

	private EntityConverterHelper getConverterHelper(FacesContext context) {
		WebApplicationContext applicationContext = FacesContextUtils.getRequiredWebApplicationContext(context);
		return applicationContext.getBean(EntityConverterHelper.class);
	}

}
