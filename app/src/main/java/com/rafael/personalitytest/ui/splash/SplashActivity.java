package com.rafael.personalitytest.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rafael.personalitytest.App;
import com.rafael.personalitytest.R;
import com.rafael.personalitytest.ui.login.LoginActivity;
import com.rafael.personalitytest.ui.question.QuestionActivity;
import com.rafael.personalitytest.ui.register.RegisterActivity;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity {

    @Inject
    SplashViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ((App) getApplication()).getComponent().inject(this);

        viewModel.getLoginStatus().observe(this, isAuth -> {
            if (isAuth) {
                Intent intent = new Intent(this, QuestionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.validateToken();
    }
}
