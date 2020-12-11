package Security.auth.demo2;
import java.security.Principal;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 */


public class DemoPrincipal implements Principal {
    private String name;

    public DemoPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
