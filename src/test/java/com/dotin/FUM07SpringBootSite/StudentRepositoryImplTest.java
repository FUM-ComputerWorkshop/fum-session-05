package com.dotin.FUM07SpringBootSite;

import com.dotin.FUM07SpringBootSite.api.Student;
import com.dotin.FUM07SpringBootSite.data.StudentRepository;
import com.dotin.FUM07SpringBootSite.data.StudentRepositoryImpl;
import com.dotin.FUM07SpringBootSite.exception.StudentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentRepositoryImplTest {

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new StudentRepositoryImpl();
    }

    @Test
    void findAll_ShouldReturnListOfStudents() {
        // Arrange
        List<Student> expectedStudents = new ArrayList<>();
        Student student1 = new Student();
        Student student2 = new Student();
        expectedStudents.add(student1);
        expectedStudents.add(student2);

        // Act
        List<Student> result = studentRepository.findAll();

        // Assert
        assertEquals(expectedStudents, result);
    }

    @Test
    void save_ShouldAddStudentToList() {
        // Arrange
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");

        // Act
        studentRepository.save(1L, student);

        // Assert
        List<Student> students = studentRepository.findAll();
        assertTrue(students.contains(student));
    }

    @Test
    void isExisted_ShouldReturnTrueForExistingStudent() {
        // Arrange
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");
        studentRepository.save(1L, student);

        // Act
        boolean result = studentRepository.isExisted(1L);

        // Assert
        assertTrue(result);
    }

    @Test
    void isExisted_ShouldReturnFalseForNonExistingStudent() {
        // Act
        boolean result = studentRepository.isExisted(1L);

        // Assert
        assertFalse(result);
    }

    @Test
    void findById_ShouldReturnStudentForExistingId() {
        // Arrange
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");
        studentRepository.save(1L, student);

        // Act
        Student result = studentRepository.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(student, result);
    }

    @Test
    void findById_ShouldReturnNullForNonExistingId() {
        // Act
        Student result = studentRepository.findById(1L);

        // Assert
        assertNull(result);
    }

    @Test
    void update_ShouldUpdateExistingStudent() {
        // Arrange
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");
        studentRepository.save(1L, student);

        Student updatedStudent = new Student();
        updatedStudent.setId(1L);
        updatedStudent.setName("Jane Doe");

        // Act
        studentRepository.update(1L, updatedStudent);

        // Assert
        Student updated = studentRepository.findById(1L);
        assertNotNull(updated);
        assertEquals(updatedStudent.getName(), updated.getName());
    }

    @Test
    void update_ShouldThrowExceptionForNonExistingStudent() {
        // Arrange
        Student updatedStudent = new Student();
        updatedStudent.setId(1L);
        updatedStudent.setName("Jane Doe");

        // Act & Assert
        assertThrows(StudentNotFoundException.class, () -> studentRepository.update(1L, updatedStudent));
    }

    @Test
    void delete_ShouldRemoveExistingStudent() {
        // Arrange
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");
        studentRepository.save(1L, student);

        // Act
        studentRepository.delete(1L);

        // Assert
        Student deleted = studentRepository.findById(1L);
        assertNull(deleted);
    }

    @Test
    void delete_ShouldNotThrowExceptionForNonExistingStudent() {
        // Act & Assert (no exception expected)
        studentRepository.delete(1L);
    }

}
