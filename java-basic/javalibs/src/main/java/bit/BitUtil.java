package bit;

/**
 * Created by Defias on 2017/9/1.
 */
public class BitUtil {

    /**
     * int到byte[]
     * @param i
     * @return
     * 大端字节序Big Endian（网络字节序）  存放在内存中最低位的数值是来自数据的最左边边部分（也就是数据的最高位部分）
     */
    public static byte[] intToBigEndianBytes(int i) {
        byte[] result = new byte[4];
        result[0] = (byte)((i >> 24) & 0xFF);
        result[1] = (byte)((i >> 16) & 0xFF);
        result[2] = (byte)((i >> 8) & 0xFF);
        result[3] = (byte)(i & 0xFF);
        return result;
    }


    /**
     * byte[]转int
     * @param bytes
     * @return
     * 大端字节序
     */
    public static int bigEndianBytesToInt(byte[] bytes) {
        int value=0;
        for (int i=0; i<4; i++) {
            int shift = (4-1-i)*8;
            value += (bytes[i] & 0x000000FF) << shift;
        }
        return value;
    }


    /**
     * short转byte[]
     *
     * @param s 需要转换的short
     * 大端字节序
     */
    public static byte[] shortToBigEndianBytes(short s) {
        byte[] b = new byte[2];
        b[0] = (byte) (s >> 8);
        b[1] = (byte) (s >> 0);
        return b;
    }

    /**
     * byte[]转short
     *
     * @param b
     * @return
     * 大端字节序
     */
    public static short bigEndianBytesToShort(byte[] b) {
        return (short) (((b[0] << 8) | b[1] & 0xff));
    }


    /**
     * 将64位的long值放到8字节的byte数组
     * @param num
     * @return 返回转换后的byte数组
     * 大端字节序
     */
    public static byte[] longToByteArray(long num) {
        byte[] result = new byte[8];
        result[0] = (byte) (num >>> 56); //取最高8位放到0下标
        result[1] = (byte) (num >>> 48);
        result[2] = (byte) (num >>> 40);
        result[3] = (byte) (num >>> 32);
        result[4] = (byte) (num >>> 24);
        result[5] = (byte) (num >>> 16);
        result[6] = (byte) (num >>> 8);
        result[7] = (byte) (num);  //取最低8位放到7下标
        return result;
    }

    /**
     * 将8字节的byte数组转成一个long值
     * @param byteArray
     * @return 转换后的long型数值
     * 大端字节序
     */
    public static long byteArrayToInt(byte[] byteArray) {
        byte[] a = new byte[8];
        int i = a.length-1, j = byteArray.length-1;
        for (; i>=0; i--, j--) {  //从b的尾部(即int值的低位)开始copy数据
            if (j >= 0)
                a[i] = byteArray[j];
            else
                a[i] = 0;   //如果b.length不足4,则将高位补0
        }
        //注意: 此处和byte数组转换成int的区别在于，下面的转换中要将先将数组中的元素转换成long型再做移位操作，若直接做位移操作将得不到正确结果，因为Java默认操作数字时，若不加声明会将数字作为int型来对待，此处必须注意。
        long v0 = (long) (a[0] & 0xff) << 56; //&0xff将byte值无差异转成int,避免Java自动类型提升后,会保留高位的符号位
        long v1 = (long) (a[1] & 0xff) << 48;
        long v2 = (long) (a[2] & 0xff) << 40;
        long v3 = (long) (a[3] & 0xff) << 32;
        long v4 = (long) (a[4] & 0xff) << 24;
        long v5 = (long) (a[5] & 0xff) << 16;
        long v6 = (long) (a[6] & 0xff) << 8;
        long v7 = (long) (a[7] & 0xff);
        return v0 + v1 + v2 + v3 + v4 + v5 + v6 + v7;
    }

    /**
     * float转换byte数组
     *
     * @param bb
     * @param x
     * @param index
     * 大端字节序
     */
    public static void putFloat(byte[] bb, float x, int index) {
        // byte[] b = new byte[4];
        int l = Float.floatToIntBits(x);
        for (int i=0; i<4; i++) {
            bb[index + i] = new Integer(l).byteValue();
            l = l >> 8;
        }
    }

