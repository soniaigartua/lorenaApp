package com.example.pps_tudai.mvp.presenter;

import android.util.Log;
import com.example.pps_tudai.data.entities.entity.User;
import com.example.pps_tudai.mvp.model.RegistrationModel;
import com.example.pps_tudai.mvp.view.RegistrationView;
import com.example.pps_tudai.utils.GMailSender;
import com.example.pps_tudai.utils.Tools;
import static com.example.pps_tudai.utils.StringUtils.EMAIL_HEADER;
import static com.example.pps_tudai.utils.StringUtils.EMAIL_REGISTERED;
import static com.example.pps_tudai.utils.StringUtils.EMAIL_SENDER;
import static com.example.pps_tudai.utils.StringUtils.EMAIL_SUBJECT;
import static com.example.pps_tudai.utils.StringUtils.NAME_REGISTERED;
import static com.example.pps_tudai.utils.StringUtils.PASSWORD_REGISTERED;
import static com.example.pps_tudai.utils.StringUtils.PASSWORD_SENDER;
import static com.example.pps_tudai.utils.StringUtils.SURNAME_REGISTERED;
import static com.example.pps_tudai.utils.StringUtils.WELCOME_MESSAGE;

public class RegistrationPresenter {

    private final RegistrationModel registerModel;
    private final RegistrationView registerView;


    public RegistrationPresenter(RegistrationModel registerModel, RegistrationView registerView) {
        this.registerModel = registerModel;
        this.registerView = registerView;
    }

    public void onRegisterPressed() {
        String name = registerView.getUserName();
        String surname = registerView.getUserSurname();
        String email = registerView.getUserEmail();
        String password = registerView.getPassword();
        String password_repeat = registerView.getPasswordRepeat();

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || password_repeat.isEmpty()) {
            registerView.showDataEmptyScreen();
            return;
        }
        if (!Tools.isValid(email)) {
            registerView.showErrorEmailRegistrationScreen();
            return;
        }
        if (!password.equals(password_repeat)) {
            registerView.showErrorPasswordRegistrationScreen();
        } else {
            User user = new User(name, surname, email, password);
            registerModel.setUser(user);
            registerModel.registerUser(user);
            if (registerView.emailSendRequest()) {
                sendEmail();
            }
            registerView.showRegistrationScreenOK();
        }
    }

    public void onCancelPressed() {
        registerView.cancelRegister();
    }

    public void onSelectAvatarPressed() {
        registerView.selectAvatar();
    }

    public void sendEmail() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender(EMAIL_SENDER, PASSWORD_SENDER);
                    String body = EMAIL_HEADER + System.lineSeparator() +
                            NAME_REGISTERED + registerView.getUserName() + System.lineSeparator() +
                            SURNAME_REGISTERED + registerView.getUserSurname() + System.lineSeparator() +
                            EMAIL_REGISTERED + registerView.getUserEmail() + System.lineSeparator() +
                            PASSWORD_REGISTERED + registerView.getPassword() + System.lineSeparator() +
                            WELCOME_MESSAGE;
                    sender.sendMail(EMAIL_SUBJECT, body, sender.getSenderUser(), registerView.getUserEmail());
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }
        }).start();
    }


}


