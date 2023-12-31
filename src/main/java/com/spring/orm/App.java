package com.spring.orm;


import com.spring.orm.dao.StudentDao;
import com.spring.orm.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

public class App
{
    public static void main( String[] args ) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = true;
        while(flag){
            System.out.println();
            System.out.println("********************* Welcome to Spring ORM Project *********************");
            System.out.println("PRESS 1 to add new student");
            System.out.println("PRESS 2 to display all students");
            System.out.println("PRESS 3 to display one student");
            System.out.println("PRESS 4 to delete one student");
            System.out.println("PRESS 5 to update student");
            System.out.println("PRESS 6 to exit..");

            try {
                int input = Integer.parseInt(br.readLine());

                switch (input){
                    case 1 :
                        System.out.println("Enter Name : ");
                        String name = br.readLine();
                        System.out.println("Enter City : ");
                        String city = br.readLine();
                        Student student = new Student(new Random().nextInt(1000), name, city);
                        int inserted = studentDao.insert(student);
                        System.out.println("Record Inserted : "+ inserted + student);
                        break;
                    case 2 :
                        System.out.println("Getting all records : ");
                        List<Student> allStudents = studentDao.getAllStudents();
                        for (Student std : allStudents){
                            System.out.println(std);
                        }
                        break;
                    case 3 :
                        System.out.println("Enter id to get record : ");
                        int id = Integer.parseInt(br.readLine());
                        Student student1 = studentDao.getStudent(id);
                        if (null != student1) {
                            System.out.println(student1);
                        }else {
                            System.out.println("Pls check Id, Id not present in DB...");
                        }
                        break;
                    case 4 :
                        System.out.println("Enter id to delete record : ");
                        int deleteId = Integer.parseInt(br.readLine());
                        studentDao.delete(deleteId);
                        break;
                    case 5 :
                        System.out.println("Enter id to update record : ");
                        int updateId = Integer.parseInt(br.readLine());
                        System.out.println("Enter Name : ");
                        String updateName = br.readLine();
                        System.out.println("Enter City : ");
                        String updateCity = br.readLine();
                        studentDao.updateStudent(new Student(updateId, updateName, updateCity));
                        break;
                    case 6 :
                        System.out.println("Program exited...");
                        flag = false;
                        break;
                    default:
                        System.out.println("Invalid input, pls try with given options.");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Thanks for using this application...");

    }
}
