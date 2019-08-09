package com.example.pps_tudai.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.example.pps_tudai.R;
import com.example.pps_tudai.mvp.presenter.PresentationPresenter;
import com.example.pps_tudai.mvp.view.PresentationView;
import butterknife.ButterKnife;
import static com.example.pps_tudai.utils.IntUtils.TIMEOUT_ENTER;

public class PresentationActivity extends AppCompatActivity {

    private PresentationPresenter presenter;
    private int timeout = TIMEOUT_ENTER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);

        init();
    }

    private void init() {
        ButterKnife.bind(this);
        PresentationView view = new PresentationView(this);
        presenter = new PresentationPresenter(view);

        Animation welcome_animation = AnimationUtils.loadAnimation(this, R.anim.welcome_anim);
        Animation logo_animation = AnimationUtils.loadAnimation(this, R.anim.portada_anim);
        presenter.startWelcomeAnimation(welcome_animation, logo_animation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intro = new Intent(PresentationActivity.this, MainActivity.class);
                startActivity(intro);
                finish();
            }
        }, timeout);
    }
}
