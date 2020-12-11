package json.gson;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-03
 */
public class User {
    //@Expose
    private String firstName;

    //@Expose(serialize = false)
    private String lastName;

    //@Expose(deserialize = false)
    private String emailAddress;

    private String password;
}
