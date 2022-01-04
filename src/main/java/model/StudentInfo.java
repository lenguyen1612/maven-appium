package model;

import java.util.List;

public class StudentInfo {
    List<Student> studentList;
   ClassInfo classInfos;

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public ClassInfo getClassInfos() {
        return classInfos;
    }

    public void setClassInfos(ClassInfo classInfos) {
        this.classInfos = classInfos;
    }

    public StudentInfo(List<Student> studentList, ClassInfo classInfos){
        this.classInfos = classInfos;
        this.studentList = studentList;
    }
}
