package Event.Demo1;

/**
 * Created with IntelliJ IDEA.
 * Description: 主程序
 * User: Defias
 * Date: 2018-03

 java事件处理机制

 当事件发生时，事件源将事件对象传递给所有注册的监听器，监听器对象将利用事件对象中的信息决定如何对事件做出响应

 */
public class EventMain {
    public static void main(String[] args) {
        DoorSource manager = new DoorSource();   //事件源
        manager.addDoorListener(new DoorListener1());// 增加监听器1
        manager.addDoorListener(new DoorListener2());// 增加监听器2

        // 开门
        manager.fireWorkspaceOpened();

        System.out.println("我已经进来了");

        // 关门
        manager.fireWorkspaceClosed();
    }
}
