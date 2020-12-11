package Event.Demo1;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-03
 *
 * 事件监听类
 */

//具体实现了事件监听接口 - 门2
public class DoorListener2 implements DoorListener {

    @Override
    public void doorEvent(DoorEvent event) {
        if (event.getDoorState() != null && event.getDoorState().equals("open")) {
            System.out.println("门打开2，同时打开走廊的灯");
        } else {
            System.out.println("门关闭2，同时关闭走廊的灯");
        }
    }

}
