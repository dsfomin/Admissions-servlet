package com.servlet.model.dao.mapper;

import com.servlet.model.entity.Faculty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class FacultyMapper implements ObjectMapper<Faculty> {

    @Override
    public Faculty extractFromResultSet(ResultSet rs) throws SQLException {
        Faculty faculty = new Faculty();
        faculty.setFacultyId(rs.getInt("facultyid"));
        faculty.setTitle(rs.getString("title"));
        faculty.setTotalPlaces(rs.getInt("totalPlaces"));
        faculty.setBudgetPlaces(rs.getInt("budgetPlaces"));
        faculty.setContractPlaces(rs.getInt("contractPlaces"));
        faculty.setFirstSubject(rs.getString("firstSubject"));
        faculty.setSecondSubject(rs.getString("secondSubject"));
        faculty.setThirdSubject(rs.getString("thirdSubject"));
        return faculty;
    }

    public void makeUnique(Map<Integer, Faculty> cache,
                           Faculty faculty) {
        cache.putIfAbsent(faculty.getFacultyId(), faculty);
        cache.get(faculty.getFacultyId());
    }
}
