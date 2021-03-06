package com.example.android.musicquizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * Global variables. One variable establish for each RadioGroup and RadioButton
     * to store selected value
     */
    private RadioGroup questionOneGroup;
    private RadioGroup questionTwoGroup;
    private RadioGroup questionThreeGroup;
    private RadioGroup questionFourGroup;
    private RadioGroup questionFiveGroup;

    private RadioButton questionOneAnswer;
    private RadioButton questionTwoAnswer;
    private RadioButton questionThreeAnswer;
    private RadioButton questionFourAnswer;
    private RadioButton questionFiveAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
    }


    /**
     * This method adds listener to submit button, which then retrieves the answers
     * provided by the user.
     */
    public void addListenerOnButton() {
        questionOneGroup = findViewById(R.id.question_one_answer);
        questionTwoGroup = findViewById(R.id.question_two_answer);
        questionThreeGroup = findViewById(R.id.question_three_answer);
        questionFourGroup = findViewById(R.id.question_four_answer);
        questionFiveGroup = findViewById(R.id.question_five_answer);

        Button submitBtn = findViewById(R.id.submit_btn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedIdQ1 = questionOneGroup.getCheckedRadioButtonId();
                int selectedIdQ2 = questionTwoGroup.getCheckedRadioButtonId();
                int selectedIdQ3 = questionThreeGroup.getCheckedRadioButtonId();
                int selectedIdQ4 = questionFourGroup.getCheckedRadioButtonId();
                int selectedIdQ5 = questionFiveGroup.getCheckedRadioButtonId();

                questionOneAnswer = findViewById(selectedIdQ1);
                questionTwoAnswer = findViewById(selectedIdQ2);
                questionThreeAnswer = findViewById(selectedIdQ3);
                questionFourAnswer = findViewById(selectedIdQ4);
                questionFiveAnswer = findViewById(selectedIdQ5);


                String answerQ1 = (String) questionOneAnswer.getText();
                String answerQ2 = (String) questionTwoAnswer.getText();
                String answerQ3 = (String) questionThreeAnswer.getText();
                String answerQ4 = (String) questionFourAnswer.getText();
                String answerQ5 = (String) questionFiveAnswer.getText();

                showResultsToast(totalCorrectAnswers(answerQ1, answerQ2, answerQ3, answerQ4, answerQ5, haydnResponse(), pucinniOperaTally()));
            }
        });
    }

    /**
     * This method retrieves value of name from EditText view associated with
     * question about Joseph Haydn
     */

    private String haydnResponse () {

        EditText getHaydnResponse = (EditText) findViewById(R.id.haydn_answer);
        String haydnAnswer = getHaydnResponse.getText().toString();

        return haydnAnswer;
    };

    /**
     * This method evaluates question seven and determines if a point should be awarded
     * for choosing the correct answers
     */

    private int pucinniOperaTally () {
        int checkBoxScore = 0;

        CheckBox getBoheme = findViewById(R.id.boheme);
        CheckBox getButterfly = findViewById(R.id.mad_butterfly);
        CheckBox getCarmen = findViewById(R.id.carmen);
        CheckBox getGiovanni = findViewById(R.id.giovanni);
        CheckBox getTosca = findViewById(R.id.tosca);
        CheckBox getRigolleto = findViewById(R.id.rigoletto);

        boolean isBohemeChecked = getBoheme.isChecked();
        boolean isButterflyChecked = getButterfly.isChecked();
        boolean isCarmenChecked = getCarmen.isChecked();
        boolean isToscaChecked = getTosca.isChecked();
        boolean isRigolletoChecked = getRigolleto.isChecked();
        boolean isGiovanniChecked = getGiovanni.isChecked();

        if (isBohemeChecked && isButterflyChecked && isToscaChecked && !isCarmenChecked && !isRigolletoChecked && !isGiovanniChecked) {
            checkBoxScore++;
        }

        return checkBoxScore;
    }

    /**
     * This method evaluates the responses by the user to determine the number of correct answers
     * and returns the total number correct.
     *
     * @param answerToQ1 Answer to question 1
     * @param answerToQ2 Answer to question 2
     * @param answerToQ3 Answer to question 3
     * @param answerToQ4 Answer to question 4
     * @param answerToQ5 Answer to question 5
     * @param answerToQ6 Calls method to provide answer to question 6 about Joseph Haydn
     * @param checkboxScore calls method to that returns value of checkbox question; will
     * return 0 or 1
     */

    public int totalCorrectAnswers (String answerToQ1, String answerToQ2, String answerToQ3, String answerToQ4, String answerToQ5, String answerToQ6, int checkboxScore) {
        String q1CorrectAnswer = "Hector Berlioz";
        String q2CorrectAnswer = "Johannes Brahms";
        String q3CorrectAnswer = "Rite of Spring";
        String q4CorrectAnswer = "Napoléon Bonaparte";
        String q5CorrectAnswer = "Matthew Passion";
        String q6CorrectAnswer = "107";

        int totalCount = 0;

        if (q1CorrectAnswer.equals(answerToQ1)) {
            totalCount+= 1;
        }

        if (q2CorrectAnswer.equals(answerToQ2)) {
            totalCount+= 1;
        }

        if (q3CorrectAnswer.equals(answerToQ3)) {
            totalCount+= 1;
        }

        if (q4CorrectAnswer.equals(answerToQ4)) {
            totalCount+= 1;
        }

        if (q5CorrectAnswer.equals(answerToQ5)) {
            totalCount+= 1;
        }

        if (q6CorrectAnswer.equals(answerToQ6)) {
            totalCount+= 1;
        }

        return totalCount + checkboxScore;
    }

    /**
     * This method evaluates the total correct responses to determine what message to present
     * in the Toast
     */
    private void showResultsToast(int totalCorrect) {
        String resultsMessage;

        if (totalCorrect == 7 ) {
            resultsMessage = "You answered 7/7 questions correctly. That's a perfect score!!";
        } else if (totalCorrect > 3 ) {
            resultsMessage = "You answered " + totalCorrect + "/7 correctly. Not bad! ";
        } else {
            resultsMessage = "You answered " + totalCorrect + "/7 correctly. Come on. You can do better!";
        }

        Toast.makeText(getApplicationContext(), resultsMessage, Toast.LENGTH_LONG).show();
    }
}
