package University;

import User.Student;

import java.util.ArrayList;

public class University {
    private ArrayList<Lesson> lessons;
    private ArrayList<Student> students;
    final private String name;

    public University(String name) {
        this.name = name;
        this.lessons = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addLesson(Lesson lesson)
    {
        this.lessons.add(lesson);
    }
    public void addStudent(Student student)
    {
        this.students.add(student);
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }
}
