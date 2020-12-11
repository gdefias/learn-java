/**
 * Created by Defias on 2016/4/27.
 *
 * 不可变类
 *
 * 不可变类：创建了这个类的实例后就不允许修改它的属性
 * 具有实例缓存的不可变类
 *
 */
package Basic;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.lang.ref.*;

public class TestImmutableC {
	private final String firstname;
	private final String lastname;

	public TestImmutableC(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname(){  //没有提供setXXX方法
		return firstname;
	}
	public String getLastname(){
		return lastname;
	}
	public boolean equals(Object o){
		if (this == o) return true;
		if (!(o instanceof TestImmutableC)) return false;

		final TestImmutableC name = (TestImmutableC) o;
		if(!firstname.equals(name.firstname)) return false;
		if(!lastname.equals(name.lastname)) return false;
		return true;
	}

	public int hashCode() {
		int result;
		result= (firstname==null?0:firstname.hashCode());
		result = 29 * result + (lastname==null?0:lastname.hashCode());
		return result;
	}

	public String toString(){
		return lastname+" "+firstname;
	}

	//实例缓存，存放TestImmutableC对象的软引用
	private static final Set<SoftReference<TestImmutableC>> names=
			new  HashSet<SoftReference<TestImmutableC>>();

	public static TestImmutableC valueOf(String firstname, String lastname){  //静态工厂方法
		Iterator<SoftReference<TestImmutableC>> it=names.iterator();
		while(it.hasNext()){
			SoftReference<TestImmutableC> ref=it.next();
			TestImmutableC name=ref.get();
			if(name!=null
					&& name.firstname.equals(firstname)
					&& name.lastname.equals(lastname))
				return name;
		}
		TestImmutableC name=new TestImmutableC(firstname,lastname);
		names.add(new SoftReference<TestImmutableC>(name));
		return name;
	}

	public static void main(String args[]){
		TestImmutableC n1=TestImmutableC.valueOf("小红","王");
		TestImmutableC n2=TestImmutableC.valueOf("小红","王");
		TestImmutableC n3=TestImmutableC.valueOf("小东","张");
		System.out.println(n1);
		System.out.println(n2);
		System.out.println(n3);
		System.out.println(n1==n2);
	}
}
