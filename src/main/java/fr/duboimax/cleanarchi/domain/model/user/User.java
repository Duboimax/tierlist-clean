package fr.duboimax.cleanarchi.domain.model.user;

import java.time.Instant;
import java.util.Objects;

public class User {

    final private UserId id;
    final private Email email;
    final private Password password;
    final private Instant createdAt;

    public User(Email email, Password password) {
        this.id = UserId.generate();
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
        this.createdAt = Instant.now();
    }

    public User(UserId id, Email email, Password password, Instant createdAt) {
        this.id = Objects.requireNonNull(id);
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    public UserId getId() { return id; }
    public Email getEmail() { return email; }
    public Password getPassword() { return password; }
    public Instant getCreatedAt() { return createdAt; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
