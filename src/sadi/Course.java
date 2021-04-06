package sadi;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String id;
    private String name;
    private int numberOfCredits;
    private List<Course> courses;

    public Course(String id, String name, int numberOfCredits) {
        this.id = id;
        this.name = name;
        this.numberOfCredits = numberOfCredits;
    }

    public Course() {
        courses = new ArrayList<>();
        courses.add(new Course("COSC1234", "Intro to IT", 12));
        courses.add(new Course("COSC1235", "Intro to Programming", 12));
        courses.add(new Course("COSC1236", "UCD", 12));
        courses.add(new Course("COSC1237", "SADI", 12));
        courses.add(new Course("COSC1238", "SEPM", 12));
        courses.add(new Course("COSC1111", "Web Programming", 12));
        courses.add(new Course("COSC2222", "Cloud Computing", 12));
        courses.add(new Course("COSC3333", "Machine Learning", 12));
        courses.add(new Course("COSC4444", "AI", 12));
        courses.add(new Course("COSC5555", "Programming 1", 12));
    }

    public String getName() {
        return name;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Course name: " + name +
                " | ID: " + id  +
                " | Number of credits: " + numberOfCredits
                ;
    }

}
