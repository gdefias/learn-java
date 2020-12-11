package Generics;

import Generics.element.CountedObject;
import Generics.element_generics.BasicGenerator;
import Generics.element_generics.Generator;

/**
 * Created by Defias on 2020/07.
 * Description: 泛型的使用: 一个通用的生成器
 */

public class Test0_GenericsGenerator {

    public static void main(String[] args) {
        Generator<CountedObject> gen = BasicGenerator.create(CountedObject.class);
        for(int i = 0; i < 5; i++)  //很方便的生成任意多个CountedObject对象
            System.out.println(gen.next());
    }
}

