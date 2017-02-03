package com.example.lucyxiao.crunchtime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    NumberFormat formatter = new DecimalFormat("#0.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void update(View view) {
        String[] temp = {"SITUP", "PUSHUP", "JUMPING JACKS", "JOGGING"};
        List valid = Arrays.asList(temp);

        EditText editText_rep = (EditText) findViewById(R.id.editText_reps);
        EditText editText_exercise = (EditText) findViewById(R.id.editText_exercise);
        String exer = editText_exercise.getText().toString();
        if ((exer.matches(""))) {
            Toast.makeText(getApplication().getBaseContext(), "You did not enter an exercise.", Toast.LENGTH_SHORT).show();
        } else if (editText_rep.getText().toString().matches("")){
            Toast.makeText(getApplication().getBaseContext(), "You did not enter a number.", Toast.LENGTH_SHORT).show();
        } else if (!(valid.contains(exer.toUpperCase()))) {
            Toast.makeText(getApplication().getBaseContext(), "Entered exercise not found.", Toast.LENGTH_SHORT).show();
        } else {
            int reps = Integer.parseInt(editText_rep.getText().toString());
            updateValues(reps);
        }
    }

    private void updateValues(int reps) {
        TextView calories = (TextView) findViewById(R.id.calories);
        TextView pushupRep = (TextView) findViewById(R.id.pushup_rep);
        TextView situpRep = (TextView) findViewById(R.id.situp_rep);
        TextView jumpMin = (TextView) findViewById(R.id.jump_min);
        TextView jogMin = (TextView) findViewById(R.id.jog_min);
        EditText editText_rep = (EditText) findViewById(R.id.editText_reps);
        EditText editText_exercise = (EditText) findViewById(R.id.editText_exercise);
        String exer = editText_exercise.getText().toString().toUpperCase();
        double ratio = 1;
        Double cal = ratio*reps;
        if (exer.equals("PUSHUP")) {
            ratio = 100/350.0;
            cal = ratio*reps;
            calories.setText("Burned "+ formatter.format(cal) + " Calories");
            pushupRep.setText("" + formatter.format(reps) + " Reps");
            situpRep.setText("" + formatter.format(cal/(100/200.0)) + " Reps");
            jumpMin.setText("" + formatter.format(cal/(100/10.0)) + " Min");
            jogMin.setText("" + formatter.format(cal/(100/12.0)) + " Min");
        } else if (exer.equals("SITUP")) {
            ratio = 100/200.0;
            cal = ratio*reps;
            calories.setText("Burned "+ formatter.format(cal) + " Calories");
            situpRep.setText("" + formatter.format(reps) + " Reps");
            pushupRep.setText(""+ formatter.format(cal/(100/350.0)) + " Reps");
            jumpMin.setText("" + formatter.format(cal/(100/10.0)) + " Min");
            jogMin.setText("" + formatter.format(cal/(100/12.0)) + " Min");
        } else if (exer.equals("JUMPING JACKS")) {
            ratio = 100/10.0;
            cal = ratio*reps;
            calories.setText("Burned "+ formatter.format(cal) + " Calories");
            jumpMin.setText("" + formatter.format(reps) + " Min");
            pushupRep.setText(""+ formatter.format(cal/(100/350.0)) + " Reps");
            situpRep.setText("" + formatter.format(cal/(100/200.0)) + " Reps");
            jogMin.setText("" + formatter.format(cal/(100/12.0)) + " Min");
        } else if (exer.equals("JOGGING")) {
            ratio = 100/12.0;
            cal = ratio*reps;
            calories.setText("Burned "+ formatter.format(cal) + " Calories");
            jogMin.setText(""+formatter.format(reps) + " Min");
            pushupRep.setText("" + formatter.format(reps) + " Reps");
            situpRep.setText("" + formatter.format(cal/(100/200.0)) + " Reps");
            jumpMin.setText("" + formatter.format(cal/(100/10.0)) + " Min");
        }

    }
}
