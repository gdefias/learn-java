package Collection;

import java.util.AbstractCollection;
import java.util.Iterator;

/**
 * Created by Defias on 2020/07.
 * Description: 继承AbstractCollection
 */
public class TestAbstractCollection extends AbstractCollection<String> {
    private String[] pets =  new String[] {"zhangsan", "lisi", "wangwu"};

    public int size() {
        return pets.length;
    }

    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index = 0;
            public boolean hasNext() {
                return index < pets.length;
            }
            public String next() { return pets[index++]; }
            public void remove() { // Not implemented
                throw new UnsupportedOperationException();
            }
        };
    }
    public static void main(String[] args) {
        TestAbstractCollection c = new TestAbstractCollection();
        TestCollectionVsIterator.display(c);  //由于继承了AbstractCollection 所以TestCollectionSequence算作是Collection
        TestCollectionVsIterator.display(c.iterator());
    }
}