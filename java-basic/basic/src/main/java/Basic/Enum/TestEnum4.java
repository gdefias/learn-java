package Basic.Enum;
import Generics.element_generics.Generator;
import java.util.Random;
/**
 * Created by Defias on 2017/8/16.
 *
 * 使用枚举类实现接口
 */

public class TestEnum4 {
    public static void main(String[] args) {
        Gender.MALE.info();
        Gender.FEMALE.info();
        System.out.println("----------------");

        CartoonCharacter cc = CartoonCharacter.BOB;
        for(int i = 0; i < 10; i++)
            printNext(cc);
        System.out.println("----------------");

    }


    public static <T> void printNext(Generator<T> rg) {
        System.out.print(rg.next() + ", ");
    }
}

interface GenderDescription {
    public void info();
}

enum Gender implements GenderDescription {
    MALE, FEMALE;

    @Override
    public void info() {
        System.out.println("这是一个用于定义性别的枚举类");
    }
}


enum CartoonCharacter implements Generator<CartoonCharacter> {
    SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;
    private Random rand = new Random(47);

    public CartoonCharacter next() {
        return values()[rand.nextInt(values().length)];
    }
}