package User;

import University.College;
import University.Lesson;

import java.util.ArrayList;

public class Student {
    static private long num;
    final private long id;
    private String name;
    private String openYear;
    private College college;
    private int numDelete;
    private ArrayList<Lesson> lessons;

    public Student(String name, String openYear, College college) {
        this.college = college;
        this.openYear = openYear;
        this.id = createID();
        this.name = name;
        this.lessons = new ArrayList<>();
        this.numDelete = 0;

        num++;
    }

    private long createID()
    {
        StringBuilder id = new StringBuilder();
        if(Integer.parseInt(openYear) >= 1400) id.append(openYear, 1, 4);
        else id.append(openYear, 2, 4);
        id.append(college.getCode());
        id.append(String.format("%3s", num).replace(' ', '0'));
        return Long.parseLong(id.toString());
    }

    public int selectUnit(ArrayList<Lesson> lessons)
    {
        if(lessons.size()+this.lessons.size() > 15) return -1;

        int statue = 1;
        for(Lesson lesson : lessons) {
            if(lesson.getStudentNum() > lesson.getCapacity()) statue = -2;
            else {
                this.lessons.add(lesson);
                lesson.setStudentNum(lesson.getStudentNum() + 1);
            }
        }

        return statue;
    }

    public int deleteUnit(ArrayList<Lesson> lessons)
    {
        if(numDelete > 3) return -1;

        for(Lesson lesson : lessons) {
            this.lessons.remove(lesson);
            lesson.setStudentNum(lesson.getStudentNum() - 1);
        }

        numDelete++;

        return 1;
    }

    public String information() {
        return "User.Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", openYear='" + openYear + '\'' +
                ", college=" + college +
                '}';
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenYear() {
        return openYear;
    }

    public void setOpenYear(String openYear) {
        this.openYear = openYear;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }
}
