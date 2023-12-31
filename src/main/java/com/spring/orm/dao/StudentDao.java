package com.spring.orm.dao;

import com.spring.orm.entity.Student;
import org.springframework.orm.hibernate5.HibernateTemplate;

import javax.transaction.Transactional;
import java.util.List;

public class StudentDao {

    private HibernateTemplate hibernateTemplate;

    public StudentDao() {
    }

    public StudentDao(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }


    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    // create - save student data
    @Transactional
    public int insert(Student student){
        Integer i = (Integer) this.hibernateTemplate.save(student);
        return i;
    }

    // read - get single object
    public Student getStudent(int studentId){
        Student student = this.hibernateTemplate.get(Student.class, studentId);
        return student;
    }

    // read - get all students
    public List<Student> getAllStudents(){
        List<Student> students = this.hibernateTemplate.loadAll(Student.class);
        return students;
    }

    // update - update the data
    @Transactional
    public void updateStudent(Student student){
        this.hibernateTemplate.update(student);
    }

    // delete - delete data
    @Transactional
    public void delete(int id){
        this.hibernateTemplate.delete(getStudent(id));
    }
}
