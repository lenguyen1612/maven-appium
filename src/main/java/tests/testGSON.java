package tests;

import com.google.gson.Gson;
import model.ClassInfo;
import model.Student;
import model.StudentInfo;

import java.util.ArrayList;
import java.util.List;

public class testGSON {

    public static void main(String[] args) {
        //set GSON
        Gson gson = new Gson();
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("STU001", "Nguyen Van A", "093248734984"));
        students.add(new Student("STU002", "Nguyen Van B", "03314534543"));

        ClassInfo classInfo = new ClassInfo("CLS001", "Dia Ly", "Dia ly");

        List<StudentInfo> studentInfos = new ArrayList<StudentInfo>();
        studentInfos.add(new StudentInfo(students, classInfo));

        String json = gson.toJson(studentInfos);

        System.out.println(json.toString());
    }
}
