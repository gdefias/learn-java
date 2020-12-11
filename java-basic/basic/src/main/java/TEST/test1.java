package TEST;

/**
 * Created by Jeff on 2016/3/5.
 */
class Student {
    public int id;

    public Student(int id) {
        this.id = id;
    }
}

public class test1 {
    /**
     * Declare a public attribute `students` which is an array of `Student`
     * instances
     */
    // write your code here.
    public Student[] students;

    /**
     * Declare a constructor with a parameter n which is the total number of
     * students in the *class*. The constructor should create n Student
     * instances and initialized with student id from 0 ~ n-1
     */
    // write your code here
    test1(int n) {
        students = new Student[n];
        for(int i=0; i<n; i++) {
            students[i] = new Student(i);
        }
    }

    public static void main(String[] args) {
        test1 test1O = new test1(3);
        for(int i=0; i<3; i++) {
            System.out.println(test1O.students[i].id);
        }
    }
}