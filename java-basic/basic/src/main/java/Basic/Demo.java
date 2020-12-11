/**
 * Created by Defias on 2016/2/26.
 *
 * Demo
 *
 */
package Basic;



public class Demo {
	public static void  main(String[] args) {  //入口

		System.out.println("Demo!");
		class Student {   //定义类（内部类）
			String name;  //类包含的变量（属性）
			int age;
			float score;

			//类包含的函数（方法）
			void say() {
				System.out.println(name + "的年龄是：" + age + ", 成绩是：" + score);
			}  //基本数据类型与字符串进行“+”操作一般也会自动转换为字符串

		}

		Student stu1 = new Student(); //创建对象
		//操作类的成员
		stu1.name = "小明";
		stu1.age = 23;
		stu1.score = 99.5f;
		stu1.say();

		int rand1 = (int)(Math.random() * 100);  //随机数
		System.out.print(rand1);
	}


}
