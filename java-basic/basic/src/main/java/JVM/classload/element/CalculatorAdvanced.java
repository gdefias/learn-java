package JVM.classload.element;

/**
 * Created by Defias on 2017/9/13.
 */
public class CalculatorAdvanced implements ICalculator  {
    public String calculate(String expression) {
        return "Result is " + expression;
    }

    public String getVersion() {
        return "2.0";
    }
}
