package com.example.pps_tudai.mvp.presenter;

import android.view.animation.Animation;
import com.example.pps_tudai.mvp.view.PresentationView;

public class PresentationPresenter {

    private final PresentationView presentationView;

    public PresentationPresenter(PresentationView presentationView) {
        this.presentationView = presentationView;
    }

    public void startWelcomeAnimation (Animation animation1, Animation animation2) {
        presentationView.initAnimations(animation1, animation2);
    }
}
