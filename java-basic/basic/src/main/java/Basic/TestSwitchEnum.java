package Basic;

import java.util.Random;

/**
 * Created by Defias on 2020/07.
 * Description:
 *
 * JDK1.5开始有枚举类型，同时switch语句支持枚举
 *
 * 编译器一般并不会抱怨switch语句缺少default语句，但是如果case语句中有调用return，那么编译器就会抱怨了，与是否覆盖所有分支无关
 */
public class TestSwitchEnum {
    public static void main(String[] args) {
        int len = Color.values().length;
        Color color = Color.getColor(len);
        switch (color) {
            case RED:
                System.out.println("select " + "RED");
                break;
            case GREEN:
                System.out.println("select " + "GREEN");
                break;
            case BLUE:
                System.out.println("select " + "BLUE");
                break;
            case YELLOW:
                System.out.println("select " + "YELLOW");
                break;
            default:
                System.out.println("select " + "unknow!!");
                break;
        }
    }

    public enum Color {
        RED("red color", 0),
        GREEN("green color", 1),
        BLUE("blue color", 2),
        YELLOW("yellow color", 3);

        Color(String name, int id) {
            _name = name;
            _id = id;
        }

        private String _name;
        private int _id;

        public String getName() {
            return _name;
        }

        public int getId() {
            return _id;
        }

        public static Color getColor(int max) {
            Random random = new Random(System.currentTimeMillis());
            int num = random.nextInt(max);
            switch (num) {
                case 0:
                    return Color.RED;
                case 1:
                    return Color.GREEN;
                case 2:
                    return Color.BLUE;
                case 3:
                    return Color.YELLOW;
                default:
                    return Color.BLUE;
            }
        }
    }
}
