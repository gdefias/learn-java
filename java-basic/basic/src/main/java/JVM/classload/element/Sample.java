package JVM.classload.element;

/**
 * Created by Defias on 2017/9/13.
 */

public class Sample {
    private Sample instance;

    public void setSample(Object instance) {
        this.instance = (Sample) instance;
    }

    public void print() {
        System.out.println("I am Sample class!");
    }
}
