package Event.Demo1;
import java.util.*;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-03
 *
 * 事件源对象/事件源（event source）
 *
 * 具体的事件源，比如说，你点击一个button，那么button就是event source，要想使button对某些事件进行响应，你就需要注册特定的listener
 *
 * 事件源：一个能够注册监听器对象并发送事件对象的对象
 * 持有事件监听器，触发事件，当事件发生时向事件监听器发布事件
 * 充当：事件源、事件监听器注册表、事件广播器
 *
 * 任何一个事件都拥有一个事件源
 *
 */

public class DoorSource {
    private Collection listeners;  //用一个集合来存储所有的事件监听器对象

    /**
     * 添加事件  --事件的注册
     *
     * @param listener
     *            DoorListener
     */
    public void addDoorListener(DoorListener listener) {
        if (listeners == null) {
            listeners = new HashSet();
        }
        listeners.add(listener);
    }

    /**
     * 移除事件
     *
     * @param listener
     *            DoorListener
     */
    public void removeDoorListener(DoorListener listener) {
        if (listeners == null)
            return;
        listeners.remove(listener);
    }

    /**
     * 触发开门事件
     */
    protected void fireWorkspaceOpened() {
        if (listeners == null)
            return;
        //新建开门事件对象（第一个参数本事件源；第二个参数事件状态）
        DoorEvent event = new DoorEvent(this, "open");
        notifyListeners(event);   //对象将事件通知给多有监听器
    }

    /**
     * 触发关门事件
     */
    protected void fireWorkspaceClosed() {
        if (listeners == null)
            return;
        DoorEvent event = new DoorEvent(this, "close");
        notifyListeners(event);
    }

    /**
     * 事件的发布 通知所有的事件监听器DoorListener
     */
    private void notifyListeners(DoorEvent event) {
        Iterator iter = listeners.iterator();
        while (iter.hasNext()) {
            DoorListener listener = (DoorListener) iter.next();
            listener.doorEvent(event);
        }
    }
}
