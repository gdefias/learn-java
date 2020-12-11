package Exception;
import static Basic.Print.*;

/**
 * Created by Defias on 2020/07.
 * Description: 异常链
 *
 * 捕获到一个异常后抛出另一个异常，希望把原异常的信息保存下来
 */


class DynamicFieldsException extends Exception {}

public class TestExceptionChain {
    private Object[][] fields;

    public static void main(String[] args) {
        TestExceptionChain df = new TestExceptionChain(3);
        print(df.toString());
        try {
            df.setField("d", "A value for d");
            df.setField("number", 47);
            df.setField("number2", 48);
            print(df.toString());
            df.setField("d", "A new value for d");
            df.setField("number3", 11);
            print("df: " + df);
            print("df.getField(\"d\") : " + df.getField("d"));
            Object field = df.setField("d", null); // Exception
        } catch(NoSuchFieldException e) {
            e.printStackTrace(System.out);
        } catch(DynamicFieldsException e) {
            e.printStackTrace(System.out);
        }
    }

    public TestExceptionChain(int initialSize) {
        fields = new Object[initialSize][2];
        for(int i = 0; i < initialSize; i++)
            fields[i] = new Object[] { null, null };
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Object[] obj : fields) {
            result.append(obj[0]);
            result.append(": ");
            result.append(obj[1]);
            result.append("\n");
        }
        return result.toString();
    }

    private int hasField(String id) {
        for(int i = 0; i < fields.length; i++)
            if(id.equals(fields[i][0]))
                return i;
        return -1;
    }

    private int getFieldNumber(String id) throws NoSuchFieldException {
        int fieldNum = hasField(id);
        if(fieldNum == -1)
            throw new NoSuchFieldException();
        return fieldNum;
    }

    private int makeField(String id) {
        for(int i = 0; i < fields.length; i++)
            if(fields[i][0] == null) {
                fields[i][0] = id;
                return i;
            }
        // No empty fields. Add one:
        Object[][] tmp = new Object[fields.length + 1][2];
        for(int i = 0; i < fields.length; i++)
            tmp[i] = fields[i];
        for(int i = fields.length; i < tmp.length; i++)
            tmp[i] = new Object[] { null, null };
        fields = tmp;
        // Recursive call with expanded fields:
        return makeField(id);
    }

    public Object getField(String id) throws NoSuchFieldException {
        return fields[getFieldNumber(id)][1];
    }

    public Object setField(String id, Object value) throws DynamicFieldsException {
        if(value == null) {
            // Most exceptions don't have a "cause" constructor.
            // In these cases you must use initCause(),
            // available in all Throwable subclasses.
            DynamicFieldsException dfe = new DynamicFieldsException();
            dfe.initCause(new NullPointerException());  //把NullPointerException异常插入DynamicFieldsException异常
            throw dfe;
        }
        int fieldNumber = hasField(id);
        if(fieldNumber == -1)
            fieldNumber = makeField(id);
        Object result = null;
        try {
            result = getField(id); // Get old value
        } catch(NoSuchFieldException e) {
            // Use constructor that takes "cause":
            throw new RuntimeException(e);  //可以把检查异常包装进RuntimeException里成为免检异常
        }
        fields[fieldNumber][1] = value;
        return result;
    }

}
