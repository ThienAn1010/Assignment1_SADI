package sadi;

import java.io.IOException;
import java.util.Scanner;

public class StudentEnrollment {
    static StudentEnrollmentDatabase studentEnrollmentDatabase = new StudentEnrollmentDatabase("Student enrollments.csv");
    private String studentID;
    private String courseID;
    private String semester;
    static Scanner sc = new Scanner(System.in);

    public StudentEnrollment(String studentID, String courseID, String semester) throws IOException {
        this.studentID = studentID;
        this.courseID = courseID;
        this.semester = semester;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getSemester() {
        return semester;
    }


    public static void enrollStudent() throws IOException {
        String newStudentID;
        String newCourse;
        String newSemester;
        try {
            studentEnrollmentDatabase.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("Please enter the student ID: ");
        newStudentID = sc.nextLine();
        System.out.print("Please enter the course ID: ");
        newCourse = sc.nextLine();
        System.out.print("Please enter the semester: ");
        newSemester = sc.nextLine();
        studentEnrollmentDatabase.add(newStudentID,newCourse,newSemester);
        try {
            studentEnrollmentDatabase.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Press \"0\" to exit");
        sc.nextLine();
    }

    public static void updateEnrollment(){
        System.out.println("test");
        sc.nextLine();
    }

    public static void printEnrollment() {
        System.out.println("test 3");
        sc.nextLine();
    }









    @Override
    public String toString() {
        return "StudentEnrolment{" +
                "studentID='" + studentID + '\'' +
                ", courseID='" + courseID + '\'' +
                ", semester='" + semester + '\'' +
                '}';
    }


}




