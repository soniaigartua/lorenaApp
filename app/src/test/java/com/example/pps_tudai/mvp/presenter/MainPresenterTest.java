package com.example.pps_tudai.mvp.presenter;

import com.example.pps_tudai.mvp.model.MainModel;
import com.example.pps_tudai.mvp.view.MainView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class MainPresenterTest {

    private MainPresenter mainPresenter;
    @Mock private MainView mainView;
    @Mock private MainModel mainModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mainPresenter = new MainPresenter(mainView, mainModel);
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