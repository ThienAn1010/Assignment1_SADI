package sadi;

import java.util.ArrayList;

public class Course implements StudentEnrolmentManager{
    private String id;
    private String name;
    private int numberOfCredits;
    private ArrayList<Student> enrolledStudents;

    public Course(String id, String name, int numberOfCredits) {
        this.id = id;
        this.name = name;
        this.numberOfCredits = numberOfCredits;
        enrolledStudents = new ArrayList<Student>();
    }

    public String getName() {
        return name;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    @Override
    public String toString() {
        return "Course name: " + name +
                " | ID: " + id  +
                " | Number of credits: " + numberOfCredits
                ;
    }

    @Override
    public void add() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void getOne() {

    }

    @Override
    public void getAll() {

    }
}
