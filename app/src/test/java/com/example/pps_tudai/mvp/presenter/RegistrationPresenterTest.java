package com.example.pps_tudai.mvp.presenter;

import com.example.pps_tudai.mvp.model.RegistrationModel;
import com.example.pps_tudai.mvp.view.RegistrationView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.example.pps_tudai.utils.StringUtils.EMAIL_USER;
import static com.example.pps_tudai.utils.StringUtils.EMPTY;
import static com.example.pps_tudai.utils.StringUtils.NAME_USER;
import static com.example.pps_tudai.utils.StringUtils.PASSWORD_USER;
import static com.example.pps_tudai.utils.StringUtils.SURNAME_USER;
import static com.example.pps_tudai.utils.StringUtils.WRONG_EMAIL_USER;
import static com.example.pps_tudai.utils.StringUtils.WRONG_PASSWORD_USER;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RegistrationPresenterTest {

    @Mock
    private RegistrationPresenter registerPresenter;
    @Mock
    private RegistrationView registerView;
    @Mock
    private RegistrationModel registerModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        registerModel = new RegistrationModel();
        registerPresenter = new RegistrationPresenter(registerModel, registerView);
    }

    @Test
    public void registrationOK() {
        when(registerView.getUserName()).thenReturn(NAME_USER);
        when(registerView.getUserSurname()).thenReturn(SURNAME_USER);
        when(registerView.getUserEmail()).thenReturn(EMAIL_USER);
        when(registerView.getPassword()).thenReturn(PASSWORD_USER);
        when(registerView.getPasswordRepeat()).thenReturn(PASSWORD_USER);

        registerPresenter.onRegisterPressed();
        verify(registerView).showRegistrationScreenOK();
    }

    @Test
    public void incorrectPassword() {
        when(registerView.getUserName()).thenReturn(NAME_USER);
        when(registerView.getUserSurname()).thenReturn(SURNAME_USER);
        when(registerView.getUserEmail()).thenReturn(EMAIL_USER);
        when(registerView.getPassword()).thenReturn(PASSWORD_USER);
        when(registerView.getPasswordRepeat()).thenReturn(WRONG_PASSWORD_USER);

        registerPresenter.onRegisterPressed();
        verify(registerView).showErrorPasswordRegistrationScreen();
    }

    @Test
    public void incorrectEmail() {
        when(registerView.getUserName()).thenReturn(NAME_USER);
        when(registerView.getUserSurname()).thenReturn(SURNAME_USER);
        when(registerView.getUserEmail()).thenReturn(WRONG_EMAIL_USER);
        when(registerView.getPassword()).thenReturn(PASSWORD_USER);
        when(registerView.getPasswordRepeat()).thenReturn(WRONG_PASSWORD_USER);

        registerPresenter.onRegisterPressed();
        verify(registerView).showErrorEmailRegistrationScreen();
    }

    @Test
    public void emptyFields() {
        when(registerView.getUserName()).thenReturn(EMPTY);
        when(registerView.getUserSurname()).thenReturn(SURNAME_USER);
        when(registerView.getUserEmail()).thenReturn(WRONG_EMAIL_USER);
        when(registerView.getPassword()).thenReturn(PASSWORD_USER);
        when(registerView.getPasswordRepeat()).thenReturn(PASSWORD_USER);

        registerPresenter.onRegisterPressed();
        verify(registerView).showDataEmptyScreen();
    }

    @Test
    public void pressedCancelButton() {
        registerPresenter.onCancelPressed();
        verify(registerView).cancelRegister();
    }
}