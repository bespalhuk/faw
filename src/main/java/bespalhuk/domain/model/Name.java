package bespalhuk.domain.model;

import com.google.common.collect.ComparisonChain;
import org.assertj.core.util.Strings;

import java.io.IOException;
import java.io.Serializable;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.text.Normalizer.Form;
import static java.text.Normalizer.normalize;

public class Name implements Serializable, Comparable<Name>, Formattable {

	private final String value;

	private Name(String value) {
		this.value = value;
	}

	public static Name of(String value) {
		checkArgument(!Strings.isNullOrEmpty(value));
		return new Name(normalize(value.trim(), Form.NFD).replaceAll("[^\\p{ASCII}]", ""));
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Name) {
			Name other = (Name) obj;
			return Objects.equals(this.value, other.value);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public int compareTo(Name o) {
		return ComparisonChain.start().compare(this.value, o.value).result();
	}

	@Override
	public void formatTo(Formatter formatter, int flags, int width, int precision) {
		StringBuilder sb = new StringBuilder();
		sb.append(value);
		try {
			formatter.out().append(sb.toString());
		} catch (IOException e) {
			throw new IllegalArgumentException("Couldn't format value: " + value);
		}
	}

}
