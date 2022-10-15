package devrabbit.personalblog.helper;

import devrabbit.personalblog.model.Role;
import devrabbit.personalblog.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public final class DataCreator {

    private DataCreator() {
        throw new UnsupportedOperationException();
    }

    public static Collection<User> createUsers() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername("kubrafelek");
        user.setFullname("Kübra Felek");
        user.setEmail("kbr.flk@hotmail.com");
        user.setLastSuccessfullyLoggedInTime(new Date(Date.from(Instant.now()).getTime()));
        user.setLastFailureLoggedInTime(new Date(Date.from(Instant.now()).getTime()));
        user.setPassword(passwordEncoder.encode("password"));
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        user.addRoles(Collections.singleton(adminRole));
        return Collections.singletonList(user);
    }

}