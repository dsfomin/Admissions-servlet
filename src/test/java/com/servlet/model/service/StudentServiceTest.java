package com.servlet.model.service;

import com.servlet.model.entity.Student;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class StudentServiceTest {
    StudentService studentService = mock(StudentService.class);

    @Test
    public void getStudents() {
        Student student = new Student();
        List<Student> list = new ArrayList<>();
        list.add(student);
        list.add(new Student());
        when(studentService.getStudents()).thenReturn(list);
    }
    @Test
    public void findByLogin() throws SQLException {
        Optional<Student> student = Optional.of(new Student());

        when(studentService.findByLogin(student.get().getLogin())).thenReturn(student);
    }

        @Test
    public void findStudentById() throws SQLException {
        Optional<Student> student = Optional.of(new Student());
        student.get().setId(1);

        when(studentService.findById(student.get().getId())).thenReturn(student);
    }

    @Test
    public void saveStudent() throws SQLException {
        Student student = new Student();
        studentService.createStudent(student);
        verify(studentService, times(1)).createStudent(student);
    }

    @Test
    public void deleteStudentById() {
        Student student = new Student();
        studentService.deleteStudent(student.getId());
        verify(studentService, times(1)).deleteStudent(student.getId());
    }

}
