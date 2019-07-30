package com.example.pps_tudai.mvp.presenter;

import com.example.pps_tudai.mvp.model.LoginModel;
import com.example.pps_tudai.mvp.view.LoginView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginPresenterTest {

    @Mock private LoginPresenter loginPresenter;
    @Mock private LoginView loginView;
    @Mock private LoginModel loginModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loginModel = new LoginModel();
        loginPresenter = new LoginPresenter(loginModel,loginView);

    }


//    @Test
//    public void validEmail () {
//        when(loginPresenter.isvalid("lorena@example.com")).;
//    }

    @Test
    public void loginOK() {
        when(loginView.getUserEmail()).thenReturn("lorena@example.com");
        when(loginView.getPassword()).thenReturn("123456");

        loginPresenter.onLoginPressed();
        verify(loginView).showWelcomeScreen();
    }

    @Test
    public void loginInvalidEmail() {
        when(loginView.getUserEmail()).thenReturn("lorena.example.com");
        when(loginView.getPassword()).thenReturn("123456");
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
        when(loginView.getUserEmail()).thenReturn("lorena@gmail.com");
        when(loginView.getPassword()).thenReturn("123456");

        loginPresenter.onLoginPressed();
        verify(loginView).showOnAuthenticationErrorMessage();
    }

    @Test
    public void pressedCancelButton () {
        loginPresenter.onCancelPressed();
        verify(loginView).cancelLogin();
    }




}