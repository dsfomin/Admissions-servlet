package com.servlet.model.service;

import com.servlet.model.entity.Faculty;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class FacultyServiceTest {

    FacultyService facultyService = mock(FacultyService.class);

    @Test
    public void getFaculties() {
        Faculty faculty = new Faculty();
        List<Faculty> list = new ArrayList<>();
        list.add(faculty);
        list.add(new Faculty());
        when(facultyService.getFaculties()).thenReturn(list);
    }

    @Test
    public void findFacultyById() throws SQLException {
        Optional<Faculty> faculty = Optional.of(new Faculty());
        faculty.get().setFacultyId(1);

        when(facultyService.findById(faculty.get().getFacultyId())).thenReturn(faculty);
    }

    @Test
    public void saveFaculty() throws SQLException {
        Faculty faculty = new Faculty();
        facultyService.createFaculty(faculty);
        verify(facultyService, times(1)).createFaculty(faculty);
    }

    @Test
    public void deleteFacultyById() {
        Faculty faculty = new Faculty();
        facultyService.deleteFaculty(faculty.getFacultyId());
        verify(facultyService, times(1)).deleteFaculty(faculty.getFacultyId());
    }

}
