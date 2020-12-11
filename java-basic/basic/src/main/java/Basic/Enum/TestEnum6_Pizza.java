package Basic.Enum;

public class TestEnum6_Pizza {

    private PizzaStatus status;

    public enum PizzaStatus {
        ORDERED (5) {      //带构造参数
            @Override
            public boolean isOrdered() {
                return true;
            }
        },
        READY (2) {
            @Override
            public boolean isReady() {
                return true;
            }
        },
        DELIVERED (0) {
            @Override
            public boolean isDelivered() {
                return true;
            }
        };

        private int timeToDelivery;

        public boolean isOrdered() {return false;}

        public boolean isReady() {return false;}

        public boolean isDelivered(){return false;}

        public int getTimeToDelivery() {
            return timeToDelivery;
        }

        PizzaStatus (int timeToDelivery) {
            this.timeToDelivery = timeToDelivery;
        }
    }

    public boolean isDeliverable() {
        return this.status.isReady();
    }

    public void printTimeToDeliver() {
        System.out.println("Time to delivery is " +
                this.getStatus().getTimeToDelivery());
    }

    // Methods that set and get the status variable.
    public PizzaStatus getStatus() {
        return status;
    }

    public void setStatus(PizzaStatus st) {
        status = st;
    }


    public static void givenPizaOrder_whenReady_thenDeliverable() {
        TestEnum6_Pizza testPz = new TestEnum6_Pizza();
        testPz.setStatus(TestEnum6_Pizza.PizzaStatus.READY);
        System.out.println(testPz.isDeliverable());
    }

    public static void main(String[] args) {
        givenPizaOrder_whenReady_thenDeliverable();
    }
}