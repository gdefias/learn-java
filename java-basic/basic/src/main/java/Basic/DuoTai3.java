package Basic;
/**
 * Created by Defias on 2016/2/27.
 *
 * 多态
 */


public class DuoTai3 {
    public static void main(String[] args) {
        // 引用 PPeople 类的实例
        PPeople obj = new PPeople();
        if(obj instanceof Object) {   //instanceof运算符用来判断一个变量所引用的对象的实际类型（不是变量的类型）
            System.out.println("我是一个对象");
        }
        if(obj instanceof PPeople){
            System.out.println("我是人类");
        }
        if(obj instanceof TTeacher){   //父类不能当成是子类
            System.out.println("我是一名教师");
        }
        if(obj instanceof PPresident){
            System.out.println("我是校长");
        }
        System.out.println("-----------");

        // 引用 TTeacher 类的实例
        obj = new TTeacher();
        if(obj instanceof Object){
            System.out.println("我是一个对象");
        }
        if(obj instanceof PPeople){   //子类可以当成是父类
            System.out.println("我是人类");
        }
        if(obj instanceof TTeacher){
            System.out.println("我是一名教师");
        }
        if(obj instanceof PPresident) {
            System.out.println("我是校长");
        }
    }
}

class PPeople { 
    
}

class TTeacher extends PPeople { 
    
}

class PPresident extends TTeacher {

}
