/**
 * Created by John Donnelly on 9/1/2015.
 */
import java.util.*;

public class Iteratorversion <T extends Comparable<? super T>> {

    public  Iterator<T> literator1;
    public  Iterator<T> literator2;

    public  StringBuilder st = new StringBuilder();

    public  List<T> longestPrefix(List<T> a, List<T> b, Comparator<? super T> cmp){
        literator1 = a.iterator();
        literator2 = b.iterator();

        int counter = 0;
        T a1 = literator1.next();
        T b1 = literator2.next();
        List ret = new ArrayList<>();
        while (cmp.compare(a1, b1) == 0){

            st.append(a1);
            ret.add(a1);
            a1 = literator1.next();
            b1 = literator2.next();

        }

            return ret;
        }


    public static void main(String[] args) {
        List a = new ArrayList<>();
        List b = new ArrayList<>();

        a.add("s");
        a.add("a");
        a.add("d");
        a.add("e");

        b.add("s");
        b.add("a");
        b.add("i");
        b.add("d");
        b.add("s");

        Iteratorversion run = new Iteratorversion();
        List testlist = run.longestPrefix(a, b, new Comparator<String>(){
                       @Override
                        public int compare(String o1, String o2)
                        {
                               return o2.compareTo(o1);
                            }
                    });

        for (int i = 0; i < testlist.size(); i++) {
            System.out.println(testlist.get(i));
        }
    }
    }

