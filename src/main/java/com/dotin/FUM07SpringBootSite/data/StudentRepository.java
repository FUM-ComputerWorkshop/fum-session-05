package com.dotin.FUM07SpringBootSite.data;


import com.dotin.FUM07SpringBootSite.api.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> findAll();

    void save(Long id, Student student);

    boolean isExisted(Long id);

    Student findById(Long id);

    void update(Long id, Student student);

    void delete(Long id);
}
