package com.dotin.FUM07SpringBootSite.api;

import lombok.*;

// import javax.persistence.*;
// import javax.validation.constraints.NotNull;

import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
// @Entity
public class Student{
    // @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    // @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String email;
    private Integer age;

    public void setAge(/*@NotNull*/ Integer age) {
        if (age < 18 || age > 150) {
            throw new IllegalArgumentException("Invalid age range");
        }
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age);
    }


}
