package TypeInfo.cglibdyproxy;
import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.NoOp;
/**
 * Created by Defias on 2017/9/5.
 *
 * 需求再次变更： 开放其他人CRUD操作中的查询功能权限
 */

public class Test1Proxy3 {
    public static void main(String[] args) {
        haveAuthByFilter();
    }

    public static void haveAuthByFilter(){
        //AuthProxy已在TestProxyCGLib2中实现，直接使用即可
        TableDAO tDao = TableDAOFactory3.getAuthInstanceByFilter(new AuthProxy("张三"));
        doMethod(tDao);

        tDao = TableDAOFactory3.getAuthInstanceByFilter(new AuthProxy("李四"));  //现在李四有权查询了
        doMethod(tDao);
    }

    public static void doMethod(TableDAO dao){
        dao.create();
        dao.query();
        dao.update();
        dao.delete();
    }
}


//DAO工厂
class TableDAOFactory3 {
    public static TableDAO getAuthInstanceByFilter(AuthProxy authProxy){
        Enhancer en = new Enhancer();
        en.setSuperclass(TableDAO.class);

        //除了设置回调
        en.setCallbacks(new Callback[]{authProxy, NoOp.INSTANCE});

        //还要设置回调过滤器
        en.setCallbackFilter(new AuthProxyFilter());
        return (TableDAO)en.create();
    }
}


//方法过滤器（CallbackFilter）
class AuthProxyFilter implements CallbackFilter {

    public int accept(Method arg0) {
        if(!"query".equalsIgnoreCase(arg0.getName()))
            return 0;
        return 1;
    }

}

