package com.dotin.FUM07SpringBootSite.api;


import com.dotin.FUM07SpringBootSite.data.StudentRepository;
import com.dotin.FUM07SpringBootSite.exception.StudentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    public StudentController(@Qualifier("studentRepositoryMemory") StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @GetMapping()
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("logger")
    public void logger() {
        System.out.println("This is an Error");

        Student st = Student.builder().id(1L).age(50).build();

        //
        // logger.error("This is an Error");
        // logger.warn("This is an warn");
        // logger.info("This is an info");
        // logger.debug("This is an debug");
        // logger.trace("This is an trace");
    }

    @PostMapping("{id}")
    public ResponseEntity<String> addStudent(@PathVariable Long id, @RequestBody Student student) {
        if (studentRepository.isExisted(id)) {
            logger.warn("Student with this ID " + id + " already exists");
            return new ResponseEntity<>("Student with this ID already exists", HttpStatus.CONFLICT);
        }
        studentRepository.save(id, student);

        logger.debug("Student with this ID " + id + " added");
        return new ResponseEntity<>("Student added successfully", HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student st = studentRepository.findById(id);
        if (st != null) {
            return new ResponseEntity<>(st, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        if (studentRepository.isExisted(id)) {
            studentRepository.update(id, updatedStudent);
            return new ResponseEntity<>("Student updated successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }

        // try {
        //     studentRepository.update(id, updatedStudent);
        //     return new ResponseEntity<>("Student updated successfully", HttpStatus.CREATED);
        // } catch (StudentNotFoundException e) {
        //     return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        // }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        try {
            studentRepository.delete(id);
            return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
        } catch (StudentNotFoundException e) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
    }

}
