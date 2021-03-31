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
        Ultility.clearscr();
        System.out.println("*****************************");
        System.out.println("NEW ENROLLMENT");
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
        System.out.println("Press \"Enter\" to exit");
        sc.nextLine();
    }

    public static void updateEnrollment(){
        System.out.println("test");
        sc.nextLine();
    }

    public static void printEnrollment() {
        int choice;
        do {
            Ultility.clearscr();
            System.out.println("*****************************");
            System.out.println("Choose what you want to print");
            System.out.println("1. Course list");
            System.out.println("2. Student list");
            System.out.println("3. Enrollment list");
            System.out.println("4. Return");
            System.out.println("*****************************");
            System.out.print("choose a command to execute: ");
            choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("1");
                choice = 0;
            }

            if (choice == 2) {
                System.out.println("2");
                choice = 0;
            }

            if (choice == 3) {
                Ultility.clearscr();
                sc.nextLine();
                try {
                    studentEnrollmentDatabase.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                studentEnrollmentDatabase.getAll();
                System.out.println("Press \"Enter\" to return");
                sc.nextLine();

                choice = 0;
            }
        } while (choice != 4);
        System.out.println("Press \"Enter\" to return");
        sc.nextLine();

    }









    @Override
    public String toString() {
        return
                "StudentID: " + studentID + " " +
                "| CourseID: " + courseID + " "   +
                "| semester: " + semester
                ;
    }


}




