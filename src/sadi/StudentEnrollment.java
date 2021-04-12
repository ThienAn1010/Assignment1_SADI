package sadi;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StudentEnrollment {
    static StudentEnrollmentDatabase studentEnrollmentDatabase = new StudentEnrollmentDatabase(Main.fileName);
    private static Student studentInfo;
    private static Course courseInfo;
    private String studentID;
    private String courseID;
    private String semester;
    private final String studentName;
    private final String courseName;
    private final String birthdate;
    private final int numberOfCredits;
    static Scanner sc = new Scanner(System.in);
    static Validator validator = new Validator();

    //////////////////////////////////////////////////////////////////////////////////
    public StudentEnrollment(String studentID, String studentName, String birthdate, String courseID, String courseName, int numberOfCredits, String semester) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.semester = semester;
        this.studentName = studentName;
        this.courseName = courseName;
        this.birthdate = birthdate;
        this.numberOfCredits = numberOfCredits;
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

    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    //////////////////////////////////////////////////////////////////////////////////
    /* ADD ENROLLMENT METHOD */
    public static void enrollStudent() {
        Student student = new Student();
        Course course = new Course();
        List<Course> courses = course.getCourses();
        List<Student> students = student.getStudents();
        try {
            studentEnrollmentDatabase.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Validator.clearscr();
        System.out.println("*****************************");
        System.out.println("NEW ENROLLMENT");
        System.out.println("press \"0\" to stop viewing");
        String newStudentID = validator.setSid();
        if (newStudentID.equals("0")) {
            return;
        }
        String newCourse = validator.setCid();
        if (newCourse.equals("0")) {
            return;
        }
        String newSemester = validator.setSem();
        if (newSemester.equals("0")) {
            return;
        }
        students.forEach(e -> {
            if (e.getId().equalsIgnoreCase(newStudentID)) {
                studentInfo = e;
            }
        });
        courses.forEach(e -> {
            if (e.getId().equalsIgnoreCase(newCourse)) {
                courseInfo = e;
            }
        });
        String studentName = studentInfo.getName();
        String studentBirthdate = studentInfo.getBirthdate();
        String courseName = courseInfo.getName();
        int coursePoint = courseInfo.getNumberOfCredits();

        studentEnrollmentDatabase.add(newStudentID, studentName, studentBirthdate, newCourse, courseName, coursePoint, newSemester);
        try {
            studentEnrollmentDatabase.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("*****************************");
        System.out.println("The enrollment has been added!");
        System.out.println("Press \"Enter\" to exit");
        sc.nextLine();
    }

    //////////////////////////////////////////////////////////////////////////////////
    public static void updateEnrollment() {
        int choice1;
        String choice2;
        int choice3;
        String newUpdate;

        do {
            Validator.clearscr();
            System.out.println("*****************************");
            System.out.println("UPDATE ENROLLMENT");
            System.out.println("1. Update enrollment detail");
            System.out.println("2. Add/Delete courses for student");
            System.out.println("3. Return");
            System.out.println("*****************************");
            choice1 = validator.choiceValidator(3);
            if (choice1 == 0) {
                return;
            }

            if (choice1 == 1) {
                Validator.clearscr();
                System.out.println("*****************************");
                System.out.println("UPDATE ENROLLMENT DETAIL");
                System.out.println("Press \"0\" to return");
                String updateSid = validator.setSid2();
                if (updateSid.equals("0")) {
                    return;
                }
                String updateCid = validator.setCid();
                if (updateCid.equals("0")) {
                    return;
                }
                try {
                    studentEnrollmentDatabase.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("*****************************");
                StudentEnrollment updateStudent = studentEnrollmentDatabase.detailEnrollment("student", updateSid, updateCid);
                if (updateStudent != null) {
                    System.out.println("Enrolment Information");
                    System.out.println(updateStudent);
                    System.out.println("Continue to update? Y/N");
                    choice2 = sc.nextLine();
                    while ((!choice2.equalsIgnoreCase("y")) && (!choice2.equalsIgnoreCase(("n")))) {
                        System.out.print("Please enter in the correct format: ");
                        choice2 = sc.nextLine();
                    }
                    if (choice2.equalsIgnoreCase("Y")) {
                        do {
                            Validator.clearscr();
                            System.out.println("*****************************");
                            System.out.println("Please choose what you want to update:");
                            System.out.println("1. Student ID");
                            System.out.println("2. Course ID");
                            System.out.println("3. Semester");
                            System.out.println("4. Return");
                            System.out.println("*****************************");
                            System.out.print("Please choose a command to execute: ");
                            choice3 = sc.nextInt();

                            if (choice3 == 1) {
                                Validator.clearscr();
                                sc.nextLine();
                                System.out.println("*****************************");
                                System.out.println("Student ID update");
                                System.out.print("Please enter a new student ID: ");
                                newUpdate = validator.setSid();
                                if (newUpdate.equals("0")) {
                                    return;
                                }
                                studentEnrollmentDatabase.update("sid", updateStudent, newUpdate);
                                try {
                                    studentEnrollmentDatabase.save();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("Press \"Enter\" to exit");
                            }

                            if (choice3 == 2) {
                                Validator.clearscr();
                                sc.nextLine();
                                System.out.println("*****************************");
                                System.out.println("Course ID update");
                                System.out.println("Press \"0\" to return");
                                System.out.print("Please enter a new course ID: ");
                                newUpdate = validator.setCid();
                                if (newUpdate.equals("0")) {
                                    return;
                                }
                                studentEnrollmentDatabase.update("cid", updateStudent, newUpdate);
                                try {
                                    studentEnrollmentDatabase.save();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("Press \"Enter\" to exit");
                            }

                            if (choice3 == 3) {
                                Validator.clearscr();
                                sc.nextLine();
                                System.out.println("*****************************");
                                System.out.println("Semester update");
                                System.out.print("Please enter a new semester: ");
                                newUpdate = validator.setSem();
                                if (newUpdate.equals("0")) {
                                    return;
                                }
                                studentEnrollmentDatabase.update("sem", updateStudent, newUpdate);
                                try {
                                    studentEnrollmentDatabase.save();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("Press \"Enter\" to exit");
                            }
                            sc.nextLine();
                        } while (choice3 != 4);
                    } else if ((choice2.equalsIgnoreCase("N"))) {
                        System.out.println("Press \"Enter\" to return");
                        sc.nextLine();
                    }
                } else {
                    System.err.println("There is no enrollment matches your input!");
                    System.err.println("Press \"Enter\" to return");
                    sc.nextLine();
                }
                choice1 = 0;
            }

            if (choice1 == 2) {
                Validator.clearscr();
                System.out.println("*****************************");
                System.out.println("UPDATE STUDENT'S COURSES");
                System.out.println("Press \"0\" to return");
                String newStudentID = validator.setSid2();
                if (newStudentID.equals("0")) {
                    return;
                }

                String semInput = validator.setSem();
                if (semInput.equals("0")) {
                    return;
                }
                try {
                    studentEnrollmentDatabase.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                StudentEnrollment updateStudent = studentEnrollmentDatabase.detailEnrollment("sem", newStudentID, semInput);
                if (updateStudent != null) {
                    System.out.println("*****************************");
                    System.out.println("Enrolment Information");
                    System.out.println(updateStudent);
                    System.out.println("*****************************");
                    System.out.println("Continue to update? Y/N");
                    choice2 = sc.nextLine();
                    while ((!choice2.equalsIgnoreCase("y")) && (!choice2.equalsIgnoreCase(("n")))) {
                        System.out.print("Please enter in the correct format: ");
                        choice2 = sc.nextLine();
                    }
                    if (choice2.equalsIgnoreCase("Y")) {
                        Validator.clearscr();
                        System.out.println("Select your action");
                        System.out.println("1.Add");
                        System.out.println("2.Delete");
                        System.out.print("choose a command to execute: ");
                        int choice4 = sc.nextInt();

                        if (choice4 == 1) {
                            System.out.println("*****************************");
                            System.out.println("ADD COURSE");
                            System.out.println("Press \"0\" to return");
                            String newCourse = validator.setCid();
                            if (newCourse.equals("0")) {
                                return;
                            }
                            Course course = new Course();
                            List<Course> courses = course.getCourses();
                            courses.forEach(e -> {
                                if (e.getId().equalsIgnoreCase(newCourse)) {
                                    courseInfo = e;
                                }
                            });
                            String courseName = courseInfo.getName();
                            int coursePoint = courseInfo.getNumberOfCredits();
                            studentEnrollmentDatabase.add(updateStudent.getStudentID(), updateStudent.getStudentName(), updateStudent.getBirthdate(), newCourse, courseName, coursePoint, semInput);
                            try {
                                studentEnrollmentDatabase.save();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sc.nextLine();
                            System.out.println("The enrollment has been updated!");
                            System.out.println("Press \"Enter\" to exit");
                            sc.nextLine();
                        }

                        if (choice4 == 2) {
                            System.out.println("*****************************");
                            System.out.println("DELETE COURSE");
                            System.out.println("Press \"0\" to return");
                            String newCourse = validator.setCid();
                            if (newCourse.equals("0")) {
                                return;
                            }
                            studentEnrollmentDatabase.delete(updateStudent);
                            try {
                                studentEnrollmentDatabase.save();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sc.nextLine();
                            System.out.println("The enrollment has been removed!");
                            System.out.println("Press \"Enter\" to exit");
                            sc.nextLine();
                        }


                    } else if ((choice2.equalsIgnoreCase("N"))) {
                        System.out.println("Press \"Enter\" to return");
                        sc.nextLine();
                    }
                } else {
                    System.err.println("There is no enrollment matches your input!");
                    System.err.println("Press \"Enter\" to return");
                    sc.nextLine();
                }
                choice1 = 0;
            }
        } while (choice1 != 3);
    }

    //////////////////////////////////////////////////////////////////////////////////
    public static void printEnrollment() {
        int choice;
        do {
            Validator.clearscr();
            try {
                studentEnrollmentDatabase.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("*****************************");
            System.out.println("Please choose what you want to print");
            System.out.println("1. Print all enrollments");
            System.out.println("2. Print individually");
            System.out.println("3. Return");
            System.out.println("*****************************");
            System.out.print("Please choose a command to execute: ");
            choice = sc.nextInt();

            if (choice == 1) {
                Validator.clearscr();
                sc.nextLine();
                System.out.println("ENROLLMENT LIST");
                System.out.println("*****************************");
                studentEnrollmentDatabase.getAll();
                System.out.println("*****************************");
                System.out.println("Press \"Enter\" to return");
                sc.nextLine();
                choice = 0;
            }

            if (choice == 2) {
                int choice2;
                do {
                    Validator.clearscr();
//                    sc.nextLine();
                    System.out.println("*****************************");
                    System.out.println("Please choose what you want to print");
                    System.out.println("1. One enrollment of one student");
                    System.out.println("2. One student's enrollment list");
                    System.out.println("3. One course's enrollment list");
                    System.out.println("4. All courses in one semester");
                    System.out.println("5. Return");
                    System.out.println("*****************************");
                    System.out.print("Please choose a command to execute: ");
                    choice2 = sc.nextInt();

                    if (choice2 == 1) {
                        Validator.clearscr();
                        System.out.println("*****************************");
                        System.out.println("Press \"0\" to return");
                        String studentId = validator.setSid();
                        if (studentId.equals("0")) {
                            return;
                        }

                        String courseInput = validator.setCid();
                        if (courseInput.equals("0")) {
                            return;
                        }
                        String semesterInput = validator.setSem();
                        if (semesterInput.equals("0")) {
                            return;
                        }
                        sc.nextLine();
                        StudentEnrollment student = studentEnrollmentDatabase.getOne(studentId, courseInput, semesterInput);
                        if (student != null) {
                            System.out.println(student);
                            System.out.println("Press \"Enter\" to return");
                        } else {
                            System.err.println("There is no enrollment matches your input!");
                            System.err.println("Press \"Enter\" to return");
                        }
                        sc.nextLine();
                        choice2 = 0;
                    }

                    if (choice2 == 2) {
                        Validator.clearscr();
                        sc.nextLine();
                        System.out.println("*****************************");
                        System.out.println("Press \"0\" to return");
                        String studentId = validator.setSid();
                        if (studentId.equals("0")) {
                            return;
                        }
                        studentEnrollmentDatabase.getStudentEnrollment(studentId);
                        sc.nextLine();
                        choice2 = 0;
                    }
                    if (choice2 == 3) {
                        Validator.clearscr();
                        sc.nextLine();
                        System.out.println("*****************************");
                        System.out.println("Press \"0\" to return");
                        String courseIdInput = validator.setCid();
                        if (courseIdInput.equals("0")) {
                            return;
                        }
                        studentEnrollmentDatabase.getCourseEnrollment(courseIdInput);
                        sc.nextLine();
                        choice2 = 0;

                    }
                    if (choice2 == 4) {
                        Validator.clearscr();
                        sc.nextLine();
                        System.out.println("*****************************");
                        System.out.println("Press \"0\" to return");
                        String semesterInput = validator.setSem();
                        if (semesterInput.equals("0")) {
                            return;
                        }
                        studentEnrollmentDatabase.getCourseOffered(semesterInput);
                        sc.nextLine();
                        choice2 = 0;
                    }
                    choice = 0;
                } while (choice2 != 5);
                System.out.println("Press \"Enter\" to return");
                sc.nextLine();
            }

        } while (choice != 3);
        System.out.println("Press \"Enter\" to return");
        sc.nextLine();

    }

    //////////////////////////////////////////////////////////////////////////////////
    public static void deleteEnrollment() {
        Validator.clearscr();
        System.out.println("*****************************");
        System.out.println("DELETE AN ENROLLMENT");
        System.out.println("Press \"0\" to return");

        String studentId = validator.setSid();
        if (studentId.equals("0")) {
            return;
        }
        String courseInput = validator.setCid();
        if (courseInput.equals("0")) {
            return;
        }
        String semesterInput = validator.setSem();
        if (semesterInput.equals("0")) {
            return;
        }
        try {
            studentEnrollmentDatabase.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StudentEnrollment deleteStudent = studentEnrollmentDatabase.getOne(studentId, courseInput, semesterInput);
        if (deleteStudent != null) {
            System.out.println("*****************************");
            System.out.println("Enrolment Information");
            System.out.println(deleteStudent);
            System.out.println("Do you want to delete this enrollment? Y/N");
            String choice = sc.nextLine();

            while ((!choice.equalsIgnoreCase("y")) && (!choice.equalsIgnoreCase(("n")))) {
                System.out.print("Please enter in the correct format: ");
                choice = sc.nextLine();
            }
            if (choice.equalsIgnoreCase("y")) {
                studentEnrollmentDatabase.delete(deleteStudent);
                try {
                    studentEnrollmentDatabase.save();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("The enrollment has been removed!");
                System.err.println("Press \"Enter\" to return");
                sc.nextLine();
                return;
            }
            if (choice.equalsIgnoreCase("n")) {
                return;
            }

        } else {
            System.err.println("There is no enrollment matches your input!");
            System.err.println("Press \"Enter\" to return");
        }
        sc.nextLine();
    }

    //////////////////////////////////////////////////////////////////////////////////


    @Override
    public String toString() {
        return
                "StudentID: " + studentID + " " +
                        "| Student name: " + studentName + " " +
                        "| Birthdate: " + birthdate + " " +
                        "| CourseID: " + courseID + " " +
                        "| Course name: " + courseName + " " +
                        "| Credit point: " + numberOfCredits + " " +
                        "| semester: " + semester

                ;
    }


}




