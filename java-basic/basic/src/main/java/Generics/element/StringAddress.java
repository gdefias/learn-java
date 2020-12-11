package Generics.element;

/**
 * Created by Defias on 2020/07.
 * Description:
 */
public class StringAddress {
    private String s;
    public StringAddress(String s) { this.s = s; }
    public String toString() {
        return super.toString() + " " + s;
    }
}
