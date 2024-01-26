package com.dotin.FUM07SpringBootSite.data;

import com.dotin.FUM07SpringBootSite.api.Student;
import com.dotin.FUM07SpringBootSite.exception.StudentNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("studentRepositoryMemory")
public class StudentRepositoryImpl implements StudentRepository {

    private final List<Student> students = new ArrayList<>();
    private final Set<Long> studentIds = new HashSet<>();


    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public void save(Long id, Student student) {
        student.setId(id);
        students.add(student);
        studentIds.add(id);
    }

    @Override
    public boolean isExisted(Long id) {
        return studentIds.contains(id);
    }

    @Override
    public Student findById(Long id) {

        if (isExisted(id)) {
            for (Student student : students) {
                if (student.getId().equals(id)) {
                    return student;
                }
            }
        }
        return null;
    }

    @Override
    public void update(Long id, Student updatedStudent) {
    if (isExisted(id) && updatedStudent.getAge() != null) {
            for (Student student : students) {
                if (student.getId().equals(id)) {
                    student.setName(updatedStudent.getName());
                    student.setAge(updatedStudent.getAge());
                    student.setEmail(updatedStudent.getEmail());
                    return;
                }
            }
        }
        throw new StudentNotFoundException("Student not found with ID: " + id);
    }

    @Override
    public void delete(Long id) {
        if (isExisted(id)) {
            students.removeIf(student -> student.getId().equals(id));
            studentIds.remove(id);
        }
    }

}
