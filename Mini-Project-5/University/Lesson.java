package University;

public class Lesson {
    private String name;
    private LessonType type;
    final private int unit;
    private int capacity;
    private int studentNum;

    public Lesson(String name, LessonType type, int unit, int capacity) {
        this.name = name;
        this.type = type;
        this.unit = unit;
        this.capacity = capacity;
        this.studentNum = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LessonType getType() {
        return type;
    }

    public void setType(LessonType type) {
        this.type = type;
    }

    public int getUnit() {
        return unit;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public String information() {
        return "University.Lesson{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", unit=" + unit +
                ", capacity=" + capacity +
                ", studentNum=" + studentNum +
                '}';
    }
}
