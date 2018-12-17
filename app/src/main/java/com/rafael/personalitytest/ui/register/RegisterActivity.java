package com.rafael.personalitytest.ui.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.rafael.personalitytest.App;
import com.rafael.personalitytest.R;
import com.rafael.personalitytest.ui.login.LoginActivity;
import com.rafael.personalitytest.ui.question.QuestionActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Inject
    RegisterViewModel viewModel;

    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_user)
    EditText editUser;
    @BindView(R.id.edit_password)
    EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        ((App) getApplication()).getComponent().inject(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Autenticação");
        progressDialog.setMessage("aguarde registrando usuário");
        progressDialog.setCancelable(false);

        viewModel.getRegisterProgress().observe(this, showProgressBar -> {
            if (showProgressBar) progressDialog.show();
            else progressDialog.dismiss();
        });

        viewModel.getRegisterStatus().observe(this, isAuth -> {
            if (isAuth) {
                Intent intent = new Intent(this, QuestionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        viewModel.getRegisterError().observe(this, message -> Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show());
    }

    @OnClick(R.id.register)
    public void register() {
        String name = editName.getText().toString();
        String user = editUser.getText().toString();
        String password = editPassword.getText().toString();

        if (!name.isEmpty() && !user.isEmpty() && !password.isEmpty()) {
            viewModel.register(name, user, password);
        } else {
            Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.login)
    public void login() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
