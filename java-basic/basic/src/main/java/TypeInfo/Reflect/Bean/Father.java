package TypeInfo.Reflect.Bean;

/**
 * Created by Defias on 2017/9/5.
 */
public class Father {
    public String FatherName;
    private int FatherAge;

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String FatherName) {
        this.FatherName = FatherName;
    }

    private int getFatherAge() {
        return FatherAge;
    }

    public void setFatherAge(int FatherAge) {
        this.FatherAge = FatherAge;
    }

    public void printt() {
        System.out.println("HAOHAO123");
    }
}
