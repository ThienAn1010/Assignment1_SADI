package sadi;

import java.io.IOException;
import java.util.List;


public interface StudentEnrolmentManager {
    public void add(String studentID, String studentName, String birthDate, String courseID, String courseName, int creditPoint, String semester) throws IOException;
    public void update(String type, StudentEnrollment updateStudent, String newID, String newName, int newInfo, String semInput);
    public void delete(StudentEnrollment updateStudent);
    public List<StudentEnrollment> getAll();
    public void load() throws IOException;
    public void save() throws IOException;
    public StudentEnrollment getOne(String studentId, String courseId, String semester);
}
