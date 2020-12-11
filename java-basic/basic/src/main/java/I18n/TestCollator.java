package I18n;

import java.text.CollationKey;
import java.text.Collator;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * 排序
 * Collator
 *
 * 排序强度
 * 可以设置Collator的排序强度strength属性来确定比较中认为显著的差异级别。提供了四种strength：
 * PRIMARY、SECONDARY、TERTIARY 和 IDENTICAL
 *
 * 分解：即一个字符对应多个Unicode编码；如A（头顶一个圆圈）可以是Unicode字符U+00C5， 或者表示为普通的A（U+0065）后跟一个圆圈；
 * Unicode标准对字符串定义了4种范化形式：D、KD、C、KC
 * 可以选择排序器所使用的范化程度：
 * Collator.NO_DECOMPOSITION （不分解）： 表示不对字符串做任何范化，这时选项处理速度较快， 但是对于以多种形式表示字符的文本就显得不适用了
 * Collator.CANONICAL_DECOMPOSITION（规范分解）： 默认值 使用范化形式D， 这对于包含重音但不包含连字的文本是非常有用的形式
 * Collator.FULL_DECOMPOSITION（完全分解）： 最后是使用范化形式KD的完全分解
 *
 * CollationKey排序键
 * 让排序器去多次分解一个字符串是很浪费时间的，getCollationKey方法返回一个CollationKey对象，可以用它来进行更深入的，更快速的比较操作
 *
 * 范化处理
 * 有可能在你不需要进行排序时，也希望将字符串转换为它们的范化形式。java.text.Normalizer类实现了对范化的处理
 *
 */

public class TestCollator {
    public static void main(String[] args) {

        //getInstance方法获取当前默认语言环境的Collator
        Collator collator = Collator.getInstance(Locale.getDefault());

        Locale[] locales;
        //getAvailableLocales返回一个所有受支持语言环境的数组
        locales = (Locale[]) Collator.getAvailableLocales().clone();

        //排序
        Arrays.sort(locales, new Comparator<Locale>() {
            public int compare(Locale l1, Locale l2) {
                return collator.compare(l1.getDisplayName(), l2.getDisplayName());
            }
        });

        //迭代输出
        for (Locale loc : locales)
            System.out.println(loc.getDisplayName());

        collator.setStrength(Collator.PRIMARY);
        System.out.println("a equals b -> " + (collator.compare("a", "b")==0 ? "true":"false"));
        System.out.println("a equals à -> " + (collator.compare("a", "à")==0 ? "true":"false"));
        System.out.println("A equals a -> " + (collator.compare("a", "A")==0 ? "true":"false"));
        System.out.println();

        collator.setStrength(Collator.TERTIARY);
        System.out.println("a equals b -> " + (collator.compare("a", "b")==0 ? "true":"false"));
        System.out.println("a equals à -> " + (collator.compare("a", "à")==0 ? "true":"false"));
        System.out.println("A equals a -> " + (collator.compare("a", "A")==0 ? "true":"false"));
        System.out.println();

        //为排序器设置分解模式
        System.out.println("\u00E1");
        collator.setDecomposition(Collator.NO_DECOMPOSITION); // 设置为不分解模式
        System.out.println(collator.compare("\u00E1", "a"));

        collator.setDecomposition(Collator.FULL_DECOMPOSITION); // 设置为完全分解模式
        System.out.println(collator.compare("\u00E1", "a"));


        String a = "A";
        String b = "a";
        CollationKey key = collator.getCollationKey(a); //排序键
        if(key.compareTo(collator.getCollationKey(b)) == 0) // fast comparison
            System.out.println("true");
        else
            System.out.println("false");

        //范化处理
        String name = "augs";
        String normalized = Normalizer.normalize(name, Normalizer.Form.NFD); // use normalization from D
        System.out.println(normalized);

    }
}
