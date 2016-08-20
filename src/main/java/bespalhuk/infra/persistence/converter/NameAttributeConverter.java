package bespalhuk.infra.persistence.converter;

import bespalhuk.domain.model.Name;
import org.assertj.core.util.Strings;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class NameAttributeConverter implements AttributeConverter<Name, String> {

	@Override
	public String convertToDatabaseColumn(Name name) {
		return name.toString();
	}

	@Override
	public Name convertToEntityAttribute(String s) {
		if (!Strings.isNullOrEmpty(s)) {
			return Name.of(s);
		}
		return null;
	}

}
