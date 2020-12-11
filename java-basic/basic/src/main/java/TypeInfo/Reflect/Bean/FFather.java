package TypeInfo.Reflect.Bean;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 */
public class FFather {
    public String ffatherName;
    public Father father;

    public FFather(String ffatherName) {
        ffatherName = ffatherName;
    }

    public String getFFatherName() {
        return ffatherName;
    }

    public void setFFatherName(String ffatherName) {
        this.ffatherName = ffatherName;
    }

    private Father getFather() {
        return father;
    }

    public void setFather(Father father) {
        this.father = father;
    }

}
