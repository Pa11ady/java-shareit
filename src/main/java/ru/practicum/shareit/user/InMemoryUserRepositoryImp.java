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
        return new User(user);
    }

    @Override
    public User findById(Long id) {
        User user = super.findById(id);
        if (user == null) {
            return null;
        }
        return new User(user);
    }

    @Override
    public User update(User user) {
        String oldEmail = super.findById(user.getId()).getEmail();
        user = super.update(user);
        emails.remove(oldEmail);
        emails.add(user.getEmail());
        return new User(user);
    }

    @Override
    public boolean containsEmail(String email) {
        return emails.contains(email);
    }

    @Override
    public void delete(Long id) {
        String oldEmail = super.findById(id).getEmail();
        emails.remove(oldEmail);
        super.delete(id);
    }
}
