package com.example.pps_tudai.mvp.presenter;

import com.example.pps_tudai.data.entities.AppRepository;
import com.example.pps_tudai.data.entities.entity.User;
import com.example.pps_tudai.mvp.model.LoginModel;
import com.example.pps_tudai.mvp.view.LoginView;
import com.example.pps_tudai.utils.Validator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import static com.example.pps_tudai.utils.StringUtils.EMAIL_USER;
import static com.example.pps_tudai.utils.StringUtils.EMPTY;
import static com.example.pps_tudai.utils.StringUtils.INVALID_EMAIL_USER;
import static com.example.pps_tudai.utils.StringUtils.NAME_USER;
import static com.example.pps_tudai.utils.StringUtils.PASSWORD_USER;
import static com.example.pps_tudai.utils.StringUtils.SURNAME_USER;
import static com.example.pps_tudai.utils.StringUtils.WRONG_EMAIL_USER;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginPresenterTest {

    private LoginPresenter loginPresenter;
    @Mock
    private LoginView loginView;
    @Mock
    private LoginModel loginModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loginPresenter = new LoginPresenter(loginModel, loginView);
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
        when(loginView.getUserEmail()).thenReturn(EMPTY);
        when(loginView.getPassword()).thenReturn(EMPTY);
        loginPresenter.onLoginPressed();

        verify(loginView).showDataEmptyScreen();
    }

    @Test
    public void pressedCancelButton() {
        loginPresenter.onCancelPressed();
        verify(loginView).cancelLogin();
    }
}