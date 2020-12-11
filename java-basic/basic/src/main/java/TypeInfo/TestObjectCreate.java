package TypeInfo;

/**
 * Created by Defias on 2016/4/27.
 *
 * Create  Object
 *
 * 创建对象几种的方式：
 * 1、new
 * 2、反射: 能够分析类能力的程序
 * 3、克隆
 * 4、反序列化
 */

public class TestObjectCreate implements Cloneable {
	private String name = "initname";
	private int age = 0;

	public TestObjectCreate(){
		System.out.println("call default constructor");
	}

	public TestObjectCreate(String name, int age){
		this.name = name;
		this.age = age;
		System.out.println("call second constructor： name： " + name + " age： " + age);
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public boolean equals(Object o){
		if(this==o)
			return true;
		if(!(o instanceof TestObjectCreate))
			return false;
		final TestObjectCreate other=(TestObjectCreate)o;

		if(this.name.equals(other.name) && this.age==other.age)
			return true;
		else
			return false;
	}

	public String toString() {
        return "name=" + name + ", age=" + age;
	}

	public static void main(String args[]) throws Exception {
		//运用反射手段创建TestObject1对象
		Class objClass = Class.forName("TypeInfo.TestObjectCreate");  //如果类名不正确将报ClassNotFoundException异常
        TestObjectCreate c1 = (TestObjectCreate)objClass.newInstance();  //会调用TestObject1类的默认无参构造方法
        System.out.println("c1对象的类的名子： " + c1.getClass().getName());
		System.out.println("c1: " + c1);  //打印name=unknown,age=0

        Class objClass2 = TestObjectCreate.class;  //如果T是任意的JAVA类型，那么T.class代表匹配的类对象
        System.out.println((c1.getClass()==objClass2)?"Y":"N");
        System.out.println();

		//用new语句创建TestObject1对象
        TestObjectCreate c2 = new TestObjectCreate("Tom",20);
		System.out.println("c2: "+c2);  //打印name=tom,age=20
        System.out.println();

		//运用克隆手段创建TestObject1对象
        TestObjectCreate c3 = (TestObjectCreate)c2.clone();  //不会调用TestObject1类的构造方法
		System.out.println("c2==c3 : "+(c2==c3));  //打印false
		System.out.println("c2.equals(c3) : "+c2.equals(c3));  //打印true
		System.out.println("c3: "+c3);  //打印name=tom,age=20
	}
}

