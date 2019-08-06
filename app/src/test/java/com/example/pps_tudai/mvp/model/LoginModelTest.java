package com.example.pps_tudai.mvp.model;

import com.example.pps_tudai.data.entities.AppRepository;
import com.example.pps_tudai.data.entities.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static com.example.pps_tudai.utils.StringUtils.EMAIL_USER;
import static com.example.pps_tudai.utils.StringUtils.PASSWORD_USER;
import static org.mockito.Mockito.verify;

public class LoginModelTest {

    private LoginModel loginModel;
    @Mock
    private AppRepository appRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loginModel = new LoginModel(appRepository);
    }

    @Test
    public void checkRegisteredEmail() {
        loginModel.checkEmailRegistered(EMAIL_USER);
        verify(appRepository).getUserByEmail(EMAIL_USER);
    }

    @Test
    public void checkEmailAndPassword() {
        loginModel.validateUserByEmailAndPassword(EMAIL_USER, PASSWORD_USER);
        verify(appRepository).getUserByEmail(EMAIL_USER);
    }
}