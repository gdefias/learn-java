package Generics.element_generics;


import org.apache.poi.ss.formula.functions.T;

//实现泛型接口
public class InfoImp<U> implements Info<U> {  //这里的类型参数标识符可以与接口的相同为T，也可以不同
    private U var;

    // 定义泛型构造方法
    public InfoImp(U var) {
        this.setVar(var);
    }

    public void setVar(U var) {
        this.var = var;
    }

    public U getVar() {
        return this.var;
    }

    //单独的泛型方法
    public  <N extends Number> N getMax(N[] array) {
        N max = array[0];
        for(N element : array) {
            max = element.doubleValue() > max.doubleValue() ? element : max;
        }
        return max;
    }
}

