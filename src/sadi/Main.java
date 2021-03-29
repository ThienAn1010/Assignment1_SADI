package sadi;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Populate data
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("s3825455","Nguyen Hoang Thien An","10/10/2001"));
        students.add(new Student("s3825456","Nguyen Dang Lam Phuong","04/12/2001"));
        students.add(new Student("s3825457","Nguyen Hoang Nam","10/05/2001"));
        students.add(new Student("s3825458","Pham Quang Man","06/09/2001"));
        students.add(new Student("s3825459","Nguyen Huu Luan","01/04/2001"));

        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("COSC1234","Intro to IT",12));
        courses.add(new Course("COSC1235","Intro to Programming",12));
        courses.add(new Course("COSC1236","UCD",12));
        courses.add(new Course("COSC1237","SADI",12));
        courses.add(new Course("COSC1238","SEPM",12));



        for (Student student: students) {
            System.out.println(student);
        }
        for (Course course: courses) {
            System.out.println(course);
        }
    }
}
