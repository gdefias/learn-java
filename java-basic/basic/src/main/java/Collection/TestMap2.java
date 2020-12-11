package Collection;

import Collection.generator.CountingGenerator;
import Collection.generator.MapData;
import Collection.generator.RandomGenerator;
import Generics.element_generics.Generator;
import Generics.element.Par;

import java.util.Iterator;

/**
 * Created by Defias on 2020/07.
 * Description: 填充Map容器 -- 数据生成器
 *
 */

public class TestMap2 {

    public static void main(String[] args) {
        // Pair Generator:
        System.out.println(MapData.map(new Letters(), 11));

        // Two separate generators:
        System.out.println(MapData.map(new CountingGenerator.Character(),
                new RandomGenerator.String(3), 8));

        // A key Generator and a single value:
        System.out.println(MapData.map(new CountingGenerator.Character(),
                "Value", 6));
        // An Iterable and a value Generator:
        System.out.println(MapData.map(new Letters(),
                new RandomGenerator.String(3)));
        // An Iterable and a single value:
        System.out.println(MapData.map(new Letters(), "Pop"));
    }
}


class Letters implements Generator<Par<Integer,String>>, Iterable<Integer> {
    private int size = 9;
    private int number = 1;
    private char letter = 'A';

    public Par<Integer,String> next() {
        return new Par<Integer,String>(number++, "" + letter++);
    }

    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            public Integer next() {
                return number++;
            }

            public boolean hasNext() {
                return number < size;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
