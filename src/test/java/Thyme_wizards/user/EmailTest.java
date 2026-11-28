package Thyme_wizards.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class EmailTest {

    @Test
    void createsEmailFromTextContainingAtSign() {
        Email email = new Email("person@example.com");

        assertThat(email.asString()).isEqualTo("person@example.com");
    }

    @Test
    void rejectsNullBlankOrWhitespaceOnlyEmail() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Email(null))
                .withMessage("email cannot be blank");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Email(""))
                .withMessage("email cannot be blank");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Email("   "))
                .withMessage("email cannot be blank");
    }

    @Test
    void rejectsEmailWithoutAtSign() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Email("person.example.com"))
                .withMessage("email should contain @ symbol");
    }

    @Test
    void equalEmailsHaveEqualValuesAndHashes() {
        Email first = new Email("person@example.com");
        Email second = new Email("person@example.com");

        assertThat(first).isEqualTo(second);
        assertThat(first.hashCode()).isEqualTo(second.hashCode());
        assertThat(first).hasToString("Email{email='person@example.com'}");
    }
}
