/**
 * Created by Defias on 2016/4/27.
 *
 * 重载构造方法
 */
package Basic;

public class JavaConstruction {
	private String name;
	private int age;

	public JavaConstruction(String name, int age) {
		this.name = name;
		this.age=age;
	}

	public JavaConstruction(String name) {
		this(name, -1);
	}

	public JavaConstruction() {
		this( "无名氏" );
	}

	public void setName(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age=age;
	}

	public int getAge() {
		return age;
	}

	public static void main(String args[]){
		JavaConstruction zhangsan = new JavaConstruction("张三",25);
		JavaConstruction lisi = new JavaConstruction("李四");
		JavaConstruction someone = new JavaConstruction();
	}

}
