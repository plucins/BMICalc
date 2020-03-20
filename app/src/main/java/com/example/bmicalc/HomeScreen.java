package com.example.bmicalc;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeScreen extends AppCompatActivity {


    private EditText height;
    private EditText weight;
    private EditText age;
    private TextView result;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        age = findViewById(R.id.age);
        result = findViewById(R.id.result);


    }


    public void calculateBMI(View v) {
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();
        String ageStr = age.getText().toString();

        if (heightStr != null && !"".equals(heightStr)
                && weightStr != null && !"".equals(weightStr)) {

            float heightValue = Float.parseFloat(heightStr) / 100;
            float weightValue = Float.parseFloat(weightStr);

            float bmi = weightValue / (heightValue * heightValue);

            String sex = addListenerOnButton();
            String ppm = calculatePPM(heightStr, weightStr, ageStr, sex);

            displayBMI(bmi, ppm);
        }
    }

    private String calculatePPM(String heightStr, String weightStr, String ageStr, String sex) {
        int height = Integer.getInteger(heightStr);
        int weight = Integer.getInteger(weightStr);
        int age = Integer.getInteger(ageStr);
        double result = 0;


        if(sex.equals("Kobieta")) {
            result = 655.1 + (9.563 * weight) + (1.85 * height) - (4.676 * age);
        }else if(sex.equals("Mężczyzna")) {
            result = 66.5 + (13.75 * weight) + (5.003 * height) - (6.775 * age);
        }

        return String.valueOf(result);
    }

    private void displayBMI(float bmi, String ppm) {
        String bmiLabel = "";

        if (Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.wyglodzenie);
        } else if (Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.wychudzenie);
        } else if (Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.niedowaga);
        } else if (Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.wartosc_prawidlowa);
        } else if (Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.nadwaga);
        } else if (Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.I_stopien_otylosci);
        } else if (Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.II_stopien_otylosci);
        } else {
            bmiLabel = getString(R.string.otyłosc_skrajna);
        }

        bmiLabel = bmi + "\n\n" + bmiLabel + "\n\n" + "PPM" + "\n\n" + ppm;
        result.setText(bmiLabel);
    }

    public String addListenerOnButton() {

        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);


        // get selected radio button from radioGroup
        int selectedId = radioSexGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioSexButton = (RadioButton) findViewById(selectedId);

        return radioSexButton.getText().toString();

    }


}
