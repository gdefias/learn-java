package Event.Demo1;
import java.util.EventListener;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-03
 *
 * 事件监听接口（event listener） -- EventListener
 *
 * 监听器：一个实现了特定事件监听器接口的类的实例
 *
 * 对每个明确的事件的发生，都相应地定义一个明确的事件处理方法
 */


public interface DoorListener extends EventListener {
    public void doorEvent(DoorEvent event);  //对doorEvent事件的处理
}
