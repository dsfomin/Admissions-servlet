package com.servlet.command;

import com.servlet.model.entity.Faculty;
import com.servlet.model.entity.Student;
import com.servlet.model.service.FacultyService;
import com.servlet.model.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FinalizeResultCommand implements Command {
    private final StudentService studentService;
    private final FacultyService facultyService;

    public FinalizeResultCommand(StudentService studentService, FacultyService facultyService) {
        this.facultyService = facultyService;
        this.studentService = studentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Faculty> facultyList = facultyService.getFaculties();
        for (Faculty faculty : facultyList) {
            int budgetPlaces = faculty.getBudgetPlaces();

            List<Student> studentList = facultyService.getAllStudents(faculty.getFacultyId())
                    .stream().filter(Student::isInSearch)
                    .sorted(Comparator.comparing(Student::getAverageGrade).reversed())
                    .collect(Collectors.toList());
            studentList.forEach(System.out::println);
            for (Student student : studentList) {
                if (budgetPlaces-- > 0) {
                    student.setOnBudget(true);
                    student.setInSearch(false);
                    studentService.setOneFaculty(student.getId(),faculty.getFacultyId());
                }
                try {
                    studentService.updateStudent(student);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "/WEB-INF/admin/adminbasis.jsp";
    }
}
