package Collection;

import Collection.generator.Countries;

import java.util.*;

import static Basic.Print.print;

/**
 * Created by Defias on 2020/07.
 * Description:  Collections 实用方法
 */
public class TestCollections {
    static List<String> list = Arrays.asList(
            "one Two three Four five six one".split(" "));

    static Collection<String> data =
            new ArrayList<String>(Countries.names(6));

    public static void main(String[] args) {

        testUtilities();
        testListSortSearch();

    }

    //实用方法
    public static void testUtilities() {
        print(list.toString());
        print("'list' disjoint (Four)?: " +
                Collections.disjoint(list,
                        Collections.singletonList("Four")));
        print("max: " + Collections.max(list));
        print("min: " + Collections.min(list));
        print("max w/ comparator: " + Collections.max(list,
                String.CASE_INSENSITIVE_ORDER));
        print("min w/ comparator: " + Collections.min(list,
                String.CASE_INSENSITIVE_ORDER));
        List<String> sublist =
                Arrays.asList("Four five six".split(" "));
        print("indexOfSubList: " +
                Collections.indexOfSubList(list, sublist));
        print("lastIndexOfSubList: " +
                Collections.lastIndexOfSubList(list, sublist));
        Collections.replaceAll(list, "one", "Yo");
        print("replaceAll: " + list);
        Collections.reverse(list);
        print("reverse: " + list);
        Collections.rotate(list, 3);
        print("rotate: " + list);
        List<String> source =
                Arrays.asList("in the matrix".split(" "));
        Collections.copy(list, source);
        print("copy: " + list);
        Collections.swap(list, 0, list.size() - 1);
        print("swap: " + list);
        Collections.shuffle(list, new Random(47));
        print("shuffled: " + list);
        Collections.fill(list, "pop");
        print("fill: " + list);
        print("frequency of 'pop': " +
                Collections.frequency(list, "pop"));
        List<String> dups = Collections.nCopies(3, "snap");
        print("dups: " + dups);
        print("'list' disjoint 'dups'?: " +
                Collections.disjoint(list, dups));
        // Getting an old-style Enumeration:
        Enumeration<String> e = Collections.enumeration(dups);
        Vector<String> v = new Vector<String>();
        while(e.hasMoreElements())
            v.addElement(e.nextElement());
        // Converting an old-style Vector
        // to a List via an Enumeration:
        ArrayList<String> arrayList =
                Collections.list(v.elements());
        print("arrayList: " + arrayList);
    }



    //List的排序与查询
    public static void testListSortSearch() {
        List<String> list =
                new ArrayList<String>(TestCollections.list);
        list.addAll(TestCollections.list);
        print(list.toString());
        Collections.shuffle(list, new Random(47));
        print("Shuffled: " + list);
        // Use a ListIterator to trim off the last elements:
        ListIterator<String> it = list.listIterator(10);
        while(it.hasNext()) {
            it.next();
            it.remove();
        }
        print("Trimmed: " + list);
        Collections.sort(list);
        print("Sorted: " + list);
        String key = list.get(7);
        int index = Collections.binarySearch(list, key);
        print("Location of " + key + " is " + index +
                ", list.get(" + index + ") = " + list.get(index));
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        print("Case-insensitive sorted: " + list);
        key = list.get(7);
        index = Collections.binarySearch(list, key,
                String.CASE_INSENSITIVE_ORDER);
        print("Location of " + key + " is " + index +
                ", list.get(" + index + ") = " + list.get(index));
    }

    //只读的Collection和Map
    public static void testReadOnly() {
        Collection<String> c =
                Collections.unmodifiableCollection(
                        new ArrayList<String>(data));
        print(c.toString()); // Reading is OK
        //! c.add("one"); // Can't change it

        List<String> a = Collections.unmodifiableList(
                new ArrayList<String>(data));
        ListIterator<String> lit = a.listIterator();
        print(lit.next()); // Reading is OK
        //! lit.add("one"); // Can't change it

        Set<String> s = Collections.unmodifiableSet(
                new HashSet<String>(data));
        print(s.toString()); // Reading is OK
        //! s.add("one"); // Can't change it

        // For a SortedSet:
        Set<String> ss = Collections.unmodifiableSortedSet(
                new TreeSet<String>(data));

        Map<String,String> m = Collections.unmodifiableMap(
                new HashMap<String,String>(Countries.capitals(6)));
        print(m.toString()); // Reading is OK
        //! m.put("Ralph", "Howdy!");

        // For a SortedMap:
        Map<String,String> sm =
                Collections.unmodifiableSortedMap(
                        new TreeMap<String,String>(Countries.capitals(6)));
    }


    //Collection和Map的同步控制
    public static void testSynchronization() {
        Collection<String> c =
                Collections.synchronizedCollection(
                        new ArrayList<String>());
        List<String> list = Collections.synchronizedList(
                new ArrayList<String>());
        Set<String> s = Collections.synchronizedSet(
                new HashSet<String>());
        Set<String> ss = Collections.synchronizedSortedSet(
                new TreeSet<String>());
        Map<String,String> m = Collections.synchronizedMap(
                new HashMap<String,String>());
        Map<String,String> sm =
                Collections.synchronizedSortedMap(
                        new TreeMap<String,String>());
    }

}
