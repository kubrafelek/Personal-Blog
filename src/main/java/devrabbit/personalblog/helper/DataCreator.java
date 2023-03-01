package devrabbit.personalblog.helper;

import devrabbit.personalblog.model.Role;
import devrabbit.personalblog.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
        adminRole.setName("ROLE_ADMIN");
        user.addRoles(Collections.singleton(adminRole));

        User user2 = new User();
        user2.setUsername("ahmetf");
        user2.setFullname("Ahmet Faik");
        user2.setEmail("ahmet@hotmail.com");
        user2.setLastSuccessfullyLoggedInTime(new Date(Date.from(Instant.now()).getTime()));
        user2.setLastFailureLoggedInTime(new Date(Date.from(Instant.now()).getTime()));
        user2.setPassword(passwordEncoder.encode("123"));

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        user2.addRoles(Collections.singleton(userRole));

        User user3 = new User();
        user3.setUsername("selins");
        user3.setFullname("Selin Sezer");
        user3.setEmail("selin@gmail.com");
        user3.setLastSuccessfullyLoggedInTime(new Date(Date.from(Instant.now()).getTime()));
        user3.setLastFailureLoggedInTime(new Date(Date.from(Instant.now()).getTime()));
        user3.setPassword(passwordEncoder.encode("123"));

        Role userRole2 = new Role();
        userRole2.setName("ROLE_USER");
        user3.addRoles(Collections.singleton(userRole2));

        return List.of(user,user2,user3);
    }

}