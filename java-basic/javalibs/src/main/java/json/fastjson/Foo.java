package json.fastjson;

/**
 * Created by Defias on 2017/8/17.
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Foo {
    //声明Java中的8种基本变量和String类型变量.
    private String vString = "vStringhehhehe";

    private char vchar = 'x';
    private byte vbyte = 64;
    private short vshort = 128;
    private int vint = 65535;
    private long vlong = 9999999L;
    private float vfloat = 12.1f;
    private double vdouble = 22.203d;
    private boolean vboolean = false;

    //Date和Object类型变量
    private Date vDate = new Date();
    private Object vnull = null;

    //String,int,boolean类型的数组
    private String[] avString = {"aaa", "bbb", "ccc"};
    private int[] avint = {1, 2, 3, 4};
    private boolean[] avboolean = {true, false, true, true};

    //List和Map类型变量
    private List<String> listString = new ArrayList<String>();
    private Map<String, String> map = new HashMap<String, String>();

    //封装的Bar对象
    private Bar bar = new Bar();
    private Bar[] avBar = {new Bar(), new Bar()};
    private List<Bar> listBar = new ArrayList<Bar>();

    //代码块封装数据
    {
        listString.add("listString1");
        listString.add("listString2");
        listString.add("listString3");

        map.put("x", "s11111x");
        map.put("y", "s22222y");
        map.put("z", "s33333z");

        listBar.add(new Bar());
        listBar.add(new Bar());
        listBar.add(new Bar());
    }

    public String getvString() {
        return vString;
    }

    public void setvString(String vString) {
        this.vString = vString;
    }

    public char getVchar() {
        return vchar;
    }

    public void setVchar(char vchar) {
        this.vchar = vchar;
    }

    public byte getVbyte() {
        return vbyte;
    }

    public void setVbyte(byte vbyte) {
        this.vbyte = vbyte;
    }

    public short getVshort() {
        return vshort;
    }

    public void setVshort(short vshort) {
        this.vshort = vshort;
    }

    public int getVint() {
        return vint;
    }

    public void setVint(int vint) {
        this.vint = vint;
    }

    public long getVlong() {
        return vlong;
    }

    public void setVlong(long vlong) {
        this.vlong = vlong;
    }

    public float getVfloat() {
        return vfloat;
    }

    public void setVfloat(float vfloat) {
        this.vfloat = vfloat;
    }

    public double getVdouble() {
        return vdouble;
    }

    public void setVdouble(double vdouble) {
        this.vdouble = vdouble;
    }

    public boolean isVboolean() {
        return vboolean;
    }

    public void setVboolean(boolean vboolean) {
        this.vboolean = vboolean;
    }

    public Date getvDate() {
        return vDate;
    }

    public void setvDate(Date vDate) {
        this.vDate = vDate;
    }

    public Object getVnull() {
        return vnull;
    }

    public void setVnull(Object vnull) {
        this.vnull = vnull;
    }

    public String[] getAvString() {
        return avString;
    }

    public void setAvString(String[] avString) {
        this.avString = avString;
    }

    public int[] getAvint() {
        return avint;
    }

    public void setAvint(int[] avint) {
        this.avint = avint;
    }

    public boolean[] getAvboolean() {
        return avboolean;
    }

    public void setAvboolean(boolean[] avboolean) {
        this.avboolean = avboolean;
    }

    public List<String> getListString() {
        return listString;
    }

    public void setListString(List<String> listString) {
        this.listString = listString;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public Bar[] getAvBar() {
        return avBar;
    }

    public void setAvBar(Bar[] avBar) {
        this.avBar = avBar;
    }

    public List<Bar> getListBar() {
        return listBar;
    }

    public void setListBar(List<Bar> listBar) {
        this.listBar = listBar;
    }
}
