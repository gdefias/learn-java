package Event.Demo1;
import java.util.EventObject;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-03
 *
 * 事件对象/事件（或事件状态对象 event object）
 *
 * 用于listener的相应的方法之中，作为参数，一般存在与listerner的方法之中
 *
 * EventObject
 * public class EventObject extends Object implements SerializableIO
 * 所有EventObject在构造时都引用了对象 "source"，source对象是发生有关Event的对象的源头
 *
 */

public class DoorEvent extends EventObject {
    private static final long serialVersionUID = 6496098798146410884L;

    private String doorState = "";   //表示门的状态，有“开”和“关”两种

    public DoorEvent(Object source, String doorState) {  //第一个参数是必须的
        super(source);
        this.doorState = doorState;
    }

    public void setDoorState(String doorState) {
        this.doorState = doorState;
    }

    public String getDoorState() {
        return this.doorState;
    }
}
