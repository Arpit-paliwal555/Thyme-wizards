package Thyme_wizards.user.jpa;

import io.github.wimdeblauwe.jpearl.AbstractEntity;

import jakarta.persistence.Entity;
import org.hibernate.annotations.Audited;

@Entity
@Audited.Table(name = "tt_user")
public class User extends AbstractEntity<UserId> {

    /**
     * Default constructor for JPA
     */
    protected User() {
    }

    public User(UserId id) {
        super(id);
    }
}