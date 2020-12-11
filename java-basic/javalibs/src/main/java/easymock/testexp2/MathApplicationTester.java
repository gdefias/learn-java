package easymock.testexp2;

/**
 * Created by Defias on 2017/3/10.
 *
 * easymock
 *
 */

import easymock.testexp2.src.MathApplication;
import easymock.testexp2.src.CalculatorService;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

//@RunWith attaches a runner with the test class to initialize the test data
@RunWith(EasyMockRunner.class)
public class MathApplicationTester {

    //@TestSubject annotation is used to identify class which is going to use the mock object
    @TestSubject
    MathApplication mathApplication = new MathApplication();

    //@Mock annotation is used to create the mock object to be injected
    @Mock
    CalculatorService calcService;

    @Test(expected = RuntimeException.class)
    public void testAdd(){
        //add the behavior to throw exception
        EasyMock.expect(calcService.add(10.0,20.0)).andThrow(new RuntimeException("Add operation not implemented"));

        //activate the mock
        EasyMock.replay(calcService);

        //test the add functionality
        Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);

        //verify call to calcService is made or not
        EasyMock.verify(calcService);
    }
}