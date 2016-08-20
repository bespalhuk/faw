package bespalhuk.view.converter.vo;

import bespalhuk.domain.model.Name;
import org.assertj.core.util.Strings;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Name.class)
public class NameConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
		try {
			if (!Strings.isNullOrEmpty(s)) {
				return Name.of(s);
			}
			return null;
		} catch (Exception e) {
			throw new ConverterException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid name.", null));
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
		if (o != null) {
			return String.format("%s", o);
		}
		return null;
	}

}
