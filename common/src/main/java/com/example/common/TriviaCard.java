package com.example.common;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class TriviaCard {


    private String question;
    private List<String> answerChoices;
    private int correctAnswerIndex;
    private String imageUrl;
    private String selectedAnswer;
    private boolean isAnswered;
    private int selectedAnswerIndex;
    private String correctAnswer;
    private CardListener cardListener;


    public TriviaCard(String question, List<String> answerChoices, String correctAnswer, @Nullable String imageUrl) {
        this.question = question;
        this.answerChoices = answerChoices;
        this.correctAnswer = correctAnswer;
        this.imageUrl = imageUrl;
        selectedAnswer = "";
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswerChoices() {
        return answerChoices;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public int getSelectedAnswerIndex() {
        return selectedAnswerIndex;
    }


    public void setSelectedAnswerIndex(int selectedAnswerIndex) {
        this.selectedAnswerIndex = selectedAnswerIndex;
    }

    public void setCardListener(CardListener cardListener){
        this.cardListener = cardListener;
    }


    public String getCorrectAnswer() {
        return correctAnswer;
    }


    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswerChoices(List<String> answerChoices) {
        this.answerChoices = answerChoices;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
    public CardListener getCardListener() {
        return cardListener;
    }
}


