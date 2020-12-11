/**
 * Created by Defias on 2017/2/23.
 *
 * 可变长度参数
 *
 */
package Basic;

public class DemoXcanshu {
    public static void main(String[] args) {
        printMax(1,2,12,23,45,6,2,32,12,3456,566,33,98,3);
        printMax(new int[] {22,3,4,6,7});
    }

    public static void printMax(int... nums) {  //只能给方法制定一个可变长度参数，且可变长度参数要位于最后一个参数
        if(nums.length == 0) {  //java将可变参数nums当数组对待
            return;
        }

        int max = nums[0];
        for(int i=0; i<nums.length; i++) {
            if(nums[i] > max) {
                max = nums[i];
            }
        }
        System.out.println(max);
    }
}
