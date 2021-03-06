package sadi;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class Validator {
    private Student student;
    private Course course;
    Scanner sc = new Scanner(System.in);
    //////////////////////////////////////////////////////////////////////////////////
    public boolean validate(String type, String input) {
        student = new Student();
        course = new Course();
        List<Course> courses = course.getCourses();
        List<Student> students = student.getStudents();
        List<String> studentId = new ArrayList<>();
        List<String> courseId = new ArrayList<>();
        students.forEach( e -> {
            String sid = e.getId();
            studentId.add(sid);
        } );
        courses.forEach( e -> {
           String cid = e.getId();
           courseId.add(cid);
        });

        Pattern pattern1 = Pattern.compile("^s([0-9]{7})",Pattern.CASE_INSENSITIVE);
        Pattern pattern2 = Pattern.compile("^([a-z]{4})([0-9]{4})",Pattern.CASE_INSENSITIVE);
        Pattern pattern3 = Pattern.compile("^([1-9])([0-9]{3})([ABC])");
        int inputLength = input.length();
        if (type.equals("sid")) {
            Matcher matcher1 = pattern1.matcher(input);
            return ((inputLength == 8 ) && (matcher1.matches()) && (studentId.contains(input)));
            }
        if (type.equals("cid")) {
            Matcher matcher2 = pattern2.matcher(input);
            return ((courseId.contains(input)) && (inputLength == 8) && (matcher2.matches()));
        }
        if (type.equals("sem")) {
            Matcher matcher3 = pattern3.matcher(input);
            return ((inputLength == 5) && (matcher3.matches()));
        }
        return false;
    }

    //////////////////////////////////////////////////////////////////////////////////
    public String setSid() {
        System.out.print("Please enter the student ID: ");
        String sid = sc.nextLine();
        sid = sid.trim().toLowerCase(); //remove extra space and make all chars lowercase
        if (sid.equals("0")) {
            return sid;
        }

        while(!validate("sid", sid) ) {
            System.out.print("Please enter the correct student ID: ");
            sid = sc.nextLine();
            sid = sid.trim().toLowerCase();
            if (sid.equals("0")) {
                return sid;
            }
        }
        return sid;
    }

    //////////////////////////////////////////////////////////////////////////////////

    public String setCid() {
        System.out.print("Please enter the course ID: ");
        String cid = sc.nextLine();
        cid = cid.trim().toUpperCase();
        if (cid.equals("0")) {
            return cid;
        }
        while(!validate("cid", cid) ) {
            System.out.print("Please enter the correct course ID: ");
            cid = sc.nextLine();
            cid = cid.trim().toUpperCase();
            if (cid.equals("0")) {
                return cid;
            }
        }
        return cid;
    }

    //////////////////////////////////////////////////////////////////////////////////
    public String setSem() {
        System.out.print("Please enter the semester: ");
        String sem = sc.nextLine();
        sem = sem.trim().toUpperCase();
        if (sem.equals("0")) {
            return sem;
        }
        while(!validate("sem", sem) ) {
            System.out.print("Please enter the correct semester format: ");
            sem = sc.nextLine();
            sem = sem.trim().toUpperCase();
            if (sem.equals("0")) {
                return sem;
            }
        }
        return sem;
    }

    //////////////////////////////////////////////////////////////////////////////////
    public int choiceValidator(int limit) {
        int firstChoice =0;
        try {
            System.out.print("choose a command to execute: ");
            firstChoice = sc.nextInt();
            while (firstChoice < 0 || firstChoice > limit) {
                System.out.println("UNKNOWN INPUT!");
                System.out.print("Please enter the correct option number: ");
                firstChoice = sc.nextInt();
            }
        } catch (InputMismatchException e) {
            System.err.println("Wrong input! Input only integer number please...");
            System.err.println("Press \"Enter\" to restart!");
            sc.nextLine();
            sc.nextLine();
        }
        return firstChoice;
    }

    //////////////////////////////////////////////////////////////////////////////////
    public String setSid2() {
        sc.nextLine();
        System.out.print("Please enter the student ID: ");
        String sid = sc.nextLine();
        sid = sid.trim().toLowerCase();
        if (sid.equals("0")) {
            return sid;
        }
        while(!validate("sid", sid) ) {
            System.out.print("Please enter the correct student ID: ");
            sid = sc.nextLine();
            sid = sid.trim().toLowerCase();
            if (sid.equals("0")) {
                return sid;
            }
        }
        return sid;
    }

    //////////////////////////////////////////////////////////////////////////////////
    public String fileValidate() {
        String fileName = null;
        String choice2;
        System.out.println("*****************************");
        System.out.println("Do you want to load a new CSV file? Y/N?");
        choice2 = sc.nextLine();
        choice2 = choice2.trim().toLowerCase();
        while ((!choice2.equalsIgnoreCase("y")) && (!choice2.equalsIgnoreCase(("n")))) {
            System.out.print("Please enter in the correct format: ");
            choice2 = sc.nextLine();
        }
        if (choice2.equalsIgnoreCase("y")) {
            System.out.print("Please enter the file path: ");
            fileName = sc.nextLine();
        }
        if (choice2.equalsIgnoreCase("n")) {
            fileName = "default.csv" ;
        }
        return fileName;
    }
    //////////////////////////////////////////////////////////////////////////////////
    public static void clearscr(){
            // clear the screen
            for(int i = 0; i < 80*300; i++) // Default Height of cmd is 300 and Default width is 80
                System.out.println(""); // Prints a line
        }
    }

