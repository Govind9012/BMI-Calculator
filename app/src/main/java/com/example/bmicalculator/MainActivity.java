package com.example.bmicalculator;
import java.math.RoundingMode;   // this import is done to handle the floating values
import java.text.DecimalFormat;    // (this import is new for me )

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.internal.TextWatcherAdapter;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText idWeight;
    private EditText idHeight;
    private TextView showText;
    private TextView textView4;


    public MainActivity() {
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        idWeight = findViewById(R.id.idWeight);
        idHeight = findViewById(R.id.idHeight);
        showText = findViewById(R.id.showText);
        textView4 = findViewById(R.id.textView4);

// here submit text watcher is used to create a barrier for submit button so that user can not click the button untill he filled out weight and height

        idWeight.addTextChangedListener(submitTextWatcher);
        idHeight.addTextChangedListener(submitTextWatcher);


// This is main logic of the programm which calculates the BMI and prints it .
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String w = idWeight.getText().toString();
                float weight = Float.parseFloat(w);        // parsing String in float
                String h = idHeight.getText().toString();
                float height = Float.parseFloat(h);

                double bmi = (weight/(height*height));
                DecimalFormat df = new DecimalFormat("#.##");                 // this is used to control decimal places in float values
//                System.out.println("the value of a = "+ df.format(bmi));          // here i used df.format(bmi) method

                if (bmi>10 && bmi<17){
                    showText.setText("Your BMI Is = " + df.format(bmi) +"\n Condition : Thinner \n Advice : Follow A Strict Diet Plan");
                    textView4.setText("Food Suggestions :\n" + "👉 Don’t drink water before eating \n" +
                            "👉 Consume dairy products like milk, curd.\n" +
                            "👉 Eat in a small gaps");
                }
               else if (bmi>=17 && bmi<=18.5){
                    showText.setText(" Your BMI Is = " + df.format(bmi) +"\n Condition : Thin \n Advice : Eat High Carbohydrate Food");
                    textView4.setText("Food Suggestions :\n"+
                            "👉 Milk and Milk products.\n" +
                            "👉 Sleep tight\n"+ "👉 Do weight lifting exercises\n"+"👉 Eat Starchy vegetables like potatoes");
                }
                else if (bmi>18.5 && bmi <=25){
                    showText.setText(" Your BMI Is = " + df.format(bmi) +"\n Condition : Fit \n Advice : Maintain Your Workout Routine");
                    textView4.setText("Food Suggestions :\n" + "👉 Drink 3L Water For Healthy Skin \n" +
                            "👉 Eat Healthy Food\n" +
                            "👉 Consume Heavy Break-Fast");
                }
                else if (bmi>25 && bmi<=30){
                    showText.setText(" Your BMI Is = " + df.format(bmi) +"\n Condition : Over-Weight \n Advice : Follow 'reduced-calorie' diet and exercise regularly");
                    textView4.setText("Food Suggestions :\n" + "👉 Avoid Sugar Products \n" +
                            "👉 Eat Seasonal Fruits.\n" +
                            "👉 Do-not Eat Non-Veg");
                }
                else if (bmi>30 && bmi <45){
                    showText.setText(" Your BMI Is = " + df.format(bmi) +"\n Condition :Obese \n Advice : Do Running Regularly");
                    textView4.setText("Food Suggestions :\n" + "👉 Do-not Eat Sugar \n" +
                            "👉 Stop Drinking Cold-Drinks and Fast-Foods\n" +
                            "👉 Eat Salad and Light Food");
                }


            }
        });


    }
    // this is another function created so that no user can press "check BMI" button untill the weight and height are filled .
    private TextWatcher submitTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String w = idWeight.getText().toString().trim();

            String h = idHeight.getText().toString().trim();

            button.setEnabled(!w.isEmpty() && !h.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}