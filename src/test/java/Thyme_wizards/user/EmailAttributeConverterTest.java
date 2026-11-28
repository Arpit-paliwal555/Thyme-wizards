package Thyme_wizards.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmailAttributeConverterTest {

    private final EmailAttributeConverter converter = new EmailAttributeConverter();

    @Test
    void convertsEmailToDatabaseText() {
        assertThat(converter.convertToDatabaseColumn(new Email("person@example.com")))
                .isEqualTo("person@example.com");
        assertThat(converter.convertToDatabaseColumn(null)).isNull();
    }

    @Test
    void convertsDatabaseTextToEmail() {
        assertThat(converter.convertToEntityAttribute("person@example.com").asString())
                .isEqualTo("person@example.com");
        assertThat(converter.convertToEntityAttribute(null)).isNull();
    }
}