    /**
     * float转换byte数组
     */
    public static byte[] float2byte(float f) {

        // 把float转换为byte[]
        int fbit = Float.floatToIntBits(f);

        byte[] b = new byte[4];
        for (int i=0; i<4; i++) {
            b[i] = (byte) (fbit >> (24 - i * 8));
        }

        // 翻转数组
        int len = b.length;
        // 建立一个与源数组元素类型相同的数组
        byte[] dest = new byte[len];
        // 为了防止修改源数组，将源数组拷贝一份副本
        System.arraycopy(b, 0, dest, 0, len);
        byte temp;
        // 将顺位第i个与倒数第i个交换
        for (int i = 0; i < len / 2; ++i) {
            temp = dest[i];
            dest[i] = dest[len - i - 1];
            dest[len - i - 1] = temp;
        }

        return dest;

    }

    /**
     * 通过byte数组取得float
     *
     * @param bb
     * @param index
     * @return
     * 大端字节序
     */
    public static float getFloat(byte[] b, int index) {
        int l;
        l = b[index + 0];
        l &= 0xff;
        l |= ((long) b[index + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 3] << 24);
        return Float.intBitsToFloat(l);
    }

    /**
     * double转换byte
     *
     * @param bb
     * @param x
     * @param index
     * 大端字节序
     */
    public static void putDouble(byte[] bb, double x, int index) {
        // byte[] b = new byte[8];
        long l = Double.doubleToLongBits(x);
        for (int i=0; i<4; i++) {
            bb[index + i] = new Long(l).byteValue();
            l = l >> 8;
        }
    }

    /**
     * 通过byte数组取得double
     *
     * @param bb
     * @param index
     * @return
     * 大端字节序
     */
    public static double getDouble(byte[] b, int index) {
        long l;
        l = b[0];
        l &= 0xff;
        l |= ((long) b[1] << 8);
        l &= 0xffff;
        l |= ((long) b[2] << 16);
        l &= 0xffffff;
        l |= ((long) b[3] << 24);
        l &= 0xffffffffl;
        l |= ((long) b[4] << 32);
        l &= 0xffffffffffl;
        l |= ((long) b[5] << 40);
        l &= 0xffffffffffffl;
        l |= ((long) b[6] << 48);
        l &= 0xffffffffffffffl;
        l |= ((long) b[7] << 56);
        return Double.longBitsToDouble(l);
    }


    //=======================================================
    /**
     * short转byte[]
     *
     * @param s 需要转换的short
     * 小端字节序
     */
    public static byte[] shortToLittleEndianBytes(short s) {
        byte[] b = new byte[2];
        b[1] = (byte) (s >> 8);
        b[0] = (byte) (s >> 0);
        return b;
    }

    /**
     * byte[]转short
     *
     * @param b
     * @return
     * 小端字节序
     */
    public static short littleEndianBytesToShort(byte[] b) {
        return (short) (((b[1] << 8) | b[0] & 0xff));
    }



    /**
     * short装byte[]
     *
     * @param b
     * @param s 需要转换的short
     * @param index
     * 小端字节序Little Endian  存放在内存中最低位的数值是来自数据的最右边部分（也就是数据的最低位部分）
     */
    public static void putShortToLittleEndianBytes(byte b[], short s, int index) {
        b[index + 1] = (byte) (s >> 8);
        b[index + 0] = (byte) (s >> 0);
    }

    /**
     * byte[]转short
     *
     * @param b
     * @param index  第几位开始取
     * @return
     * 小端字节序
     */
    public static short getShortFromLittleEndianBytes(byte[] b, int index) {
        return (short) (((b[index + 1] << 8) | b[index + 0] & 0xff));
    }




    public static void main(String[] args) {
        //int value = 32;
        //byte[] bytes = intToBigEndianBytes(value);
        //System.out.println(bytes);
        //System.out.println(bigEndianBytesToInt(bytes));
        //String hexstr = HexUtil.encodeHexStr(bytes);
        //System.out.println(hexstr);
        //String hexstr2 = Integer.toHexString(value);
        //System.out.println(hexstr2);
        //
        //short s = 32;
        //String hexstrs = Integer.toHexString(s);
        //System.out.println(hexstrs);
        //byte[] bytess = shortToBigEndianBytes(s);
        //System.out.println(bytess);
        //System.out.println(bigEndianBytesToShort(bytess));

        float vf = 12.12f;
        byte[] result = new byte[4];
        putFloat(result, vf, 0);
        for(byte b: result) {
            System.out.println(b);
        }

        byte[] result2 = float2byte(vf);
        for(byte b: result2) {
            System.out.println(b);
        }

        System.out.println(getFloat(result, 0));
        System.out.println(getFloat(result2, 0));

    }
}
