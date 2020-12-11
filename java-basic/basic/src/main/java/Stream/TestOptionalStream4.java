package Stream;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;
/**
 * Created by Defias on 2020/07.
 * Description: Optional流 （Streams of Optionals）

 假设有一个可能产生 null 的生成器。如果使用这个生成器来创建流，自然的想用 Optional 来包装元素
 */
class TestOptionalStrea4 {
    public static void main(String[] args) {
        Signal.stream()
                .limit(10)
                .forEach(System.out::println);
        System.out.println(" ---");
        Signal.stream()
                .limit(10)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(System.out::println);
    }
}


class Signal {
    private final String msg;

    public Signal(String msg) { this.msg = msg; }

    public String getMsg() { return msg; }

    @Override
    public String toString() {
        return "Signal(" + msg + ")";
    }

    static Random rand = new Random(47);

    public static Signal morse() {
        switch(rand.nextInt(4)) {
            case 1: return new Signal("dot");
            case 2: return new Signal("dash");
            default: return null;
        }
    }

    public static Stream<Optional<Signal>> stream() {
        return Stream.generate(Signal::morse).map(signal -> Optional.ofNullable(signal));
    }
}