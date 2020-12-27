package jpa.entity.coursework4;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
// 1. Getting all members aged [from; to)
@NamedQueries({
        @NamedQuery(name = "Climber.getByAge",
                query = "SELECT a FROM Climber a WHERE a.age >= :from and a.age < :to"),
})
public class Climber extends AutoPrimaryKey {

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false, length = 3)
    private int age;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groupOfClimbers")
    List<GroupForClimbing> group = new ArrayList<>();

    public Climber(String name, String address, int age) {
        setName(name);
        setAddress(address);
        setAge(age);
    }

    public Climber() {
    }

    public void setName(String name) {
        if (name == null || name.length() < 3)
            throw new IllegalArgumentException("name должен быть не меньше 3");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.length() < 5)
            throw new IllegalArgumentException("address должен быть не меньше 5");
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18)
            throw new IllegalArgumentException("age должен быть больше 18");
        this.age = age;
    }

    public List<GroupForClimbing> getGroup() {
        return group;
    }

    public void setGroup(List<GroupForClimbing> group) {
        this.group = group;
    }
}