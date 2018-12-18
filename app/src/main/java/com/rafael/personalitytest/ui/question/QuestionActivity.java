package com.rafael.personalitytest.ui.question;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.rafael.personalitytest.App;
import com.rafael.personalitytest.R;
import com.rafael.personalitytest.model.Question;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionActivity extends AppCompatActivity {

    private SlimAdapter slimAdapter;

    @Inject
    QuestionViewModel viewModel;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        ButterKnife.bind(this);
        ((App) getApplication()).getComponent().inject(this);

        swipeRefresh.setOnRefreshListener(() -> viewModel.loadQuestions());

        slimAdapter = SlimAdapter.create()
                .register(R.layout.item_question, new SlimInjector<Question>() {
                    @Override
                    public void onInject(Question data, IViewInjector injector) {
                        injector.text(R.id.description, data.getDescription());
                    }
                })
                .enableDiff()
                .attachTo(recyclerView);


        viewModel.getQuestionProgress().observe(this, showProgressBar -> {
            swipeRefresh.setRefreshing(showProgressBar);
        });

        viewModel.getQuestions().observe(this, questions -> {
            if (questions != null && !questions.isEmpty()) {
                slimAdapter.updateData(questions);
            }
        });

        viewModel.getQuestionError().observe(this, message -> Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show());
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.loadQuestions();
    }
}
