package Basic;

/**
 * Created by Defias on 2020/06.
 * Description:
 */

public class JavaSwap {

    public static void main(String[] args) {
        int[] A = new int[] {1,2,3,4,5,6};
        swap1(A, 0, 1);
        for(int i=0; i<A.length; i++) {
            System.out.print(A[i] + " ");
        }
    }

    public static void swap1(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
