package ant;

public class test_runjava {
   public static void main(String[] args) {
      String email = args[0];
      notifyAdministratorviaEmail(email);
      System.out.println("Administrator "+email+" has been notified");
   }

   public static void notifyAdministratorviaEmail(String email) {
       //...
   }
}
