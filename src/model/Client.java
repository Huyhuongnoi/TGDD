package model;

public class Client {
    private String customerId;
    private String name;
    private int age;
    private String sex;

    public Client(String customerId, String name, int age, String sex) {
        this.customerId = customerId;
        this.name = name;
        this.age = age;
        this.sex = sex;

    }

    public String getCustomerId() {
        return customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "Client{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
