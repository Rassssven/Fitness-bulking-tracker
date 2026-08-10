package proiect.demo.web.ang_spring.DTO.ExerciseDTOs;

import java.time.LocalDateTime;

public class SavedExerciseResponseDTO {

    private Long savedId;
    private Long exerciseId;
    private String name;
    private String type;
    private int caloriesPerExercise;
    private String description;
    private String muscleGroup;
    private LocalDateTime savedAt;

    public SavedExerciseResponseDTO() {
    }

    public SavedExerciseResponseDTO(Long savedId, Long exerciseId, String name,
                                 String type, int caloriesPerExercise,
                                 String description, String muscleGroup,
                                 LocalDateTime savedAt) {
        this.savedId = savedId;
        this.exerciseId = exerciseId;
        this.name = name;
        this.type = type;
        this.caloriesPerExercise = caloriesPerExercise;
        this.description = description;
        this.muscleGroup = muscleGroup;
        this.savedAt = savedAt;
    }

    public Long getSavedId() {
        return savedId;
    }

    public void setSavedId(Long savedId) {
        this.savedId = savedId;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCaloriesPerExercise() {
        return caloriesPerExercise;
    }

    public void setCaloriesPerExercise(int caloriesPerExercise) {
        this.caloriesPerExercise = caloriesPerExercise;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }
}