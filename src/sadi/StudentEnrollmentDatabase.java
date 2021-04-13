package sadi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentEnrollmentDatabase implements StudentEnrolmentManager {
    List<StudentEnrollment> studentEnrollments;
    private final String file;
    static Scanner sc = new Scanner(System.in);

    public StudentEnrollmentDatabase(String file) {
        this.studentEnrollments = new ArrayList<>();
        this.file = file;
    }

    public List<StudentEnrollment> getStudentEnrollments() {
        return studentEnrollments;
    }

    //////////////////////////////////////////////////////////////////////////////////
    @Override
    public void add(String studentId, String studentName, String birthDate, String courseId, String courseName, int creditPoint, String semester) {
        StudentEnrollment newSE = new StudentEnrollment(studentId, studentName, birthDate, courseId, courseName, creditPoint, semester);
        studentEnrollments.add(newSE);
    }

    //////////////////////////////////////////////////////////////////////////////////
    public void save() throws IOException {
        // opens the file for writing
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            // iterates over the leads
            for (StudentEnrollment se : getStudentEnrollments()) {
                // // creates an array of the lead's values
                String[] values = {se.getStudentId(), se.getStudentName(), se.getBirthdate(), se.getCourseId(), se.getCourseName(), String.valueOf(se.getNumberOfCredits()), se.getSemester()};
                // // creates a new line
                String line = String.join(",", values);
                // // writes the line
                bw.append(line);
                // // writes "enter", so we more to the next line
                bw.append("\n");
            }
            bw.flush();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////
    @Override
    public void update(String type, StudentEnrollment updateStudent, String newId, String newName, int newInfo, String semInput) {
        if (type.equalsIgnoreCase("cid")) {
            add(updateStudent.getStudentId(), updateStudent.getStudentName(), updateStudent.getBirthdate(), newId, newName, newInfo, semInput);

        }
    }

    //////////////////////////////////////////////////////////////////////////////////
    @Override
    public void delete(StudentEnrollment updateStudent) {
            studentEnrollments.remove(updateStudent);
    }

    //////////////////////////////////////////////////////////////////////////////////
    @Override
    public StudentEnrollment getOne(String studentId, String courseId, String semester) {
    for (StudentEnrollment studentEnrollment : studentEnrollments) {
        if ((studentEnrollment.getCourseId().equalsIgnoreCase(courseId)) && (studentEnrollment.getSemester().equalsIgnoreCase(semester)) && (studentEnrollment.getStudentId().replaceAll("\0", "").equalsIgnoreCase(studentId)) ) {
            return studentEnrollment;
        }
    }
    return null;
    }

    //////////////////////////////////////////////////////////////////////////////////
    public StudentEnrollment detailEnrollment(String type, String studentId, String selectedId) {
        if (type.equals("student")) {
            for (StudentEnrollment studentEnrollment : studentEnrollments) {
                if ((studentEnrollment.getStudentId().equals(studentId)) && (studentEnrollment.getCourseId().equals(selectedId))) {
                    return studentEnrollment;
                }
            }
        }
        if (type.equals("sem")) {
            for (StudentEnrollment studentEnrollment : studentEnrollments) {
                if ((studentEnrollment.getStudentId().equalsIgnoreCase(studentId)) && (studentEnrollment.getSemester().equalsIgnoreCase(selectedId))) {
                    return studentEnrollment;
                }
            }
        }
        return null;
    }

    //////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<StudentEnrollment> getAll() {
        List<StudentEnrollment> getAllList = new ArrayList<>();
        getAllList.addAll(studentEnrollments);
        return getAllList;
    }

    //////////////////////////////////////////////////////////////////////////////////
    @Override
    public void load() throws IOException {
        studentEnrollments.clear();
        // Opens the file for reading
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String enrollmentInfo;
            // Reads it line by line
            while ((enrollmentInfo = br.readLine()) != null) {
                // splits the string line using semicolons
                String[] values = enrollmentInfo.split(",");
                String studentId = values[0];
                String studentName = values[1];
                String birthdate = values[2];
                String courseId = values[3];
                String courseName = values[4];
                String creditPoint = values[5];
                String semester = values[6];
                // adds a new lead with those values
                add(studentId, studentName, birthdate, courseId, courseName, Integer.parseInt(String.valueOf(creditPoint)), semester);
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////////
    public void getStudentEnrollment(String id) {
        ArrayList<StudentEnrollment> enrollmentDetails = new ArrayList<>();
        studentEnrollments.forEach(e -> {
            if (e.getStudentId().equalsIgnoreCase(id)) {
                System.out.println(e.getStudentId() + " | " + e.getStudentName() + " | " + e.getCourseId() + " | " + e.getCourseName() + " | " + e.getNumberOfCredits() + " | " + e.getSemester());
                enrollmentDetails.add(e);
            }
        });
        System.out.println("Do you want to export to CSV file? Y/N");
        String choice = sc.nextLine();
        while ((!choice.equalsIgnoreCase("y")) && (!choice.equalsIgnoreCase(("n")))) {
            System.out.print("Please enter in the correct format: ");
            choice = sc.nextLine();
        }
        if (choice.equalsIgnoreCase("y")) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("Student enrollment.csv"));
                for (StudentEnrollment enrollmentDetail : enrollmentDetails) {
                    String[] values = {enrollmentDetail.getStudentId(), enrollmentDetail.getStudentName(), enrollmentDetail.getCourseId(), enrollmentDetail.getCourseName(), String.valueOf(enrollmentDetail.getNumberOfCredits()), enrollmentDetail.getSemester()};
                    String line = String.join(",", values);
                    // // writes the line
                    bw.append(line);
                    // // writes "enter", so we more to the next line
                    bw.append("\n");
                }
                bw.flush();
                System.out.println("*****************************");
                System.out.println("The CSV file has been exported");
                System.out.println("Press \"Enter\" to return");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        if (choice.equalsIgnoreCase("n")) {
            System.out.println("*****************************");
            System.out.println("Press \"Enter\" to return");
        }
    }

    //////////////////////////////////////////////////////////////////////////////////
    public void getCourseEnrollment(String id) {
        ArrayList<StudentEnrollment> enrollmentDetails = new ArrayList<>();
        studentEnrollments.forEach(e -> {
            if (e.getCourseId().equalsIgnoreCase(id)) {
                System.out.println(e);
                enrollmentDetails.add(e);
            }
        });
        System.out.println("Do you want to export to CSV file? Y/N");
        String choice = sc.nextLine();
        while ((!choice.equalsIgnoreCase("y")) && (!choice.equalsIgnoreCase(("n")))) {
            System.out.print("Please enter in the correct format: ");
            choice = sc.nextLine();
        }
        if (choice.equalsIgnoreCase("y")) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("Course enrollment.csv"));
                for (StudentEnrollment enrollmentDetail : enrollmentDetails) {
                    String[] values = {enrollmentDetail.getStudentId(), enrollmentDetail.getStudentName(), enrollmentDetail.getBirthdate(), enrollmentDetail.getCourseId(), enrollmentDetail.getCourseName(), String.valueOf(enrollmentDetail.getNumberOfCredits()), enrollmentDetail.getSemester()};
                    String line = String.join(",", values);
                    // // writes the line
                    bw.append(line);
                    // // writes "enter", so we more to the next line
                    bw.append("\n");
                }
                bw.flush();
                System.out.println("*****************************");
                System.out.println("The CSV file has been exported");
                System.out.println("Press \"Enter\" to return");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        if (choice.equalsIgnoreCase("n")) {
            System.out.println("*****************************");
            System.out.println("Press \"Enter\" to return");
        }

    }

    //////////////////////////////////////////////////////////////////////////////////
    public void getCourseOffered(String semester) {
        ArrayList<StudentEnrollment> courseDetails = new ArrayList<>();
        studentEnrollments.forEach(e -> {
            if (e.getSemester().equalsIgnoreCase(semester)) {
                System.out.println(e.getCourseId() + " | " + e.getCourseName() + " | " + e.getNumberOfCredits() + " | " + e.getSemester());
                courseDetails.add(e);
            }
        });
        System.out.println("Do you want to export to CSV file? Y/N");
        String choice = sc.nextLine();
        while ((!choice.equalsIgnoreCase("y")) && (!choice.equalsIgnoreCase(("n")))) {
            System.out.print("Please enter in the correct format: ");
            choice = sc.nextLine();
        }
        if (choice.equalsIgnoreCase("y")) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("Course offered.csv"));
                for (StudentEnrollment enrollmentDetail : courseDetails) {
                    String[] values = {enrollmentDetail.getCourseId(), enrollmentDetail.getCourseName(), String.valueOf(enrollmentDetail.getNumberOfCredits()), enrollmentDetail.getSemester()};
                    String line = String.join(",", values);
                    bw.append(line);
                    bw.append("\n");
                }
                bw.flush();
                System.out.println("*****************************");
                System.out.println("The CSV file has been exported");
                System.out.println("Press \"Enter\" to return");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        if (choice.equalsIgnoreCase("n")) {
            System.out.println("*****************************");
            System.out.println("Press \"Enter\" to return");
        }

    }

    //////////////////////////////////////////////////////////////////////////////////
    public void clearCsv() {
        studentEnrollments.clear();
    }
}








