package com.example.pps_tudai.mvp.model;

import com.example.pps_tudai.data.entities.AppRepository;
import com.example.pps_tudai.data.entities.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class RegistrationModelTest {

    private RegistrationModel registerModel;
    @Mock
    private AppRepository appRepository;
    @Mock
    private User user;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        registerModel = new RegistrationModel(appRepository);
    }

    @Test
    public void checkUserRegistration() {
        registerModel.registerUser(user);
        verify(appRepository).insert(user);
    }
}