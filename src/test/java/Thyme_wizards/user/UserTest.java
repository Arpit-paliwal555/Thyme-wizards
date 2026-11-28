package Thyme_wizards.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void exposesConstructorValues() {
        User user = new User(7, "Alex Johnson", "alex.johnson@example.com");

        assertThat(user.getId()).isEqualTo(7);
        assertThat(user.getName()).isEqualTo("Alex Johnson");
        assertThat(user.getEmail()).isEqualTo("alex.johnson@example.com");
    }
}
