package model;

public class ClassInfo {
    private String name;
    private  String id;
    private String desc;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ClassInfo(String id, String name, String desc){
        this.id = id;
        this.name = name;
        this.desc = desc;
    }
    
}
