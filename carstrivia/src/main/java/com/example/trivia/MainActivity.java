package com.example.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.common.TriviaActivity;
import com.example.common.TriviaCard;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MainActivity extends TriviaActivity {

    private List<Map.Entry<String,String>> carsInfo;
    private List<String> carNames;

    @Override
    protected void initCards() {
        carNames = getCarsNames();
        carsInfo = new ArrayList<>(getCarsInfo().entrySet());
    }

    @Override
    protected String getCardImgUrl(int i) {
        return carsInfo.get(i).getValue();
    }

    @Override
    protected int getNumberOfCards() {

        return carsInfo.size();
    }

    @Override
    protected String getCorrectAnswer(int index) {
        return carsInfo.get(index).getKey();
    }

    @Override
    protected String getQuestion(int index) {
        return "Name the brand!";
    }

    @Override
    protected ArrayList<String> getAnswerChoices(int index) {
        ArrayList<String> answers = new ArrayList<>();
        String correctName = carsInfo.get(index).getKey();
        answers.add(correctName);
        for(int i = 0; i < 3; i++){
            String name = carNames.get(new Random().nextInt(carNames.size()));
            while(name.equalsIgnoreCase(correctName) || answers.contains(name))
                name = carNames.get(new Random().nextInt(carNames.size()));
            answers.add(name);
        }
        Collections.shuffle(answers);

        return answers;
    }

    @Override
    protected Boolean onNextButtonClicked(int index) {
        TriviaCard triviaCard = triviaCards.get(index);
        String selectedAnswer = triviaCard.getSelectedAnswer();
        if(!selectedAnswer.equals("")) {
            triviaCard.getCardListener().onNextClick(selectedAnswer);
            return true;
        }
        else {
            Toast.makeText(this, "Pick An Answer First!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private ArrayList<String> getCarsNames(){
        ArrayList<String> carBrands = new ArrayList<String>();
        carBrands.add("Audi");
        carBrands.add("BMW");
        carBrands.add("Buick");
        carBrands.add("Cadillac");
        carBrands.add("Chevrolet");
        carBrands.add("Chrysler");
        carBrands.add("Dodge");
        carBrands.add("Ford");
        carBrands.add("GMC");
        carBrands.add("Honda");
        carBrands.add("Hyundai");
        carBrands.add("Infiniti");
        carBrands.add("Jaguar");
        carBrands.add("Jeep");
        carBrands.add("Kia");
        carBrands.add("Land Rover");
        carBrands.add("Lexus");
        carBrands.add("Lincoln");
        carBrands.add("Mazda");
        carBrands.add("Mercedes-Benz");
        carBrands.add("Mitsubishi");
        carBrands.add("Nissan");
        carBrands.add("Porsche");
        carBrands.add("RAM");
        carBrands.add("Subaru");
        carBrands.add("Tesla");
        carBrands.add("Toyota");
        carBrands.add("Volkswagen");
        carBrands.add("Volvo");
        return carBrands;
    }

    public Map<String, String> getCarsInfo(){
        Map<String, String> carBrands = new HashMap<>();
        carBrands.put("Audi", "https://logo.clearbit.com/audi.com");
        carBrands.put("BMW", "https://logo.clearbit.com/bmwusa.com");
        carBrands.put("Chevrolet", "https://logo.clearbit.com/chevrolet.com");
        carBrands.put("Dodge", "https://logo.clearbit.com/dodge.com");
        carBrands.put("Ferrari", "https://logo.clearbit.com/ferrari.com");
        carBrands.put("Ford", "https://logo.clearbit.com/ford.com");
        carBrands.put("GMC", "https://logo.clearbit.com/gmc.com");
        carBrands.put("Honda", "https://logo.clearbit.com/honda.com");
        carBrands.put("Hyundai", "https://logo.clearbit.com/hyundaiusa.com");
        carBrands.put("Jaguar", "https://logo.clearbit.com/jaguarusa.com");
        carBrands.put("Jeep", "https://logo.clearbit.com/jeep.com");
        carBrands.put("Kia", "https://logo.clearbit.com/kia.com");
        carBrands.put("Lamborghini", "https://logo.clearbit.com/lamborghini.com");
        carBrands.put("Land Rover", "https://logo.clearbit.com/landroverusa.com");
        carBrands.put("Lexus", "https://logo.clearbit.com/lexus.com");
        carBrands.put("Mazda", "https://logo.clearbit.com/mazdausa.com");
        carBrands.put("Mercedes-Benz", "https://logo.clearbit.com/mercedes-benz.com");
        carBrands.put("Mitsubishi", "https://logo.clearbit.com/mitsubishicars.com");
        carBrands.put("Nissan", "https://logo.clearbit.com/nissanusa.com");
        carBrands.put("Porsche", "https://logo.clearbit.com/porsche.com");
        carBrands.put("Subaru", "https://logo.clearbit.com/subaru.com");
        carBrands.put("Tesla", "https://logo.clearbit.com/tesla.com");
        carBrands.put("Toyota", "https://logo.clearbit.com/toyota.com");
        carBrands.put("Volkswagen", "https://logo.clearbit.com/vw.com");
        carBrands.put("Volvo", "https://logo.clearbit.com/volvo.com");
        carBrands.put("Acura", "https://logo.clearbit.com/acura.com");
        carBrands.put("Buick", "https://logo.clearbit.com/buick.com");
        carBrands.put("Cadillac", "https://logo.clearbit.com/cadillac.com");
        carBrands.put("Chrysler", "https://logo.clearbit.com/chrysler.com");
        carBrands.put("Infiniti", "https://logo.clearbit.com/infiniti.com");
        return carBrands;
    }
}