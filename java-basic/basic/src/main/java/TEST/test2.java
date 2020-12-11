package TEST;

/**
 * Created by Jeff on 2016/3/5.
 */
public class test2 {
    public static void main(String[] args) {
        char character = 'a';
        int i = 'a'-'A';
        System.out.print(i);

        char k = (char)(character - i);
        System.out.print(k);

        System.out.print(""+Character.MAX_VALUE);

    }
}
