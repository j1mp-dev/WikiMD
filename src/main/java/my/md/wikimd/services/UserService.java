package my.md.wikimd.services;

import my.md.wikimd.models.User;
import my.md.wikimd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User getUserById(UUID id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    public User getUserLogin(String username, String password) {
        return userRepository.getUserLogin(username, password);
    }

    public Tuple getUserData(UUID id) {
        return userRepository.getUserData(id);
    }


}
