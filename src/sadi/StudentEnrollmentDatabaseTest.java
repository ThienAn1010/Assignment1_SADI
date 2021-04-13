package sadi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static sadi.StudentEnrollment.studentEnrollmentDatabase;

public class StudentEnrollmentDatabaseTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("Before");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("After");
    }

    @Test
    public void add() {
        System.out.println("Test add function");
        StudentEnrollment newSE = new StudentEnrollment("s3825455", "Nguyen Hoang Thien An", "10/10/2001", "COSC5555", "Programming 1", 12, "2021C");
        studentEnrollmentDatabase.add("s3825455", "Nguyen Hoang Thien An", "10/10/2001", "COSC5555", "Programming 1", 12, "2021C");
        int size = studentEnrollmentDatabase.studentEnrollments.size();
        assertEquals(newSE.toString(), studentEnrollmentDatabase.studentEnrollments.get(size - 1).toString());
    }


    @Test
    public void update() {
        studentEnrollmentDatabase.clearCsv();
        System.out.println("Test update() function");
        StudentEnrollment newSE = new StudentEnrollment("s3825455", "Nguyen Hoang Thien An", "10/10/2001", "COSC5555", "Programming 1", 12, "2021C");
        StudentEnrollment newSE2 = new StudentEnrollment("s3825455", "Nguyen Hoang Thien An", "10/10/2001", "COSC4444", "AI", 12, "2021C");
        studentEnrollmentDatabase.update("cid", newSE, "COSC4444","AI",12,"2021C");
        assertEquals(newSE2.toString(),studentEnrollmentDatabase.getOne("s3825455","COSC4444","2021C").toString());
    }

    @Test
    public void delete() {
        studentEnrollmentDatabase.clearCsv();
        System.out.println("Test delete() function");
        studentEnrollmentDatabase.add("s3825455", "Nguyen Hoang Thien An", "10/10/2001", "COSC5555", "Programming 1", 12, "2021C");
        studentEnrollmentDatabase.add("s3825455", "Nguyen Hoang Thien An", "10/10/2001", "COSC4444", "AI", 12, "2021C");
        StudentEnrollment testStudent = studentEnrollmentDatabase.getOne("s3825455","COSC5555","2021C");
        StudentEnrollment testStudent2 = studentEnrollmentDatabase.getOne("s3825455","COSC4444","2021C");
        studentEnrollmentDatabase.delete(testStudent);
        List<StudentEnrollment> compareList = new ArrayList<>();
        compareList.add(testStudent2);
        assertEquals(compareList.toString(), studentEnrollmentDatabase.getAll().toString() );

    }

    @Test
    public void getOne() {
        System.out.println("Test getOne() function");
        StudentEnrollment newSE = new StudentEnrollment("s3825455", "Nguyen Hoang Thien An", "10/10/2001", "COSC5555", "Programming 1", 12, "2021C");
        studentEnrollmentDatabase.add("s3825455", "Nguyen Hoang Thien An", "10/10/2001", "COSC5555", "Programming 1", 12, "2021C");
        assertEquals(newSE.toString(), studentEnrollmentDatabase.getOne("s3825455","COSC5555","2021C").toString());
    }


    @Test
    public void getAll() {
        studentEnrollmentDatabase.clearCsv();
        System.out.println("Test getAll() function");
        StudentEnrollment newSE = new StudentEnrollment("s3825455", "Nguyen Hoang Thien An", "10/10/2001", "COSC5555", "Programming 1", 12, "2021C");
        StudentEnrollment newSE2 = new StudentEnrollment("s3825455", "Nguyen Hoang Thien An", "10/10/2001", "COSC4444", "AI", 12, "2021C");
        List<StudentEnrollment> compareList = new ArrayList<>();
        compareList.add(newSE);
        compareList.add(newSE2);
        studentEnrollmentDatabase.add("s3825455", "Nguyen Hoang Thien An", "10/10/2001", "COSC5555", "Programming 1", 12, "2021C");
        studentEnrollmentDatabase.add("s3825455", "Nguyen Hoang Thien An", "10/10/2001", "COSC4444", "AI", 12, "2021C");
        assertEquals(studentEnrollmentDatabase.getAll().toString(),compareList.toString() );
    }
}

