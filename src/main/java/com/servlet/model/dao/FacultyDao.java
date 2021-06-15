package com.servlet.model.dao;


import com.servlet.model.entity.Faculty;
import com.servlet.model.entity.Student;

import java.util.List;

public interface FacultyDao extends GenericDao<Faculty> {
    List<Student> getAllStudents(int id);
}
