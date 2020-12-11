package Basic.Enum;
import java.io.Serializable;
import java.util.*;
/**
 * Created by Defias on 2016/4/27.
 *
 * 枚举类
 *
 * 利用静态工厂方法创建枚举类(实例的数目有限)
 */

public class TestEnumFactoryCreate implements Serializable {
	private final Character sex;
	private final transient String description;
	private static final Map<Character, TestEnumFactoryCreate> instancesBySex =
			new HashMap<Character, TestEnumFactoryCreate>();

	public static final TestEnumFactoryCreate FEMALE =
			new TestEnumFactoryCreate(new Character('F'), "Female");

	public static final TestEnumFactoryCreate MALE =
			new TestEnumFactoryCreate(new Character('M'), "Male");


	public static void main(String args[]){
		TestEnumFactoryCreate female = TestEnumFactoryCreate.getInstance(new Character('F'));
		TestEnumFactoryCreate male = TestEnumFactoryCreate.getInstance(new Character('M'));
	}


	//把构造方法声明为private类型，以禁止外部程序创建JavaEnumC类的实例
	private TestEnumFactoryCreate(Character sex, String description) {
		this.sex = sex;
		this.description = description;
		instancesBySex.put(sex, this);
	}


	//按照参数指定的性别缩写查找JavaEnumC实例
	public static TestEnumFactoryCreate getInstance(Character sex) {
		TestEnumFactoryCreate result = (TestEnumFactoryCreate)instancesBySex.get(sex);
		if (result == null) {
			throw new NoSuchElementException(sex.toString());
		}
		return result;
	}

	public Character getSex() {
		return sex;
	}
	public String getDescription() {
		return description;
	}

	public static Collection getAllValues() {
		return Collections.unmodifiableCollection(instancesBySex.values());
	}

	public String toString() {
		return description;
	}

	//保证反序列化时直接返回JavaEnumC类包含的静态实例
	private Object readResolve() {
		return getInstance(sex);
	}
}


