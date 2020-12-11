package JVM.MEM.OOM;
import java.util.EmptyStackException;
/**
 * Created by Defias on 2016/4/27.

 清除过期的对象引用，以防潜在的内存泄漏


 如果一个栈先是增长，然后再收缩，从栈中弹出来的对象不会被当作垃圾回收，即使使用栈的程序不再引用这些对象，它们也不会被回收。因为栈内部维
 护着对这些对象的过期引用（obsolete reference）。指永远也不会再被解除的引用

 解决方法：pop()方法中清空引用。元素被弹出栈，指向它的引用就过期了，就能被垃圾回收了
 */


public class TestStackOOM {
	private Object[] elements;  //存放对象
	private int size = 0;
	private int capacityIncrement = 10;  //堆栈的容量增长的步长

	public static void main(String args[]){
		TestStackOOM stack = new TestStackOOM(1000);
		for(int a=0; a<1000; a++)
			stack.push(new Integer(a));

		for(int a=0; a<1000; a++)
			System.out.println(stack.pop());
	}

	public TestStackOOM(int initialCapacity, int capacityIncrement) {
		this(initialCapacity);
		this.capacityIncrement = capacityIncrement;
	}

	public TestStackOOM(int initialCapacity) {
		elements = new Object[initialCapacity];
	}

	public void push(Object object){
		ensureCapacity();
		elements[size++] = object;
	}

	public Object pop(){
		if(size==0)
			throw new EmptyStackException();
		Object object = elements[--size];
		elements[size] = null;  //解决方案：清除过期的对象引用
		return object;
	}

	//增加堆栈的容量
	private void ensureCapacity() {
		if(elements.length == size) {
			Object[] oldElements = elements;
			elements = new Object[elements.length+capacityIncrement];
			System.arraycopy(oldElements,0,elements,0,size);
		}
	}
}