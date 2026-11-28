package Thyme_wizards.user;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserServiceTest {

    private final UserService service = new UserService();

    @Test
    void returnsExpectedDummyUsers() {
        List<User> users = service.getUsers();

        assertThat(users).hasSize(5);
        assertThat(users)
                .extracting(User::getId)
                .containsExactly(1, 2, 3, 4, 5);
        assertThat(users)
                .extracting(User::getEmail)
                .containsExactly(
                        "alex.johnson@example.com",
                        "morgan.lee@example.com",
                        "taylor.smith@example.com",
                        "jordan.patel@example.com",
                        "casey.brown@example.com");
    }

    @Test
    void eachCallReturnsAnUnmodifiableUserList() {
        assertThatThrownBy(() -> service.getUsers().clear())
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
