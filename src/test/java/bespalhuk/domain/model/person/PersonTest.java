package bespalhuk.domain.model.person;

import bespalhuk.data.provider.PersonDataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonTest {

	@Test
	public void equals() {
		assertThat(PersonDataProvider.boca()).isEqualTo(PersonDataProvider.boca());
		assertThat(PersonDataProvider.ricardo()).isNotEqualTo(PersonDataProvider.bespalhuk());
	}

}
