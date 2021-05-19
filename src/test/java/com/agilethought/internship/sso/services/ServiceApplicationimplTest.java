package com.agilethought.internship.sso.services;

import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.repository.RepositoryApplication;
import com.agilethought.internship.sso.services.security.RsaPasswordEncoder;
import com.agilethought.internship.sso.validator.user.LoginValidator;
import com.agilethought.internship.sso.validator.user.NewUserValidator;
import com.agilethought.internship.sso.validator.user.UpdateUserValidator;
import ma.glasnost.orika.MapperFacade;
import static com.agilethought.internship.sso.exception.errorhandling.ErrorMessage.NOT_FOUND_RESOURCE;
import static com.agilethought.internship.sso.exception.errorhandling.ErrorMessage.USER;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.agilethought.internship.sso.dto.NewUserRequest;
import com.agilethought.internship.sso.dto.NewUserResponse;
import com.agilethought.internship.sso.dto.UpdateUserRequest;
import com.agilethought.internship.sso.dto.UpdateUserResponse;
import com.agilethought.internship.sso.dto.UserDTO;
import com.agilethought.internship.sso.exception.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceApplicationimplTest {
	@Mock
	private RepositoryApplication repositoryApplication;

	@Mock
	private MapperFacade orikaMapperFacade;

	@Mock
	private NewUserValidator newUserValidator;

    @Mock
    private UpdateUserValidator updateUserValidator;
    
    @Mock
    private LoginValidator loginValidator;

    @Mock
	private RsaPasswordEncoder passwordEncoder;

    @InjectMocks
    private ServiceApplicationimpl serviceApplicationimpl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllUsers() {
		when(repositoryApplication.findAll()).thenReturn(new ArrayList<>());
		when(orikaMapperFacade.mapAsList(anyList(), any())).thenReturn(new ArrayList<>());
		List<UserDTO> testResult = serviceApplicationimpl.getAllUsers();
		assertNotNull(testResult);
	}

	@Test
	public void testCreateUserSuccessfully() {
		User mockCreateUser = new User();
		mockCreateUser.setFirstName("");
		mockCreateUser.setLastName("");
		mockCreateUser.setEmail("");
		
		String encryptedPassword = "cKtW2JkL0STGZ6uhs/8R/isp9hGPtE49GVLQQVPGMz/UVPlvCEuLP7ZofRmCBFlXJdeeOraUIC4qWPt+yCC/ndlYRu2vW9MituTxSYgq+CU50TiB3/4u4Qn1Iv2I34X11ybyMPT30uZXpEshsf7ZxNdk9cv19wat3Abn5mNwqWZomyCiMvyz7yc0o50AM6KpCTIDAZ5fyxFjwEo0QfxzMoxQjOc3qlmF35b34hbygC+vxgo0Aq4cEv+upcN/8vfGFsCdjOS87byRiy1HmBuYSm1ieY7IJlM5/N0WMhLHku07XrGJAUnTN4LhfJtPJSSRjxe4aqPpvbElYorqFTdbdQ==";
		
		when(orikaMapperFacade.map(any(NewUserRequest.class), any())).thenReturn(mockCreateUser);
		doNothing().when(newUserValidator).validate(any());
		when(passwordEncoder.encode(anyString())).thenReturn("");
		when(repositoryApplication.existsByEmail(anyString())).thenReturn(false);
		when(repositoryApplication.save(any())).thenReturn(new User());
		when(orikaMapperFacade.map(any(User.class), any())).thenReturn(new NewUserResponse());
		NewUserRequest newUserRequest = new NewUserRequest();
		newUserRequest.setFirstName("John");
		newUserRequest.setLastName("Petrucci");
		newUserRequest.setPassword(encryptedPassword);
		newUserRequest.setEmail("test-at@gmail.com");
		newUserRequest.setStatus(1);
		assertNotNull(serviceApplicationimpl.createUser(newUserRequest));
	}

	@Test
	public void testDeleteUserByIdSuccessfully() {

		when(repositoryApplication.findById(anyString())).thenReturn(Optional.of(new User()));
		doNothing().when(repositoryApplication).deleteById(anyString());
		serviceApplicationimpl.deleteUserById(anyString());
		verify(repositoryApplication).deleteById(anyString());

	}

	@Test
	public void testDeleteUserByIdWithIdNotFound() {

		when(repositoryApplication.findById(anyString())).thenReturn(Optional.empty());
		doNothing().when(repositoryApplication).deleteById(anyString());
		NotFoundException thrownException = assertThrows(NotFoundException.class,
				() -> serviceApplicationimpl.deleteUserById(anyString()));
		assertEquals(String.format(NOT_FOUND_RESOURCE, USER, ""), thrownException.getMessage());

	}

    @Test
    public void testUpdateUserByIdSuccessfully() {

        // Given
        User mockUpdateUser = new User();
        mockUpdateUser.setFirstName("");
        mockUpdateUser.setLastName("");
        mockUpdateUser.setEmail("");

        // Then
        when(orikaMapperFacade.map(any(UpdateUserRequest.class), any()))
                .thenReturn(mockUpdateUser);
        doNothing().when(updateUserValidator).validate(any());
		when(passwordEncoder.encode(anyString())).thenReturn("");
        when(repositoryApplication.save(any())).thenReturn(new User());
        when(orikaMapperFacade.map(any(User.class), any())).thenReturn(new UpdateUserResponse());
        assertNotNull(serviceApplicationimpl.updateUserById(new UpdateUserRequest(), ""));
    }

    public void testGetUserByIdSuccessfully() {

        when(repositoryApplication.findById(anyString())).thenReturn(Optional.of(new User()));
        when(orikaMapperFacade.map(any(), any())).thenReturn(new UserDTO());
        UserDTO testResult = serviceApplicationimpl.getUserById(anyString());
        assertNotNull(testResult);
    }

    @Test
    public void testGetUserByIdWithIdNotFound() {

        when(repositoryApplication.findById(anyString())).thenReturn(Optional.empty());
        NotFoundException thrownException = assertThrows(
                NotFoundException.class,
                () -> serviceApplicationimpl.getUserById(anyString())
        );
        assertEquals(
                String.format(NOT_FOUND_RESOURCE, USER, ""),
                thrownException.getMessage()
        );
    }
}