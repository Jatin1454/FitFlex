package com.lightweight.fitflex;

public class WorkoutPlan {
    private Day[] days;

    public WorkoutPlan() {
        days = new Day[7];
    }

    public void addDay(String dayName, String muscleGroup, String exercises) {
        Day day = new Day(dayName, muscleGroup, exercises);
        for (int i = 0; i < 7; i++) {
            if (days[i] == null) {
                days[i] = day;
                break;
            }
        }
    }

    public Day getDay(int index) {
        return days[index];
    }

    public class Day {
        private String name;
        private String muscleGroup;
        private String exercises;

        public Day(String name, String muscleGroup, String exercises) {
            this.name = name;
            this.muscleGroup = muscleGroup;
            this.exercises = exercises;
        }

        public String getName() {
            return name;
        }

        public String getMuscleGroup() {
            return muscleGroup;
        }

        public String getExercises() {
            return exercises;
        }
    }
}
