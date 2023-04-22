package com.example.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class TriviaCardAdapter extends RecyclerView.Adapter<TriviaCardAdapter.TriviaViewHolder> {

    private List<TriviaCard> triviaCards;
    private OnAnswerSelectedListener onAnswerSelectedListener;
    private RecyclerView recyclerView;


    public TriviaCardAdapter(List<TriviaCard> triviaCards, RecyclerView recyclerView, OnAnswerSelectedListener onAnswerSelectedListener) {
        this.triviaCards = triviaCards;
        this.onAnswerSelectedListener = onAnswerSelectedListener;
        this.recyclerView = recyclerView;

    }

    public int getCurrentCard() {
        int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        return position;
    }

    @NonNull
    @Override
    public TriviaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new TriviaViewHolder(view, onAnswerSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TriviaViewHolder holder, int position) {
        holder.bind(triviaCards.get(position));
    }

    @Override
    public int getItemCount() {
        return triviaCards.size();
    }

    public interface OnAnswerSelectedListener {
        void onAnswerSelected(int position, int selectedAnswerIndex);
    }

    public static class TriviaViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener {

        private TextView questionTextView;
        private RadioButton answer1RadioButton;
        private RadioButton answer2RadioButton;
        private RadioButton answer3RadioButton;
        private RadioButton answer4RadioButton;
        private ImageView imageView;
        private RadioGroup answerRadioGroup;
        private OnAnswerSelectedListener onAnswerSelectedListener;
        private TriviaCard triviaCard;

        public TriviaViewHolder(@NonNull View itemView, OnAnswerSelectedListener onAnswerSelectedListener) {
            super(itemView);
            this.onAnswerSelectedListener = onAnswerSelectedListener;

            // init views
            questionTextView = itemView.findViewById(R.id.question_textview);
            answer1RadioButton = itemView.findViewById(R.id.answer1_radiobutton);
            answer2RadioButton = itemView.findViewById(R.id.answer2_radiobutton);
            answer3RadioButton = itemView.findViewById(R.id.answer3_radiobutton);
            answer4RadioButton = itemView.findViewById(R.id.answer4_radiobutton);
            imageView = itemView.findViewById(R.id.card_image);

            //init the RadioGroup
            answerRadioGroup = itemView.findViewById(R.id.answer_radiogroup);
            answerRadioGroup.setOnCheckedChangeListener(this);
        }

        public void bind(TriviaCard triviaCard) {
            this.triviaCard = triviaCard;

            //set card question
            questionTextView.setText(triviaCard.getQuestion());

            //set answers
            List<String> answerChoices = triviaCard.getAnswerChoices();
            answer1RadioButton.setText(answerChoices.get(0));
            answer2RadioButton.setText(answerChoices.get(1));
            answer3RadioButton.setText(answerChoices.get(2));
            answer4RadioButton.setText(answerChoices.get(3));

            RequestOptions requestOptions = new RequestOptions().override(150, 150);
            //set image
            Glide.with(itemView)
                    .load(triviaCard.getImageUrl())
                    .apply(requestOptions)
                    .into(imageView);

            //set selected answer
            if (triviaCard.isAnswered()) {
                answerRadioGroup.check(answerRadioGroup.getChildAt(triviaCard.getSelectedAnswerIndex()).getId());
            } else {
                answerRadioGroup.clearCheck();
            }
        }

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
            // Get the selected RadioButton
            RadioButton selectedRadioButton = itemView.findViewById(checkedId);
            if(selectedRadioButton != null) {
                // Get the index of the selected RadioButton
                int selectedAnswerIndex = answerRadioGroup.indexOfChild(selectedRadioButton);
                // Update the TriviaCard with the selected answer
                triviaCard.setSelectedAnswerIndex(selectedAnswerIndex);
                triviaCard.setAnswered(true);
                triviaCard.setSelectedAnswer(selectedRadioButton.getText().toString());

                // notify the listener
                onAnswerSelectedListener.onAnswerSelected(getAdapterPosition(), selectedAnswerIndex);
            }
        }
    }
}



