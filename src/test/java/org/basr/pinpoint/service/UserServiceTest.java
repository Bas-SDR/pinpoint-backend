package org.basr.pinpoint.service;

import org.basr.pinpoint.dto.UserRequestDto;
import org.basr.pinpoint.dto.UserUpdateDto;
import org.basr.pinpoint.exception.ResourceNotFoundException;
import org.basr.pinpoint.helper.FileStorage;
import org.basr.pinpoint.model.User;
import org.basr.pinpoint.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    //Arrange
    User user1;
    User user2;

    @Mock
    private UserRepository userRepos;

    @Mock
    private RoleService roleService;

    @Mock
    private FileStorage fileStorage;

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        user1 = new User();
        ReflectionTestUtils.setField(user1, "id", 1L);
        user1.setFirstName("Firstname");
        user1.setLastName("Lastname");
        user1.setEmail("Email@example.com");
        user1.setPassword("password");
        user1.setDob(LocalDate.of(2000, 8, 1));

        user2 = new User();
        ReflectionTestUtils.setField(user2, "id", 2L);
        user2.setFirstName("Firstname2");
        user2.setLastName("Lastname2");
        user2.setEmail("EmailUser2@example.com");
        user2.setPassword("password2");
        user2.setDob(LocalDate.of(1990, 5, 23));
    }

    @Test
    void createUser() {
        // Arrange
        UserRequestDto newUserDto = new UserRequestDto();
        newUserDto.setFirstName("Annie");
        newUserDto.setLastName("Aardbei");
        newUserDto.setEmail("annie@email.com");
        newUserDto.setPassword("password3");

        User savedUser = new User();
        ReflectionTestUtils.setField(savedUser, "id", 3L);
        savedUser.setFirstName(newUserDto.getFirstName());
        savedUser.setLastName(newUserDto.getLastName());
        savedUser.setEmail(newUserDto.getEmail());
        savedUser.setPassword(newUserDto.getPassword());

        //https://stackoverflow.com/questions/51247796/how-to-mock-jpa-repositorys-save-method-in-unit-tests
        when(userRepos.save(Mockito.any(User.class))).thenReturn(savedUser);

        // Act
        User newUser = userService.createUser(newUserDto);

        // Assert
        assertEquals(3L, newUser.getId());
        assertEquals("Annie", newUser.getFirstName());
        assertEquals("Aardbei", newUser.getLastName());
        assertEquals("annie@email.com", newUser.getEmail());
        assertEquals("password3", newUser.getPassword());
    }

    @Test
    void shouldGetSingleUser() {
        //Arrange
        when(userRepos.findById(1L)).thenReturn(Optional.of(user1));
        //Act
        User user = userService.getSingleUser(1L);
        //Assert
        assertEquals(1L, user.getId());
        assertEquals("Firstname", user.getFirstName());
        assertEquals("Lastname", user.getLastName());
        assertEquals("Email@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
    }

    @Test
    void shouldGetNoUser() {
        //Arrange
        when(userRepos.findById(1000L)).thenReturn(Optional.empty());
        //Act
        //Assert
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            userService.getSingleUser(1000L);
        });
        assertEquals("User 1000 not found", ex.getMessage());
    }

    @Test
    void shouldGetUserByFirstNameAndLastName() {
        //Act
        when(userRepos.findByFirstNameAndLastName("FirstName",  "LastName")).thenReturn(Arrays.asList(user1, user2));
        //Arrange
        List<User> users = userService.getUserByName("FirstName", "LastName");
        //Assert
        assertEquals(2, users.size());
        assertEquals(user1, users.get(0));
        assertEquals(user2, users.get(1));
    }

    @Test
    void shouldGetUserByFirstName() {
        //Act
        when(userRepos.findByFirstName("FirstName")).thenReturn(Arrays.asList(user1, user2));
        //Arrange
        List<User> users = userService.getUserByName("FirstName", null);
        //Assert
        assertEquals(2, users.size());
        assertEquals(user1, users.get(0));
        assertEquals(user2, users.get(1));
    }

    @Test
    void shouldGetUserByLastName() {
        //Act
        when(userRepos.findByLastName("LastName")).thenReturn(Arrays.asList(user1, user2));
        //Arrange
        List<User> users = userService.getUserByName(null, "LastName");
        //Assert
        assertEquals(2, users.size());
        assertEquals(user1, users.get(0));
        assertEquals(user2, users.get(1));
    }

    @Test
    void shouldNotGetUserByName() {
        //Act
        when(userRepos.findByFirstNameAndLastName("FirstName",  "LastName3")).thenReturn(List.of());
        //Arrange
        List<User> users = userService.getUserByName("FirstName", "LastName3");
        //Assert
        assertEquals(0, users.size());
    }

    @Test
    void shouldNotGetResult() {
        //Act
        //Arrange
        List<User> users = userService.getUserByName(null, null);
        //Assert
        assertTrue(users.isEmpty());
    }

    @Test
    void shouldGetUserIdByEmail() {
        // Arrange
        when(userRepos.findByEmail("Email@example.com")).thenReturn(Optional.of(user1));

        // Act
        Long id = userService.getUserIdByEmail("Email@example.com");

        // Assert
        assertEquals(1L, id);
    }

    @Test
    void shouldNotGetUserIdByEmail() {
        // Arrange
        when(userRepos.findByEmail("notexisting@email.com")).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                userService.getUserIdByEmail("notexisting@email.com"));

        assertEquals("User not found", ex.getMessage());
    }

    @Test
    void shouldGetAllUsers() {
        //Arrange
        when(userRepos.findAll()).thenReturn(Arrays.asList(user1, user2));
        //Act
        List<User> users = userService.getAllUsers();
        //Assert
        assertEquals(2, users.size());
        assertEquals("Firstname", users.get(0).getFirstName());
        assertEquals("Lastname", users.get(0).getLastName());
        assertEquals("Email@example.com", users.get(0).getEmail());
        assertEquals("password", users.get(0).getPassword());
        assertEquals("Firstname2", users.get(1).getFirstName());
        assertEquals("Lastname2", users.get(1).getLastName());
        assertEquals("EmailUser2@example.com", users.get(1).getEmail());
        assertEquals("password2", users.get(1).getPassword());
    }

    @Test
    void shouldUpdateUser() {
        //Arrange
        when(userRepos.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepos.save(user1)).thenReturn(user1);

        UserUpdateDto newUserInfoDto = new UserUpdateDto();
        newUserInfoDto.setFirstName("FirstNameNew");
        newUserInfoDto.setLastName("LastNameNew");
        newUserInfoDto.setEmail("Email@example.com");
        //Act
        User updatedUser = userService.updateUser(1L, newUserInfoDto);
        //Assert
        assertEquals("FirstNameNew", updatedUser.getFirstName());
        assertEquals("LastNameNew", updatedUser.getLastName());
    }

    @Test
    void shouldThrowResourceNotFoundException() {
        //Arrange
        when(userRepos.findById(500L)).thenReturn(Optional.empty());

        UserUpdateDto newUserInfoDto = new UserUpdateDto();
        newUserInfoDto.setFirstName("FirstNameNew");
        newUserInfoDto.setLastName("LastNameNew");
        newUserInfoDto.setEmail("Email@example.com");

        //Act
        //Assert
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            userService.updateUser(500L, newUserInfoDto);
        });
        assertEquals("User 500 not found", ex.getMessage());
    }

    @Test
    void shouldFindSingleUserByDobAfter() {
        //Arrange
        when(userRepos.findByDobAfter(LocalDate.of(1994, 3, 3))).thenReturn(Arrays.asList(user1));
        //Act
        List<User> users = userService.findByDobAfter(LocalDate.of(1994, 3, 3));
        //Assert
        assertEquals(1, users.size());
        assertEquals(LocalDate.of(2000, 8, 1), user1.getDob());
    }

    @Test
    void shouldFindBothUsersByDobAfter() {
        //Arrange
        when(userRepos.findByDobAfter(LocalDate.of(1989, 3, 3))).thenReturn(Arrays.asList(user1, user2));
        //Act
        List<User> users = userService.findByDobAfter(LocalDate.of(1989, 3, 3));
        //Assert
        assertEquals(2, users.size());
        assertEquals("Firstname", users.get(0).getFirstName());
        assertEquals(LocalDate.of(2000, 8, 1), users.get(0).getDob());
        assertEquals("Firstname2", users.get(1).getFirstName());
        assertEquals(LocalDate.of(1990, 5, 23), users.get(1).getDob());
    }

    @Test
    void shouldUploadProfilePicture() throws IOException {
        // Arrange
        MultipartFile file = Mockito.mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("profilePic.png");
        when(file.getBytes()).thenReturn(new byte[]{1, 2, 3});

        when(userRepos.findById(user1.getId())).thenReturn(Optional.of(user1));
        when(userRepos.save(user1)).thenReturn(user1);

        // Act
        String url = userService.uploadProfilePicture(user1.getId(), file);

        // Assert
        assertTrue(url.startsWith("/images/profilepic/"));
        assertTrue(url.endsWith("_profilePic.png"));
        assertEquals(url, user1.getProfilePic());
    }

    @Test
    void shouldNotFindUserForUpload() {
        // Arrange
        MultipartFile file = Mockito.mock(MultipartFile.class);

        when(userRepos.findById(1000L)).thenReturn(Optional.empty());
        //Act
        //Assert
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            userService.uploadProfilePicture(1000L, file);
        });
        assertEquals("User 1000 not found", ex.getMessage());
    }

    @Test
    void shouldNotUploadProfilePicture() throws IOException {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("profilePic.png");
        when(file.getBytes()).thenReturn(new byte[]{1,2,3});

        when(userRepos.findById(1L)).thenReturn(Optional.of(user1));

        Mockito.doThrow(new IOException("Test")).when(fileStorage).writeFile(Mockito.any(), Mockito.any());

        //Act
        //Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            userService.uploadProfilePicture(1L, file);
        });

        assertEquals("Image upload failed", ex.getMessage());
    }

    @Test
    void shouldUpdatePassword() {
        // Arrange
        when(userRepos.findById(1L)).thenReturn(Optional.of(user1));

        //Act
        userService.updatePassword(1L, "newPassword");

        // Assert
        assertTrue(user1.getPassword().length() > 0);
        assertNotEquals("password", user1.getPassword());
    }

    @Test
    void shouldNotUpdatePassword() {
        // Arrange
        when(userRepos.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () ->
                userService.updatePassword(999L, "somePassword"));

        assertEquals("User 999 not found", ex.getMessage());
    }
}