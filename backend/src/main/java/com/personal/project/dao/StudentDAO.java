package com.personal.project.dao;

import com.personal.project.dto.*;
import org.springframework.stereotype.*;

import java.util.*;
@Repository
public interface StudentDAO {
    public void addStudent(Student student) throws Exception;

    public Student findById(Long studentId) throws Exception;

    public Student findByOid(Long studentOid) throws Exception;

    public List<Student> findAll() throws Exception;

    public List<Student> findByName(String name) throws Exception;

    public void deleteById(Long studentId) throws Exception;

    public Student update(Student student) throws Exception;

}
