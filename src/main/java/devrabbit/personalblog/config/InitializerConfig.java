package devrabbit.personalblog.config;

import devrabbit.personalblog.helper.DataCreator;
import devrabbit.personalblog.model.User;
import devrabbit.personalblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Configuration
@RequiredArgsConstructor
public class InitializerConfig {

    private final UserRepository userRepository;

    @PostConstruct
    public void onInit() {
        Collection<User> users = DataCreator.createUsers();
        userRepository.saveAll(users);
    }
}