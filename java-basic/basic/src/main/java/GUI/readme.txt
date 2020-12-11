
【AWT】
AWT: 抽象窗口工具箱（Astract Window Toolkit）
重量级组件， 提供了容器类、众多的组件类和布局管理器类
AWT组件与本地GUI绑定，AWT绘制的图像界面在不同的操作系统中会有不同的外观

Frame  顶层窗口
Component  组件类，抽象类，所有除了菜单类组件之外的AWT组件的父类
Container  容器类，继承Component类，存放别的组件

AWT构建图形用户界面的机制包括：
1、提供了一些容器组件（Frame、Panel），用来容纳其他组件（按钮Button、复选框Checkbox、文本框TextField）
2、用布局管理器管理组件在容器上的布局
3、利用监听器来响应各种事件，实现用户与程序的交互
4、提供一套绘图机制来自动维护或刷新图形界面

AWT的事件处理机制：
一种委派式事件处理方式：普通组件(事件源)将整个事件处理委托给特定的对象(事件监听器);当该事件源发生指定的事件时，就通知所委托的事件监听器，由事件监听器
来处理这个事件。每个事件监听器也可以监听一个或多个事件源。因为同一个事件源上可能发生多个事件源。因为同一个事件源上可能发生多种事件，委派式事件处理方式
可以把事件源上所有可能发生的事件分别授权给不同的事件监听器来处理；同时也可以让一类事件都使用同一个事件监听器来处理

AWT事件分为两大类：低级事件和高级事件
1）低级事件：低级事件是指基于特定动作的事件。比如进入、点击、拖放等动作的鼠标事件，当组件得到焦点、失去焦点时触发焦点事件
ComponentEvent：组件事件，当组件尺寸发生变化、位置发生移动、显示/隐藏状态发生时触发该事件
ContainerEvent：容器事件，当容器里发生添加组件、删除组件时触发该事件
WindowEvent：窗口事件，当窗口状态发生改变（如打开、关闭、最大化、最小化）时触发该事件
FocusEvent：焦点事件，当组件得到焦点或处理失去焦点时触发该事件
KeyEvent：键盘时间，当按键被按下、松开、单击时触发该事件
MouseEvent：鼠标事件，当进行单击、按下、松开、移动鼠标等动作时触发该事件
PaintEvent：组件绘制时间，该事件是一个特殊的事件类型，当GUI组件调用update/paint方法来呈现自身时触发该事件，该事件并非专用于事件处理模型

2）高级事件（语义事件）：高级事件是基于语义的事件，它可以不和特定的动作相关联，而依赖于触发此事件的类。比如，在TextField中按Enter键会触发
ActionEvent事件，在滑动条上移动滑块会触发AdjustmentEvent事件，选中项目列表的某一项就会触发ItemEvent事件
ActionEvent：动作事件，事件当按钮、菜单项被点击，在TextField中按Enter键时触发该事件
AdjustmentEvent：调节事件，在滑动条上移动滑块以调节数值时触发该事件
ItemEvent：选项事件，当用户选中某项，或取消选中某项时触发该事件
TextEvent：文本事件，当文本框、文本域里的文本发生改变时触发该事件

AWT事件继承关系图：
EventObject
    ↑
AWT Event
    ↑
Component Event | Action Event | Adjustment Event | Item Event
    ↑
Input Event | Focus Event | Paint Event | Window Event
    ↑
Mouse Event | Key Event
    ↑
MouseWheel Event



常用事件和事件监听器：
事件类型	    对应的监听器	        监听器接口中的抽象方法                     触发时机
----------------------------------------------------------------------------------------------------------------------------------------
Action	    ActionListener	    actionPerformed(ActionEvent e)          按钮、文本框、菜单项被单击时
Mouse	    MouseListener	    mouseClicked(MouseEvent e)
                                mouseEntered(MouseEvent e)
                                mouseExited(MouseEvent e)
                                mousePressed(MouseEvent e)
                                mouseReleased(MouseEvent e)
MouseMotion	MouseMotionListener	mouseDragged(MouseEvent e)
                                mouseMoved(MouseEvent e)
Item	    ItemListener	    itemStateChanged(ItemEvent e)
Key	        KeyListener     	keyPressed(KeyEvent e)
                                keyReleased(KeyEvent e)
                                keyTyped(KeyEvent e)
Focus	    FocusListener	    focusGained(FocusEvent e)
                                focusLost(FocusEvent e)
Window	    WindowListener	    windowActivated(WindowEvent e)
                                windowClosed(WindowEvent e)
                                windowClosing(WindowEvent e)
                                windowDeactivated(WindowEvent e)
                                windowDeiconified(WindowEvent e)
                                windowIconified(WindowEvent e)
                                windowOpened(WindowEvent e)
Component	ComponentListener	componentHidden(ComponentEvent e)
                                componentMoved(ComponentEvent e)
                                componentResized(ComponentEvent e)
                                componentShown(ComponentEvent e)
Text	    TextListener	    textValueChanged(TextEvent e)


事件适配器
事件适配器其实就是一个接口的实现类，实际上适配器类只是将监听接口方法中的方法全部实现成空方法。这样在定义事件监听器时就可以继承该实现类，并重写所需
要的方法，不必实现覆盖所有方法了。常用的事件适配器类：
适配器	            说明
------------------------------------------
MouseAdapter	鼠标事件适配器
WindowAdapter	窗口事件适配器
KeyAdapter	    键盘事件适配器
FocusAdapter	焦点适配器
MouseMotionAdapter	鼠标移动事件适配器
ComponentAdapter	组件源适配器
ContainerAdapter	容器源事件适配器



【Swing】
Swing： 用户界面类
java基础类库（JFC  Java Foundation Class）的一部分，由纯JAVA语言编写而成，不依赖本地操作系统，独立于本地平台, 基于AWT组件结构之上，
没有完全替代AWT，给予不同平台的用户一致的感觉

JFrame  顶层窗口，对应AWT的Frame
JComponemt  容器类的子类。除JFrame之外，其余的Swing组件都继承了JComponemt类

AWT和Swing中组件类继承层次：
        Object
           ^
           |
       Component
           ^
           |
       Container
           ^
           |
     |            |
JComponemt     Windows
     ^            ^
     |            |
  JPanel        Frame
                  ^
                  |
               JFrame


图形类之间的关系:

     Point2D                     Shape
        ^                          ^
        |                          |
      Point           |                         |
                    Line2D              Rectangular Shape
                                                ^
                                                |
                                         |              |
                                      Ellipse2D      Rectangle2D
                                                         ^
                                                         |
                                                      Rectangle

2D矩形类：
            Rectangle2D
                 ^
                 |
       |                    |
 Rectangle2D.Float   Rectangle2D.Double




【javaFX】
用于取代Swing