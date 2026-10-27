package it.lorenzolavacchini.dedalo.config;

import it.lorenzolavacchini.dedalo.domain.Admin;
import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.repository.UserRepository;
import it.lorenzolavacchini.dedalo.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final UserService userService;

    public AdminInitializer(UserRepository userRepository, UserService userService){
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        if(!userRepository.existsByAdminRoleIsNotNull()){

            User user;
            user = userService.createUser("admin", "- ", "-", "airfieldadmin@gmail.com","password");
            userService.promoteToAdmin(user.getId());
            userRepository.save(user);

            //Aggiungo due user per provare i metodi del controller
            User user1;
            User user2;
            user1 = userService.createUser("user 1", "- ", "-", "user1@gmail.com","password");
            user2 = userService.createUser("user 2", "- ", "-", "user2@gmail.com","password");
            userRepository.save(user1);
            userRepository.save(user2);

        }

    }
}
