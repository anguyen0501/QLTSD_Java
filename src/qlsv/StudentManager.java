package qlsv;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    public static Scanner scanner = new Scanner(System.in);
    private List<Student> studentList;
    private final StudentDao studentDao;

    /**
     * init StudentDao object and
     * read list student when init StudentManager object
     */
    public StudentManager() {
        studentDao = new StudentDao();
        studentList = studentDao.read();
    }
    public void add() {
        int id = (studentList.size() > 0) ? (studentList.size() + 1) : 1;
        System.out.println("student id = " + id);
        String name = inputName();
        int age = inputAge();
        String address = inputAddress();
        float gpa = inputGpa();
        Student student = new Student(id, name, age, address, gpa);
        studentList.add(student);
        studentDao.write(studentList);
    }
    public void edit(int id) {
        boolean isExisted = false;
        int size = studentList.size();
        for (Student student : studentList) {
            if (student.getId() == id) {
                isExisted = true;
                student.setName(inputName());
                student.setAge(inputAge());
                student.setAddress(inputAddress());
                student.setGpa(inputGpa());
                break;
            }
        }
        if (!isExisted) {
            System.out.printf("id = %d not existed.\n", id);
        } else {
            studentDao.write(studentList);
        }
    }
    public void delete(int id) {
        Student student = null;
        int size = studentList.size();
        for (Student value : studentList) {
            if (value.getId() == id) {
                student = value;
                break;
            }
        }
        if (student != null) {
            studentList.remove(student);
            studentDao.write(studentList);
        } else {
            System.out.printf("id = %d not existed.\n", id);
        }
    }
    public void sortStudentByName() {
        studentList.sort(new SortStudentByName());
    }
    public void sortStudentByGPA() {
        studentList.sort(new SortStudentByGPA());
    }
    public void show() {
        for (Student student : studentList) {
            System.out.format("%5d | ", student.getId());
            System.out.format("%20s | ", student.getName());
            System.out.format("%5d | ", student.getAge());
            System.out.format("%20s | ", student.getAddress());
            System.out.format("%10.1f%n", student.getGpa());
        }
    }
    public int inputId() {
        System.out.print("Input student id: ");
        while (true) {
            try {
                int id = Integer.parseInt((scanner.nextLine()));
                return id;
            } catch (NumberFormatException ex) {
                System.out.print("invalid! Input student id again: ");
            }
        }
    }
    private String inputName() {
        System.out.print("Input student name: ");
        return scanner.nextLine();
    }
    private String inputAddress() {
        System.out.print("Input student address: ");
        return scanner.nextLine();
    }
    private int inputAge() {
        System.out.print("Input student age: ");
        while (true) {
            try {
                return Byte.parseByte((scanner.nextLine()));
            } catch (NumberFormatException ex) {
                System.out.print("invalid! Input student id again: ");
            }
        }
    }
    private float inputGpa() {
        System.out.print("Input student gpa: ");
        while (true) {
            try {
                float gpa = Float.parseFloat((scanner.nextLine()));
                if (gpa < 0.0 && gpa > 10.0) {
                    throw new NumberFormatException();
                }
                return gpa;
            } catch (NumberFormatException ex) {
                System.out.print("invalid! Input student age again: ");
            }
        }
    }
    // getter && setter
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
