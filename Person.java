// Ian Orego - 225259
// OOP Activity 2: Hospital Management System
public abstract class Person {
    private String name;
    private int age;
    private String gender;

    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }

    public abstract void displayInfo();
}
