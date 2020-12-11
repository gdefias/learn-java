package Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Defias on 2020/07.
 * Description: ListIterator(迭代器) - Iterator的子类
 *
 * 可以双向移动，还可以产生相对于迭代器在列表中指向的当前位置的前一个或后一个元素的索引
 *
 List接口的迭代器返回类（ListIterator继承Iterator类，且只能用于List及其子类型）
 java.util.ListIterator<E>
 -------------------------------------------------------------------------------
 +add(element: E): void   添加一个指定的对象到线性表中
 +hasPrevious(): boolean  当往回遍历时，如果该线性表遍历器还有更多的元素，则返回true
 +nextIndex(): int  返回下一个元素的索引
 +previous(): E  返回该线性表遍历器的前一个元素
 +previousIndex(): int  返回前一个元素的索引
 +set(element: E): void  使用指定的元素替换previous或next方法返回的最后一个元素
 */
public class TestListIterator {

    public static void main(String[] args) {
        List<String> pets = new ArrayList<>(Arrays.asList("123", "abc", "def"));
        ListIterator<String> it = pets.listIterator();

        while(it.hasNext())
            System.out.print(it.next() + ", " + it.nextIndex() +
                    ", " + it.previousIndex() + "; ");
        System.out.println();
        // Backwards:
        while(it.hasPrevious())
            System.out.print(it.previous() + " ");
        System.out.println();
        System.out.println(pets);
        System.out.println("-------------");

        it = pets.listIterator(1);  //返回针对从1开始的元素的迭代器
        while(it.hasNext()) {
            System.out.println(it.next());
            it.set("000");
        }
        System.out.println(pets);
    }
}
