package com.example.pps_tudai.mvp.presenter;

import com.example.pps_tudai.mvp.view.MainView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class MainPresenterTest {

    @Mock private MainPresenter mainPresenter;
    @Mock private MainView mainView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mainPresenter = new MainPresenter(mainView);
    }

    @Test
    public void onLoginPressedTest() {
        mainPresenter.onLoginPressed();
        verify(mainView).showLoginScreen();
    }

    @Test
    public void onSignUpPressed() {
        mainPresenter.onRegistrationPressed();
        verify(mainView).showRegistrationScreen();
    }
}