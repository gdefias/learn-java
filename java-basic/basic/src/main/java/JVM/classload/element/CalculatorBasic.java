package JVM.classload.element;

/**
 * Created by Defias on 2017/9/13.
 */
public class CalculatorBasic implements ICalculator {
    public String calculate(String expression) {
        return expression;
    }

    public String getVersion() {
        return "1.0";
    }
}
