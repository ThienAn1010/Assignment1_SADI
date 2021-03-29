package sadi;

public class Course {
    private String id;
    private String name;
    private int numberOfCredits;

    public Course(String id, String name, int numberOfCredits) {
        this.id = id;
        this.name = name;
        this.numberOfCredits = numberOfCredits;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", numberOfCredits=" + numberOfCredits +
                '}';
    }
}
