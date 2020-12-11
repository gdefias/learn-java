package Collection;

/**
 * Created by Defias on 2016/3/1.
 *
 * 向量类Vector - 【已过时就不建议使用了】
 *
 * 除了包含用于访问和修改向量的同步方法之外，Vector类与ArrayList类是一样的，Vector是线程安全的，但效率比ArrayList慢
 *
 java.util.Vector <E>
 ------------------------------------------------------------------------------------
 +Vector()  创建一个初始容量为10的默认空向量
 +Vector(c: Collection<? extends E>)   从一个已经存在的合集中创建一个向量
 +Vector(initialCapacity: int)   创建一个给定初始容量的向量
 +Vector(initCapacity: int, capacityIncr: int)   创建一个给定初始容量和增量的向量
 +addElement(o: E): void     将一个元素添加到该向量的末尾
 +capacity(): int   返回该向量的当前容量
 +copyInto(anArray: Object[]): void  将该向量中的元素复制到数组中
 +elementAt(index: int): E   返回指定索引位置的对象
 +elements(): Enumeration<E>   返回该向量的一个枚举
 +ensureCapacity(): void   增加该向量的容量
 +firstElement(): E  返回该向量中的第一个元素
 +insertElementAt(o: E, index: int): void   插入o到该向量中的指定索引位置
 +lastElement(): E  返回该向量中的最后一个元素
 +removeAllElements(): void  移除该向量中的所有元素
 +removeElement(o: Object): boolean   移除该向量中的第一个匹配的元素
 +removeElementAt(index: int): void  移除指定索引位置的元素
 +setElementAt(o: E, index: int): void   在指定索引位置设置一个新的元素
 +setSize(newSize: int): void  为该向量设置一个新的大小
 +trimToSize(): void   裁剪该向量的容量到它的大小
 */

import java.util.*;

class Cattt {
        private int catNumber;
        Cattt(int i) {
            catNumber = i;
        }
    void print() {
        System.out.println("Collection.Cattt #" + catNumber);
    }
}

class Doggg {
	private int dogNumber;
	Doggg(int i) {
		dogNumber = i;
	}
	void print() {
		System.out.println("Collection.Doggg #" + dogNumber);
	}
}

public class TestVector {
	public static void main(String[] args) {
		Vector cats = new Vector();
		for(int i=0; i<7; i++)
			cats.addElement(new Cattt(i));

		// Not a problem to add a dog to cats:
		//cats.addElement(new Collection.Doggg(7));
		for(int i=0; i <cats.size(); i++)
			((Cattt)cats.elementAt(i)).print();
			// Collection.Doggg is detected only at run-time
	}
}