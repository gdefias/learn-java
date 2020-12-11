package Event.Demo1;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-03
 *
 * 事件监听类
 */

//具体实现了事件监听接口 - 门1
public class DoorListener1 implements DoorListener {

    @Override
    public void doorEvent(DoorEvent event) {
        if (event.getDoorState() != null && event.getDoorState().equals("open")) {
            System.out.println("门打开1");
        } else {
            System.out.println("门关闭1");
        }
    }

}
