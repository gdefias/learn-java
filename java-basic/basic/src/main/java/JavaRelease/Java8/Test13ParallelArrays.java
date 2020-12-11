package JavaRelease.Java8;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2019-07
 *
 * 并行数组
 * Java 8新增加了很多方法支持并行的数组处理。最重要的大概是parallelSort()这个方法显著地使排序在多核计算机上速度加快
 */
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;


public class Test13ParallelArrays {
    public static void main( String[] args ) {
        long[] arrayOfLong = new long [ 20000 ];

        Arrays.parallelSetAll( arrayOfLong,
                index -> ThreadLocalRandom.current().nextInt( 1000000 ) );

        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
                i -> System.out.print( i + " " ) );
        System.out.println();

        Arrays.parallelSort( arrayOfLong );
        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
                i -> System.out.print( i + " " ) );
        System.out.println();
    }
}
