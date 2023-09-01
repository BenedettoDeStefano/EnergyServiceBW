package EnergyServices.test_userService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import EnergyServices.Entities.User;
import EnergyServices.Enum.Role;
import EnergyServices.Repository.UserRepository;
import EnergyServices.Service.UserService;

@SpringBootTest
public class UserServiceTest {
	@InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // test per verificare se la lista di utenti è piena
    @Test
    public void testGetAllUsersWhenRepositoryContainsData() {

        List<User> users = new ArrayList<>();
        users.add(new User(UUID.randomUUID(), "fla89", "1234", "Flavio", "Mammo", 34, Role.ADMIN));
        users.add(new User(UUID.randomUUID(), "zark9", "45a98c", "Davide", "Zarconi", 22, Role.USER));

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        //mi aspetto che la lista rstituita sia composta da due utenti
        assertEquals(2, result.size());
    }

    // test per verificare se la lista di utenti è vuota
    @Test
    public void testGetAllUsersWhenRepositoryIsEmpty() {

        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        List<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    
    // test per verificare il corretto salvataggio dell'utente su DB con l'id generato
    @Test
    public void testSaveUser() {

        User userToSave = new User(null, "fla89", "1234", "Flavio", "Mammo", 34, Role.ADMIN);
        User savedUser = new User(UUID.randomUUID(), "fla89", "1234", "Flavio", "Mammo", 34, Role.ADMIN);

        when(userRepository.save(userToSave)).thenReturn(savedUser);

        User result = userService.saveUser(userToSave);

        assertNotNull(result);
        assertEquals(savedUser, result);
    }
    
    // test per verificare se il metodo di rimozione dell'utente funziona correttamente
    @Test
    public void testDeleteUser() throws NotFoundException {

        UUID userId = UUID.randomUUID();
        User userToDelete = new User(userId, "fla89", "1234", "Flavio", "Mammo", 34, Role.ADMIN);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userToDelete));

        userService.deleteUser(userId);

        verify(userRepository, times(1)).delete(userToDelete);
    }

}
