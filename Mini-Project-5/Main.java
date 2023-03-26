import University.College;
import University.Lesson;
import University.LessonType;
import University.University;
import User.Student;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        University university = new University("InFluX");

        Lesson lesson = new Lesson("DS", LessonType.SPECIALIZE, 3, 40);
        Lesson lesson1 = new Lesson("Mathematics", LessonType.CORE, 3, 50);
        Lesson lesson2 = new Lesson("Matlab", LessonType.WORKSHOP, 1, 20);
        Lesson lesson3 = new Lesson("Literature", LessonType.GENERALIZE, 2, 60);

        Student student = new Student("Reza", "1400", College.CS);
        Student student1 = new Student("Sheida", "1401", College.ECONOMIC);
        Student student2 = new Student("Melika", "1399", College.CHEMISTRY);

        university.addLesson(lesson);
        university.addLesson(lesson1);
        university.addLesson(lesson2);
        university.addLesson(lesson3);

        university.addStudent(student);
        university.addStudent(student1);
        university.addStudent(student2);

        printLessons(university);
        ArrayList<Lesson> ls = new ArrayList<>();
        ls.add(lesson);
        ls.add(lesson1);
        student.selectUnit(ls);
        ArrayList<Lesson> ls2 = new ArrayList<>();
        ls2.add(lesson);
        student.deleteUnit(ls2);

        printStudentInf(student);
        printStudentInf(student1);
        printStudentInf(student2);

        printStudentLessons(student);
    }

    public static void printLessons(University university)
    {
        for(Lesson lesson : university.getLessons())
            System.out.println(lesson.information());

    }

    public static void printStudentLessons(Student student)
    {
        for (Lesson lesson : student.getLessons())
            System.out.println(lesson.information());
    }

    public static void printStudentInf(Student student)
    {
        System.out.println(student.information());
    }
}