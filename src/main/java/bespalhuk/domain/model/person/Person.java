package bespalhuk.domain.model.person;

import bespalhuk.domain.WithVersion;
import bespalhuk.domain.model.Name;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Person extends WithVersion {

	@NotNull(message = "Required.")
	@Column(length = 60)
	private Name name;

	protected Person() {
	}

	public static Person of() {
		return new Person();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			Person other = (Person) obj;
			return Objects.equals(this.name, other.name);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return com.google.common.base.Objects.toStringHelper(this).add("name", name).toString();
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

}
