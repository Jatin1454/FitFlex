package com.lightweight.fitflex;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CustomWorkoutActivity extends AppCompatActivity {
    private EditText etAge, etWeight;
    private RadioGroup rgPhysique;
    private RadioButton rbLean, rbMuscular, rbToned;
    private Button btnGeneratePlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_custom_workout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etAge = findViewById(R.id.et_age);
        etWeight = findViewById(R.id.et_weight);
        rgPhysique = findViewById(R.id.rg_physique);
        rbLean = findViewById(R.id.rb_lean);
        rbMuscular = findViewById(R.id.rb_muscular);
        rbToned = findViewById(R.id.rb_toned);
        btnGeneratePlan = findViewById(R.id.btn_generate_plan);

        btnGeneratePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = Integer.parseInt(etAge.getText().toString());
                double weight = Double.parseDouble(etWeight.getText().toString());
                String physique = getSelectedPhysique();

                WorkoutPlan plan = generateWorkoutPlan(age, weight, physique);
                if (!physique.isEmpty()) {
                    displayWorkoutPlan(plan);
                }
            }
        });
    }

    private String getSelectedPhysique() {
        int selectedId = rgPhysique.getCheckedRadioButtonId();
        if (selectedId == rbLean.getId()) {
            return "Lean";
        } else if (selectedId == rbMuscular.getId()) {
            return "Muscular";
        } else {
            return "Toned";
        }
    }

    private WorkoutPlan generateWorkoutPlan(int age, double weight, String physique) {
        // Implement your workout plan generation logic here
        // Based on user input, generate a custom workout plan for a week
        // For simplicity, let's assume we have a predefined plan for each physique type
        WorkoutPlan plan = new WorkoutPlan();
        if (physique.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        } else if (physique.equals("Lean")) {
            plan.addDay("Monday", "Chest and Triceps", "Bench Press, Tricep Pushdown, Tricep Dips");
            plan.addDay("Tuesday", "Back and Biceps", "Pull-ups, Barbell Rows, Dumbbell Bicep Curls");
            plan.addDay("Wednesday", "Rest Day", "");
            plan.addDay("Thursday", "Legs", "Squats, Leg Press, Lunges");
            plan.addDay("Friday", "Shoulders and Abs", "Shoulder Press, Lateral Raises, Planks");
            plan.addDay("Saturday", "Rest Day", "");
            plan.addDay("Sunday", "Cardio", "30 minutes of jogging or cycling");
        } else if (physique.equals("Muscular")) {
            plan.addDay("Monday", "Back and Biceps", "Deadlifts, Bent-over Barbell Rows, Dumbbell Hammer Curls");
            plan.addDay("Tuesday", "Chest and Triceps", "Bench Press, Incline Dumbbell Press, Tricep Extensions");
            plan.addDay("Wednesday", "Rest Day", "");
            plan.addDay("Thursday", "Legs", "Squats, Leg Press, Leg Extensions");
            plan.addDay("Friday", "Shoulders and Abs", "Shoulder Press, Rear Delt Fly, Russian Twists");
            plan.addDay("Saturday", "Rest Day", "");
            plan.addDay("Sunday", "Cardio", "30 minutes of jogging or cycling");
        } else {
            plan.addDay("Monday", "Chest and Triceps", "Push-ups, Tricep Dips, Overhead Dumbbell Extension");
            plan.addDay("Tuesday", "Back and Biceps", "Bodyweight Rows, Bicep Curls, Tricep Kickbacks");
            plan.addDay("Wednesday", "Rest Day", "");
            plan.addDay("Thursday", "Legs", "Squats, Calf Raises, Leg Press");
            plan.addDay("Friday", "Shoulders and Abs", "Shoulder Rolls, Planks, Russian Twists");
            plan.addDay("Saturday", "Rest Day", "");
            plan.addDay("Sunday", "Cardio", "30 minutes of jogging or cycling");
        }

        return plan;
    }

    private void displayWorkoutPlan(WorkoutPlan plan) {
        String planText = "";
        for (int i = 0; i < 7; i++) {
            planText += "Day " + (i + 1) + ": " + plan.getDay(i).getName() + "\n";
            if (plan.getDay(i).getMuscleGroup().equals("Rest Day")) {
                planText += "No exercises today";
            } else {
                planText += "Muscle Group: " + plan.getDay(i).getMuscleGroup() + "\n";
                planText += "Exercises: " + plan.getDay(i).getExercises() + "\n\n";
            }
        }
        Toast.makeText(this, planText, Toast.LENGTH_LONG).show();
    }

}