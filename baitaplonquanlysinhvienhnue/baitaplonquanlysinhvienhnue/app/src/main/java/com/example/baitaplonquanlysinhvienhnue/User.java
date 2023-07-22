package com.example.baitaplonquanlysinhvienhnue;

public class User {
    private int image;
    private String StudentID;
    private String fullName;
    private String className;
    private String date;

    public User(int image, String studentID, String fullName, String className, String date) {
        this.image = image;
        StudentID = studentID;
        this.fullName = fullName;
        this.className = className;
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
