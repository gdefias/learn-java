/**
 * Created by Defias on 2016/3/6.
 *
 * 对象与克隆
 */
package Basic;

public class JavaClone2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Body body = new Body(new Head());
        Body body1 = (Body)body.clone();

        System.out.println("body == body1 : " + (body == body1) );
        System.out.println("body.head == body1.head : " +  (body.head == body1.head)); //false head进行了深拷贝

        System.out.println("body.head.face == body1.head.face : " +  (body.head.face == body1.head.face));
        //true face仍然为浅拷贝， 要想face也为深拷贝，需要在face类中也实现Cloneable接口
    }
}



class Body implements Cloneable{
    public Head head;
    public Body() {}
    public Body(Head head) {
        this.head = head;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Body newBody =  (Body) super.clone();
        newBody.head = (Head) head.clone();
        return newBody;
    }

}

class Head implements Cloneable {
    public Face face;
    public Head() {}

    public Head(Face face) {
        this.face = face;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Face {

}
