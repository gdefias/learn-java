package Collection;
import static Basic.Print.*;
/**
 * Created by Defias on 2017/8/27.
 *
 * 位集/位图/位向量(BitSet/BitMap/BitVector)
 *
 * 想要高效率的存储大量开关信息，BitSet是很好的选择，不过它的效率仅是对空间而言的，访问时间比本地数组稍慢些
 *
 *
 * 基本原理：用1位来表示一个数据是否出现过，0为没有出现过，1表示出现过。使用的时候既可根据某位是否为0表示此数是否出现过
 * 一个1G的空间，有 8*1024*1024*1024=8.58*10^9bit，也就是可以表示85亿个不同的数
 *
 * BitSet是位操作的对象，值只有0或1即false和true，BitSet内部维护一个long整形数组
 * 初始只有一个long整形，所以BitSet最小的size是64位（8字节），随着存储的元素越来越多，BitSet内部会自动扩充，一次扩充64位，最终内部是
 * 由N个long整形来存储；数组的大小由bitSet接收的最大数字决定，这个数组将数字分段表示[0,63],[64,127],[128,191]...。即long[0]用来存
 * 储[0,63]这个范围的数字的存在性的，long[1]用来存储[64,127]的，依次轮推
 * 默认情况下，BitSet所有位都是0即false； 数组的最大长度（long整数的个数）计算公式：
 * (maxValue - 1) >> 6  + 1  即：(maxValue - 1) / 64  + 1
 *
 * BitSet可以有效的降低内存的使用量
 * BitSet只面向数字比较，比如set(int a,boolean value)方法，将数字a在bitSet中设定为true或者false；此后可以通过get(int a)方法检测
 * 结果，对于string类型的数据，如果使用BitSet，那么可以将其hashcode值映射在bitset中
 *
 * BitSet的优缺点:
 * 优点：按位存储，内存占用空间小; 丰富的api操作
 * 缺点：线程不安全; BitSet内部动态扩展long型数组，若数据稀疏会占用较大的内存
 *
 * BitSet的基本操作有：
 * 初始化一个bitset，指定/默认大小
 * 清空bitset
 * 反转某一指定位
 * 设置某一指定位
 * 获取某一位的状态
 * 当前bitset的bit总位数
 * and（与）
 * or（或）
 * xor（异或）
 *
 * 将null参数传递给BitSet中的任何方法都将导致NullPointerException
 *
 */

import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;

public class TestBitSet {
    public static void main(String[] args) {
        //test0();
        //test1();
        //test2();
        test3();
    }


    public static void test0() {
        BitSet bs = new BitSet();
        System.out.println(bs.isEmpty()+"--"+bs.size());  //默认为空， 64bit

        bs.set(0);  //将指定索引处的位设置为true
        System.out.println(bs.isEmpty()+"--"+bs.size());
        System.out.println(bs.get(0));  //返回指定索引处的位值

        bs.set(1, true);
        System.out.println(bs.get(65));  //如果当前已设置了此BitSet中索引bitIndex处的位，则返回true；否则结果为false
        System.out.println(bs.get(1));
        System.out.println(bs.isEmpty()+"--"+bs.size());

        bs.flip(1);  //将指定索引处的位值设置为其当前值的补码(反转)
        System.out.println(bs.get(1));
        bs.flip(1);
        System.out.println(bs.get(1));
        bs.flip(1);
        System.out.println(bs.get(1));

        bs.set(63);
        System.out.println(bs.size());   //默认可存放的最大值为63

        bs.set(64);
        System.out.println(bs.size());  //自动扩容了
        System.out.println(bs.get(64));

        System.out.println(bs);
        printBitSet0(bs);
        System.out.println("------------------");

        BitSet bs2 = new BitSet(64);  //创建指定大小的BitSet，BitSet可以表示的索引范围： 0 ~ nbits-1
        System.out.println(bs2.isEmpty()+"--"+bs2.size());

        BitSet bs4 = new BitSet(111);   //扩容大小按64的倍数
        System.out.println(bs4.isEmpty()+"--"+bs4.size());
        System.out.println("------------------");
    }

