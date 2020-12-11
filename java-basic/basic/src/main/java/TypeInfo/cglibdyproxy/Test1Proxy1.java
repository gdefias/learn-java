package TypeInfo.cglibdyproxy;

/**
 * Created by Defias on 2017/8/24.
 *
 * 原始需求
 */

public class Test1Proxy1 {
    public static void main(String[] args) {
        TableDAO tableDao = TableDAOFactory.getInstance();
        doMethod(tableDao);
    }

    public static void doMethod(TableDAO dao){
        dao.create();
        dao.query();
        dao.update();
        dao.delete();
    }
}



//模拟对表的操作DAO类（CRUD）
class TableDAO {
    public void create(){
        System.out.println("create() is running !");
    }
    public void query(){
        System.out.println("query() is running !");
    }
    public void update(){
        System.out.println("update() is running !");
    }
    public void delete(){
        System.out.println("delete() is running !");
    }
}

//DAO工厂
class TableDAOFactory {
    private static TableDAO tDao = new TableDAO();

    public static TableDAO getInstance(){
        return tDao;
    }
}
