package model;

public class Student {
    private String name;
    private  String id;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Student(String name, String id, String phone){
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
}