class Student {
    private int id;
    private String name;
    private String college;

    Student(int id, String name, String college){
        this.id = id;
        this.name = name;
        this.college = college;
    }

    Student(String name, String college){
        this.name = name;
        this.college = college;
    }

    int getId(){return id; }

    String getName(){return name;}

    String getCollege(){return  college;}
}