    public static void test1() {
        Random rand = new Random(47);
        // Take the LSB of nextInt():
        byte bt = (byte)rand.nextInt();
        BitSet bb = new BitSet();
        for(int i = 7; i >= 0; i--)
            if(((1 << i) &  bt) != 0)
                bb.set(i);
            else
                bb.clear(i);
        print("byte value: " + bt);
        printBitSet(bb);

        short st = (short)rand.nextInt();
        BitSet bs = new BitSet();
        for(int i = 15; i >= 0; i--)
            if(((1 << i) &  st) != 0)
                bs.set(i);
            else
                bs.clear(i);
        print("short value: " + st);
        printBitSet(bs);

        int it = rand.nextInt();
        BitSet bi = new BitSet();
        for(int i = 31; i >= 0; i--)
            if(((1 << i) &  it) != 0)
                bi.set(i);
            else
                bi.clear(i);
        print("int value: " + it);
        printBitSet(bi);

        // Test bitsets >= 64 bits:
        BitSet b127 = new BitSet();
        b127.set(127);
        print("set bit 127: " + b127);
        BitSet b255 = new BitSet(65);
        b255.set(255);
        print("set bit 255: " + b255);
        BitSet b1023 = new BitSet(512);
        b1023.set(1023);
        b1023.set(1024);
        print("set bit 1023: " + b1023);
    }


    public static void test2() {
        BitSet bits1 = new BitSet(16);
        BitSet bits2 = new BitSet(16);

        // set some bits
        for(int i=0; i<16; i++) {
            if((i%2) == 0) bits1.set(i);
            if((i%5) != 0) bits2.set(i);
        }
        System.out.println("Initial pattern in bits1: " + bits1);
        System.out.println("Initial pattern in bits2: " + bits2);

        // AND bits
        bits2.and(bits1);
        System.out.println("bits2.and(bits1)");
        System.out.println("bits1: " + bits1);
        System.out.println("bits2: " + bits2);

        // OR bits
        bits2.or(bits1);
        System.out.println("bits2.or(bits1)");
        System.out.println("bits1: " + bits1);
        System.out.println("bits2: " + bits2);

        // XOR bits
        bits2.xor(bits1);
        System.out.println("bits2.xor(bits1)");
        System.out.println("bits1: " + bits1);
        System.out.println("bits2: " + bits2);
    }


    public static void printBitSet0(BitSet bs){
        StringBuffer buf = new StringBuffer();
        buf.append("[\n");
        for(int i=0; i<bs.size(); i++){
            if(i<bs.size()-1){
                buf.append((bs.get(i))?"1,":"0,");
            } else {
                buf.append((bs.get(i))?"1":"0");   //最后一个不需要输出逗号
            }
            if((i+1)%8==0) {
                buf.append("\n");
            }
        }
        buf.append("]");
        System.out.println(buf.toString());
    }

    public static void test3() {
        BitSet bitSet = new BitSet();
        bitSet.set(3, true);
        bitSet.set(98, true);
        System.out.println(bitSet.cardinality()); //返回此BitSet中设置为true的位数

        byte[] bytes = bitSet2ByteArray(bitSet);
        System.out.println(Arrays.toString(bytes));


        bitSet = byteArray2BitSet(bytes);
        System.out.println(bitSet.cardinality());
        System.out.println(bitSet.get(3));
        System.out.println(bitSet.get(98));

        int i = bitSet.nextSetBit(0); //返回第一个设置为true的位的索引，这发生在指定的起始索引或之后的索引上
        while(i>=0) {
            System.out.print(i + "\t");
            i = bitSet.nextSetBit(i + 1);
        }
    }


    public static void printBitSet(BitSet b) {
        System.out.println("bits: " + b);
        StringBuilder bbits = new StringBuilder();
        for(int j = 0; j < b.size() ; j++)
            bbits.append(b.get(j) ? "1" : "0");
        System.out.println("bit pattern: " + bbits);
    }



    //将BitSet对象转化为ByteArray
    public static byte[] bitSet2ByteArray(BitSet bitSet) {
        byte[] bytes = new byte[bitSet.size() / 8];
        for (int i = 0; i < bitSet.size(); i++) {
            int index = i / 8;
            int offset = 7 - i % 8;
            bytes[index] |= (bitSet.get(i) ? 1 : 0) << offset;
        }
        return bytes;
    }


    //将ByteArray对象转化为BitSet
    public static BitSet byteArray2BitSet(byte[] bytes) {
        BitSet bitSet = new BitSet(bytes.length * 8);
        int index = 0;
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 7; j >= 0; j--) {
                bitSet.set(index++, (bytes[i] & (1 << j)) >> j == 1 ? true
                        : false);
            }
        }
        return bitSet;
    }
}
