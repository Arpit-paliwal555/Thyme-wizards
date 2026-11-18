package Thyme_wizards.user.jpa;

import io.github.wimdeblauwe.jpearl.InMemoryUniqueIdGenerator;
import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("data-jpa-test") //<.>
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    private final UserRepository repository;
    private final JdbcTemplate jdbcTemplate;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserRepositoryTest(UserRepository repository,
                           JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void validatePreconditions() {
        assertThat(repository.count()).isZero();
    }

    @Test
    void testSaveUser() {
        UserId id = repository.nextId();
        repository.save(new User(id));

        entityManager.flush();
        UUID idInDb = jdbcTemplate.queryForObject("SELECT id FROM tt_user", UUID.class); //<.>
        assertThat(idInDb).isEqualTo(id.getId());
    }
    @TestConfiguration //<.>
    static class TestConfig {
        @Bean
        public UniqueIdGenerator<UUID> uniqueIdGenerator() { //<.>
            return new InMemoryUniqueIdGenerator();
        }
    }
}
