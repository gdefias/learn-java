package TypeInfo.Reflect.Bean;

/**
 * Created by Defias on 2017/8/23.
 */
public class CPerson extends Father implements China {

    public CPerson() {

    }
    public CPerson(String sex){
        this.sex=sex;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    @Override
    public void sayChina(){
        System.out.println("hello ,china");
    }
    @Override
    public void sayHello(String name, int age){
        System.out.println(name+"  "+age);
    }

    @Override
    public String toString(){
        return "["+this.name+"  "+this.age+"  " +this.sex + "]";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String sex;
    public String address;
}
