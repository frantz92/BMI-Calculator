package com.example.learning_xml_00;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // Class variables (also called 'Fields')
    private TextView resultTxt;
    private Button calculateBtn;
    private RadioButton maleRadio;
    private RadioButton femaleRadio;
    private EditText ageInput;
    private EditText feetInput;
    private EditText inchesInput;
    private EditText weightInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        resultTxt = findViewById(R.id.txt_view_result);
        maleRadio = findViewById(R.id.radio_btn_male);
        femaleRadio = findViewById(R.id.radio_btn_female);
        ageInput = findViewById(R.id.edit_txt_age);
        feetInput = findViewById(R.id.edit_txt_feet);
        inchesInput = findViewById(R.id.edit_txt_inches);
        weightInput = findViewById(R.id.edit_txt_weight);
        calculateBtn = findViewById(R.id.btn_calculate);
    }

    private void setupButtonClickListener() {
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmi = calculateBmi();

                String ageTxt = ageInput.getText().toString();
                int age = Integer.parseInt(ageTxt);

                if(age >= 18) {
                    displayResult(bmi);
                } else {
                    displayGuidance(bmi);
                }
            }
        });
    }

    private double calculateBmi() {
        String feetTxt = feetInput.getText().toString();
        String inchesTxt = inchesInput.getText().toString();
        String weightTxt = weightInput.getText().toString();

        int feet = Integer.parseInt(feetTxt);
        int inches = Integer.parseInt(inchesTxt);
        int weight = Integer.parseInt(weightTxt);

        int totalInches = (feet * 12) + inches;
        double heightInMeters = totalInches * 0.0254;

        return weight / (heightInMeters * heightInMeters);
    }

    private void displayResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTxt = myDecimalFormatter.format(bmi);

        String fullResult;

        if (bmi < 18.5) {
            fullResult = bmiTxt + "- You're underweight";
        } else if (bmi > 25) {
            fullResult = bmiTxt + "- You're overweight";
        } else {
            fullResult = bmiTxt + "- You're healthy weight";
        }

        resultTxt.setText(fullResult);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTxt = myDecimalFormatter.format(bmi);

        String fullResult;

        if(maleRadio.isChecked()) {
            fullResult = bmiTxt + "- You're under 18, so You have to consult with doctor for healthy range for boys";
        } else if (femaleRadio.isChecked()) {
            fullResult = bmiTxt + "- You're under 18, so You have to consult with doctor for healthy range for girls";
        } else {
            fullResult = bmiTxt + "- You're under 18, so You have to consult with doctor for healthy range";
        }

        resultTxt.setText(fullResult);
    }
}