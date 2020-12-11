package Collection;

/**
 * Created by Defias on 2016/3/14.
 *
 * 栈类Stack （Vector的子类）  - 【已过时就不建议使用了】
 * 先进后出(FILO, First In Last Out)
 * 实现： 由于栈只允许在栈顶进行插入和删除操作，所以用数组线性表来实现栈比用链表来实现效率更高
 * Stack是通过数组实现的，而非链表。
 *
 * 最好是将LinkedList当作栈来使用
 *
 *
 java.util.Stack<E>
 ---------------------------------------------------
 +Stack()   创建一个空的栈
 +empty(): boolean   如果栈是空的，则返回真
 +peek(): E   返回栈中的顶部元素
 +pop(): E   返回并移除该栈中的顶部元素
 +push(o: E): E   增加一个新的元素到栈的顶部
 +search(o: Object): int   返回该栈中指定元素的位置

 */
import java.util.*;

public class TestStack {
    public static void main(String[] args) {
        Stack<Integer> myStack = new Stack<>();
        //System.out.println(myStack.peek());
        //System.out.println(myStack.pop());
        myStack.push(0); //Pushes an item onto the top of this stack
        myStack.push(1);
        myStack.push(3);
        myStack.push(2);




        //栈也可以顺序输出
        for(Integer i: myStack) {
            System.out.println(i);
        }

        //int d = myStack.pop(); //Removes the object at the top of this stack and returns that object as the value of this function
        //System.out.println(d);
        //d = myStack.peek();//Looks at the object at the top of this stack without removing it from the stack.
        //d = myStack.peek();
        //d = myStack.peek();
        //System.out.println(d);
        //
        //System.out.println(myStack.isEmpty());  //Tests if this stack is empty
        //
        //int w = myStack.search(new Integer(1));  //Returns the 1-based position where an object is on this stack
        //System.out.println(w);
        //
        //test();

    }

    public static void test() {
        Stack stack = new Stack();
        // 将1,2,3,4,5添加到栈中
        for(int i=1; i<6; i++) {
            stack.push(String.valueOf(i));
        }

        // 遍历并打印出该栈
        iteratorThroughRandomAccess(stack) ;

        // 查找“2”在栈中的位置，并输出
        int pos = stack.search("2");
        System.out.println("the postion of 2 is:"+pos);

        // pup栈顶元素之后，遍历栈
        stack.pop();
        iteratorThroughRandomAccess(stack) ;

        // peek栈顶元素之后，遍历栈
        String val = (String)stack.peek();
        System.out.println("peek:"+val);
        iteratorThroughRandomAccess(stack) ;

        // 通过Iterator去遍历Stack
        iteratorThroughIterator(stack) ;
    }

    /**
     * 通过快速访问遍历Stack
     */
    public static void iteratorThroughRandomAccess(List list) {
        String val = null;
        for (int i=0; i<list.size(); i++) {
            val = (String)list.get(i);
            System.out.print(val+" ");
        }
        System.out.println();
    }

    /**
     * 通过迭代器遍历Stack
     */
    public static void iteratorThroughIterator(List list) {

        String val = null;
        for(Iterator iter = list.iterator(); iter.hasNext(); ) {
            val = (String)iter.next();
            System.out.print(val+" ");
        }
        System.out.println();
    }
}
