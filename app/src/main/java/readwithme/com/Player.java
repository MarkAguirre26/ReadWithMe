package readwithme.com;

/**
 * Created by Mark on 10/18/2016.
 */

public class Player {
    private int cn;
    private String name, age, grade;
    private String remark;
    private int teacherIndex;

    public Player() {

    }

    public Player(int id, String name_, String age_, String grade_, int teacher_, String remark_) {
        cn = id;
        name = name_;
        this.age = age_;
        this.grade = grade_;
        teacherIndex = teacher_;
        remark = remark_;
    }


    public void setCn(int id) {
        cn = id;
    }

    public void setName(String name_) {
        name = name_;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setRemark(String remark_) {
        remark = remark_;
    }

    public void setTeacherIndex(int teacherIndex) {
        this.teacherIndex = teacherIndex;
    }

    public int getCn() {
        return cn;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }

    public int getTeacherIndex() {
        return teacherIndex;
    }

    public String getAge() {
        return age;
    }

    public String getGrade() {
        return grade;
    }
}
