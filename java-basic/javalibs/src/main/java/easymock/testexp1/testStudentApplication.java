package easymock.testexp1;

/**
 * Created by Defias on 2017/3/10.
 *
 * easymock使用步骤：
 * 1、使用EasyMock生成Mock对象
 * 2、设定Mock对象的预期行为和输出
 * 3、将Mock对象切换到Replay状态
 * 4、调用Mock对象方法进行单元测试
 * 5、对Mock对象的行为进行验证
 *
 */
/**
 easymock限制：
 1) 不能mock类的final方法。如果final方法被调用，则只能执行原有的正常代码
 2) 不能mock类的static方法
 3) 同样如果private方法被调用，只能执行原有的正常代码
 4) 不能mock类的一些特殊方法: equals(), toString()和hashCode().原因是easymock在实现是为每个class mock对象提供了内建的以上三个方法。需要强调的是，对于基于interface的mock，这个限制也是同样存在的，即使以上三个方式是interface定义的一部分

 Capture

 MocksControl

 * */
import easymock.testexp1.src.IStudent;
import easymock.testexp1.src.StudentApplication;

import org.easymock.EasyMock;
import org.junit.Test;

public class testStudentApplication {

    IStudent student;

    StudentApplication application;

    @Test
    public void testdoMethod() {
        //使用EasyMock生成Mock对象；
        student = EasyMock.createMock(IStudent.class);
        /*
        Mock对象类型有三种
        Normal — EasyMock.createMock() 默认mock出来的都是这个类型，只关心调用方法的参数，不关心调用的次序
        Strict — EasyMock.createStrictMock(): 当调用方法有次序要求的时候需要mock这个类型
        Nice — EasyMock.createNiceMock(): 调用到不期望的方法时不会使测试失败，如果原方法返回的类型是number的 它就会返回0,如果是boolean 它会返回false,如果是object它会返回null
        * */

        //设定Mock对象的预期行为和输出
        EasyMock.expect(student.doMethod1()).andReturn("a").times(1);
        EasyMock.expect(student.doMethod2()).andReturn("c").times(1);
        EasyMock.expect(student.doMethod3()).andReturn("g").times(1);
        /*
        EasyMock.expect(student.doMethod3()).andThrow(new java.sql.SQLException("ID conflicts！"));    //抛出异常
        Easymock.expectLastCall(student.doMethod2()).andReturn("c").times(1);    //如果mock对象的方法返回是void，则需要使用expectLastCall()
        expect(mock.voteForRemoval("Document"))
        .andReturn((byte) 42).times(3)   //前3次调用将返回42
        .andThrow(new RuntimeException()).times(4)    //随后的4次调用(第4,5,6,7次)都将抛出异常
        .andReturn((byte) -42);   //第8次调用时将返回-42

        参数匹配：
        expect(userDao.insertUser(EasyMock.<User>anyObject())).andReturn(true);
        anyInt()、anyShort()、anyByte()、anyLong()、anyFloat()、anyDouble()、anyBoolean()、 matches(正则表达式)
        */

        //将Mock对象切换到Replay状态
        EasyMock.replay(student);

        //调用Mock对象方法进行单元测试
        application = new StudentApplication();
        application.setStudent(student);
        String getStr= application.doMethod();

        //对Mock对象的行为进行验证
        //String cstr="acg";  //正确的字符串
        //Assert.assertEquals(getStr, cstr);
        EasyMock.verify(student);
    }
}
