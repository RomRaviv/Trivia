package com.example.common;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class TriviaCard {

    private View cardView;
    private TextView questionTextView;
    private RadioGroup answerRadioGroup;
    private RadioButton answer1RadioButton;
    private RadioButton answer2RadioButton;
    private RadioButton answer3RadioButton;
    private RadioButton answer4RadioButton;
    private ImageView imageView;

    public TriviaCard(View cardView) {
        this.cardView = cardView;
        questionTextView = cardView.findViewById(R.id.question_textview);
        answerRadioGroup = cardView.findViewById(R.id.answer_radiogroup);
        answer1RadioButton = cardView.findViewById(R.id.answer1_radiobutton);
        answer2RadioButton = cardView.findViewById(R.id.answer2_radiobutton);
        answer3RadioButton = cardView.findViewById(R.id.answer3_radiobutton);
        answer4RadioButton = cardView.findViewById(R.id.answer4_radiobutton);
        imageView = cardView.findViewById(R.id.card_image);

    }

    public void setQuestion(String question) {
        questionTextView.setText(question);
    }

    public void setAnswerChoices(String[] answerChoices) {
        answer1RadioButton.setText(answerChoices[0]);
        answer2RadioButton.setText(answerChoices[1]);
        answer3RadioButton.setText(answerChoices[2]);
        answer4RadioButton.setText(answerChoices[3]);
    }

    public int getSelectedAnswerIndex() {
        int selectedRadioButtonId = answerRadioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId == R.id.answer1_radiobutton) {
            return 0;
        } else if (selectedRadioButtonId == R.id.answer2_radiobutton) {
            return 1;
        } else if (selectedRadioButtonId == R.id.answer3_radiobutton) {
            return 2;
        } else if (selectedRadioButtonId == R.id.answer4_radiobutton) {
            return 3;
        }
        return -1;
    }

    public void setSelectedAnswerIndex(int answerIndex) {
        switch (answerIndex) {
            case 0:
                answer1RadioButton.setChecked(true);
                break;
            case 1:
                answer2RadioButton.setChecked(true);
                break;
            case 2:
                answer3RadioButton.setChecked(true);
                break;
            case 3:
                answer4RadioButton.setChecked(true);
                break;
        }
    }

    public void disableAnswerChoices() {
        answer1RadioButton.setEnabled(false);
        answer2RadioButton.setEnabled(false);
        answer3RadioButton.setEnabled(false);
        answer4RadioButton.setEnabled(false);
    }

    public void enableAnswerChoices() {
        answer1RadioButton.setEnabled(true);
        answer2RadioButton.setEnabled(true);
        answer3RadioButton.setEnabled(true);
        answer4RadioButton.setEnabled(true);
    }

    public void setImage(Context context, String imageUrl) {
        Glide.with(context)
                .load(imageUrl)
                .into(imageView);
    }

}


