package com.example.pps_tudai.mvp.presenter;

import com.example.pps_tudai.mvp.model.LoginModel;
import com.example.pps_tudai.mvp.view.LoginView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginPresenterTest {

    @Mock private LoginPresenter loginPresenter;
    @Mock private LoginView loginView;
    @Mock private LoginModel loginModel;
    private static final String EMAIL_USER = "lorena@example.com";
    private static final String  WRONG_EMAIL_USER = "lorena.example.com";
    private static final String  INVALID_EMAIL_USER = "lorena@gmail.com";
    private static final String PASSWORD_USER = "123456";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loginModel = new LoginModel();
        loginPresenter = new LoginPresenter(loginModel,loginView);

    }

    @Test
    public void loginOK() {
        when(loginView.getUserEmail()).thenReturn(EMAIL_USER);
        when(loginView.getPassword()).thenReturn(PASSWORD_USER);

        loginPresenter.onLoginPressed();
        verify(loginView).showWelcomeScreen();
    }

    @Test
    public void loginInvalidEmail() {
        when(loginView.getUserEmail()).thenReturn(WRONG_EMAIL_USER);
        when(loginView.getPassword()).thenReturn(PASSWORD_USER);
        loginPresenter.onLoginPressed();

        verify(loginView).showOnEmailErrorMessage();
    }

    @Test
    public void loginEmptyData() {
        when(loginView.getUserEmail()).thenReturn("");
        when(loginView.getPassword()).thenReturn("");
        loginPresenter.onLoginPressed();

        verify(loginView).showDataEmptyScreen();
    }

    @Test
    public void loginNoAuthenticUser() {
        when(loginView.getUserEmail()).thenReturn(INVALID_EMAIL_USER);
        when(loginView.getPassword()).thenReturn(PASSWORD_USER);

        loginPresenter.onLoginPressed();
        verify(loginView).showOnAuthenticationErrorMessage();
    }

    @Test
    public void pressedCancelButton () {
        loginPresenter.onCancelPressed();
        verify(loginView).cancelLogin();
    }
}