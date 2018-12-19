package com.rafael.personalitytest.ui.answer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.rafael.personalitytest.App;
import com.rafael.personalitytest.R;
import com.rafael.personalitytest.model.Question;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnswerActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Question question;

    @Inject
    AnswerViewModel viewModel;

    @BindView(R.id.txt_description)
    TextView textView;

    @BindView(R.id.rg_options)
    MultiLineRadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        ButterKnife.bind(this);
        ((App) getApplication()).getComponent().inject(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle == null) finish();

        question = (Question) bundle.get("question");

        if (question == null) finish();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Enviando");
        progressDialog.setMessage("aguarde enviando sua resposta");
        progressDialog.setCancelable(false);

        textView.setText(question.getDescription());
        radioGroup.addButtons(question.getOptions().toArray(new String[0]));
        radioGroup.checkAt(0);

        viewModel.getAnswerProgress().observe(this, showProgressBar -> {
            if (showProgressBar) progressDialog.show();
            else progressDialog.dismiss();
        });

        viewModel.getAnswerStatus().observe(this, status -> {
            if (status) {
                Toast.makeText(getApplicationContext(), "Parabéns continue respondendo as perguntas!!!", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        viewModel.getAnswerError().observe(this, message -> Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show());
    }

    @OnClick(R.id.button_confirm)
    void confirm() {
        viewModel.sendAnswer(question.getId(), question.getDescription(), radioGroup.getCheckedRadioButtonText().toString());
    }
}
