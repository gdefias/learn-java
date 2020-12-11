package json.jackson;

/**
 * Created by Defias on 2020/08.
 * Description:
 */
public class Car {
    public String brand = null;
    public int doors = 0;

    public String getBrand() {
        return this.brand;
    }

    public void   setBrand(String brand){
        this.brand = brand;
    }

    public int  getDoors() {
        return this.doors;
    }

    public void setDoors (int doors) {
        this.doors = doors;
    }
}
