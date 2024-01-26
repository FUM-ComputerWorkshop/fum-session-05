package com.dotin.FUM07SpringBootSite.data;

import com.dotin.FUM07SpringBootSite.api.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("studentRepositoryDB")
public class StudentRepositoryDBImpl implements StudentRepository{

    private List<Student> students = new ArrayList<>();
    private Set<Long> studentIds = new HashSet<>();


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
        return null;
    }

    @Override
    public void update(Long id, Student student) {

    }

    @Override
    public void delete(Long id) {

    }
}
