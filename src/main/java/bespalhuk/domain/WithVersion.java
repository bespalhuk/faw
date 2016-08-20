package bespalhuk.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class WithVersion extends WithoutVersion {

	@Version
	Integer version;

}
