package org.vedy.mypolicies.Model;

import java.io.Serializable;

public class Policy implements Serializable {
    private int AgeId;
    private String Apply;
    private int CategoryId;
    private String Description;
    private int GenderId;
    private int Id;
    private String ImagePath;
    private int StateId;
    private String Title;
    private int Year;

    public Policy(int ageId, String apply, int categoryId, String description, int genderId, int id, String imagePath, int stateId, String title, int year) {
        AgeId = ageId;
        Apply = apply;
        CategoryId = categoryId;
        Description = description;
        GenderId = genderId;
        Id = id;
        ImagePath = imagePath;
        StateId = stateId;
        Title = title;
        Year = year;
    }

    public int getAgeId() {
        return AgeId;
    }

    public void setAgeId(int ageId) {
        AgeId = ageId;
    }

    public String getApply() {
        return Apply;
    }

    public void setApply(String apply) {
        Apply = apply;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getGenderId() {
        return GenderId;
    }

    public void setGenderId(int genderId) {
        GenderId = genderId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public int getStateId() {
        return StateId;
    }

    public void setStateId(int stateId) {
        StateId = stateId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public Policy() {
    }
}
