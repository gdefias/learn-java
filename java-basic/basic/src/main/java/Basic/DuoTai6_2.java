package Basic;
import static Basic.Print.*;

/**
 * Created by Defias on 2020/07.
 * Description: 构造器与多态 -  构造器的更完整调用顺序
 *
 * 1、在其他任何事物发生之前，将分配给对象的存储空间初始化为二进制的零（或某些特殊数据类型中与零等价的值null等）
 * 2、基类构造器（反复递归）
 * 3、按声明顺序调用成员的初始化方法
 * 4、调用导出类构造器的主体
 */
public class DuoTai6_2 {
    public static void main(String[] args) {
        new RoundGlyph(5);
    }
}


//基类
class Glyph {
    void draw() { print("Glyph.draw()"); }   //子类中会被覆盖

    Glyph() {  //构造器依赖draw方法
        print("Glyph() before draw()");
        draw();
        print("Glyph() after draw()");
    }
}

//导出类
class RoundGlyph extends Glyph {
    private int radius = 1;
    RoundGlyph(int r) {
        radius = r;
        print("RoundGlyph.RoundGlyph(), radius = " + radius);
    }
    void draw() {
        print("RoundGlyph.draw(), radius = " + radius);
    }
}