package it.lorenzolavacchini.dedalo.config;

import it.lorenzolavacchini.dedalo.domain.Admin;
import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.repository.UserRepository;
import it.lorenzolavacchini.dedalo.service.AdminService;
import it.lorenzolavacchini.dedalo.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final UserService userService;
    private final AdminService adminService;

    public AdminInitializer(UserRepository userRepository, UserService userService, AdminService adminService){
        this.userRepository = userRepository;
        this.userService = userService;
        this.adminService = adminService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        if(!userRepository.existsByAdminRoleIsNotNull()){

            User user;
            user = userService.createUser("admin", "", "", "airfieldadmin@gmail.com","password");
            userService.promoteToAdmin(user.getId());
            userRepository.save(user);
        }

    }
}
