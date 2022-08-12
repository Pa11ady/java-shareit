package ru.practicum.shareit.user;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.common.repository.AbstractInMemoryRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class InMemoryUserRepositoryImp extends AbstractInMemoryRepository<User> implements UserRepository {
    private final Set<String> emails = new HashSet<>();

    @Override
    public User create(User user) {
        user = super.create(user);
        emails.add(user.getEmail());
        return user;
    }

    @Override
    public User update(User user) {
        user = super.update(user);
        emails.add(user.getEmail());
        return user;
    }

    @Override
    public boolean containsEmail(String email) {
        return emails.contains(email);
    }
}
