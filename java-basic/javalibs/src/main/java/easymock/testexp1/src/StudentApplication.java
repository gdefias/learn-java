package easymock.testexp1.src;

/**
 * Created by Defias on 2017/3/10.
 */

//应用程序：依赖IStudent的实现类
public class StudentApplication {
    IStudent student = null;
    public StudentApplication() {
    }

    public IStudent getStudent() {
        return student;
    }

    public void setStudent(IStudent student) {
        this.student = student;
    }

    public String doMethod() {
        String str1 = student.doMethod1();
        String str2 = student.doMethod2();
        String str3 = student.doMethod3();
        return str1+str2+str3;
    }

}
