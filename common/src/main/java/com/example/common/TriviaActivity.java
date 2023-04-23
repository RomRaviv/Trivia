package com.example.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class TriviaActivity extends AppCompatActivity {

    protected TextView txt_score;
    protected List<TriviaCard> triviaCards = new ArrayList<>();
    private int score;
    private String scoreStr = "Score: ";
    private ViewPager2 viewPager;
    private NonScrollableRecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        txt_score = findViewById(R.id.txt_score);
        score = 0;
        
        initCards();

        //init recyclerView

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setNestedScrollingEnabled(false);

        // Initialize the trivia cards
        for (int i = 0; i < getNumberOfCards(); i++) {

            String correctAnswer = getCorrectAnswer(i);
            List<String> answerChoices = getAnswerChoices(i);
            String question = getQuestion(i);
            String imageUrl = getCardImgUrl(i);
            TriviaCard triviaCard = new TriviaCard(question, answerChoices, correctAnswer, imageUrl);
            triviaCard.setCardListener(answer -> {
                if (answer.equals(correctAnswer)) {
                    score += 10;
                    txt_score.setText(scoreStr + score);
                    Toast.makeText(this, "CORRECT!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "WRONG!", Toast.LENGTH_SHORT).show();
                }
            });
            triviaCards.add(triviaCard);
        }
        Collections.shuffle(triviaCards);
        TriviaCardAdapter adapter = new TriviaCardAdapter(triviaCards,recyclerView);
        recyclerView.setAdapter(adapter);


        // Set up the next button
        Button nextButton = findViewById(R.id.btn_next);
        nextButton.setOnClickListener(v -> {
            boolean swipe =  onNextButtonClicked(adapter.getCurrentCard());
            if(swipe) {
                int nextPosition = (adapter.getCurrentCard() + 1) % triviaCards.size();
                recyclerView.smoothScrollToPosition(nextPosition);
            }
        });
    }

    protected abstract void initCards();

    protected abstract String getCardImgUrl(int i);

    protected abstract int getNumberOfCards();

    protected abstract String getCorrectAnswer(int index);

    protected abstract String getQuestion(int index);

    protected abstract ArrayList<String> getAnswerChoices(int index);

    protected abstract boolean onNextButtonClicked(int index);
}
