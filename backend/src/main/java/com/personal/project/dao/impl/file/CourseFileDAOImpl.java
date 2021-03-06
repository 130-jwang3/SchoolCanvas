package com.personal.project.dao.impl.file;

import com.fasterxml.jackson.databind.*;
import com.personal.project.dto.*;
import com.personal.project.util.*;
import com.personal.project.dao.*;

import java.util.*;
import java.util.stream.*;

public class CourseFileDAOImpl implements CourseDAO{
    public static final String COURSE_FILENAME = "course.txt";
    public static ObjectMapper mapper = new ObjectMapper();


    @Override
    public void addCourse(Course course) throws Exception {
        if(findById(course.getCourseID())==null&&findByName(course.getName())==null){
            FileUtil.appendFile(mapper.writeValueAsString(course), COURSE_FILENAME);
        }
    }

    @Override
    public Long findOid(String courseId) throws Exception {
        return null;
    }

    @Override
    public Course findById(String courseId) throws Exception {
        List<Course> courseList = findAll();
        if (courseList != null && courseList.size() > 0) {
            return courseList.stream().filter(course -> course.getCourseID() .equals(courseId)).findFirst().orElse(null);
        } else {
            return null;
        }
    }

    @Override
    public Course findByOid(Long courseOid) throws Exception {
        return null;
    }

    @Override
    public List<Course> findAll() throws Exception {
        String content = FileUtil.readAllContent(COURSE_FILENAME);
        List<Course> courseList = new ArrayList<Course>();
        if(!content.isEmpty()){
            courseList = Arrays.asList(mapper.readValue(content, Course[].class));
        }
        return courseList;
    }

    @Override
    public Course findByName(String name) throws Exception {
        List<Course> courseList = findAll();
        if (courseList != null && courseList.size() > 0) {
            return courseList.stream().
                    filter(course -> course.getName().equals(name))
                    .findFirst().orElse(null);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long courseID) throws Exception {
        List<Course> courseList = findAll();
        if (courseList != null && courseList.size() > 0) {
            courseList = courseList.stream().
                    filter(course -> course.getCourseID().equals(courseID))
                    .collect(Collectors.toList());
            FileUtil.overwriteFile(mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(courseList),COURSE_FILENAME);
        } else {
            return;
        }
    }

    @Override
    public void deleteById(String courseID) throws Exception {

    }

    @Override
    public void deleteStudentByName(String course, Long student_id) throws Exception {

    }

    @Override
    public Course update(Course course) throws Exception{
        List<Course> courseList = findAll();
        if (courseList != null && courseList.size() > 0) {
            for(int i=0;i<courseList.size();i++){
                if(courseList.get(i).getCourseID().equals( course.getCourseID())){
                    courseList.set(i,course);
                }
            }
            FileUtil.overwriteFile(mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(courseList),COURSE_FILENAME);
        } else {
            return null;
        }
        return course;
    }
}
