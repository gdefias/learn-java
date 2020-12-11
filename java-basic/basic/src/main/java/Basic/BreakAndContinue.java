package Basic;
import static Basic.Print.*;
/**
 * Created by Defias on 2020/07.
 * Description:  使用标签的break和continue
 */
public class BreakAndContinue {
    public static void main(String[] args) {
        test1();
        //test2();

    }

    public static void test1() {
        int i = 0;
        outer: // Can't have statements here  标签与for语句之间不能有别的如何语句，否则语法错误
        for(; true ;) { // infinite loop
            inner: // Can't have statements here
            for(; i < 10; i++) {
                print("i = " + i);
                if(i == 2) {
                    print("continue");
                    continue;
                }
                if(i == 3) {
                    print("break");
                    i++; // Otherwise i never
                    // gets incremented.
                    break;
                }
                if(i == 7) {
                    print("continue outer");
                    i++; // Otherwise i never
                    // gets incremented.
                    continue outer;  //跳到外层后继续进入循环
                }
                if(i == 8) {
                    print("break outer");
                    break outer;  //跳到外层后循环就结束了
                }
                for(int k = 0; k < 5; k++) {
                    if(k == 3) {
                        print("continue inner");
                        continue inner;
                    }
                }
            }
        }
        // Can't break or continue to labels here
    }


    public static void test2() {
        int i = 0;
        outer:
        while(true) {
            print("Outer while loop");
            while(true) {
                i++;
                print("i = " + i);
                if(i == 1) {
                    print("continue");
                    continue;
                }
                if(i == 3) {
                    print("continue outer");
                    continue outer;
                }
                if(i == 5) {
                    print("break");
                    break;
                }
                if(i == 7) {
                    print("break outer");
                    break outer;
                }
            }
        }

        print("end");
    }
}
