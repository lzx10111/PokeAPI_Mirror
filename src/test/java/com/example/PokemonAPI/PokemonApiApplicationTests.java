package com.example.PokemonAPI;

import com.example.PokemonAPI.util.ValidMethods;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PokemonApiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testIntegerPositive() {
		assertThat(ValidMethods.isPositiveInteger("999999999", false, false)).isEqualTo(true);
		assertThat(ValidMethods.isPositiveInteger("9999999999", false, false)).isEqualTo(false);
		assertThat(ValidMethods.isPositiveInteger("+999999999", true, false)).isEqualTo(true);
	}

	@Test
	void testDateHtmlValidator() {
		assertThat(ValidMethods.isDateHtml("2024-5-03")).as("Verificando fecha con mes sin cero").isEqualTo(false);
		assertThat(ValidMethods.isDateHtml("2024-05")).isEqualTo(false);
		assertThat(ValidMethods.isDateHtml("2024-05-3")).isEqualTo(false);
		assertThat(ValidMethods.isDateHtml("2024-05-03")).isEqualTo(true);
		assertThat(ValidMethods.isDateHtml("2024/05/03")).isEqualTo(false);
	}
}
