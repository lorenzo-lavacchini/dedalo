package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PilotService pilotService;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_Successo(){
        String email = "mario.rossi@email.com";
        when(userRepository.existsByEmail(email)).thenReturn(false);

        User savedUser = userService.createUser("Mario", "Rossi", "333123456", email, "password123");

        assertNotNull(savedUser);
        assertEquals("Mario", savedUser.getName());
        assertEquals(email, savedUser.getEmail());

        verify(userRepository, times(1)).save(any(User.class));
    }
}
