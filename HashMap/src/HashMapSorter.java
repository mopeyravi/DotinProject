import java.util.*;

public class HashMapSorter {
    public static void main(String[] Args) {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

        hashMap.put("Apple",4);
        hashMap.put("Orange",10);
        hashMap.put("banana",7);
        hashMap.put("PineApple",3);
        hashMap.put("waterMelon",8);


        TreeMapComparator treeMapComparator=new TreeMapComparator(hashMap);
        TreeMap<String,Integer> sortedMap=new TreeMap<String, Integer>(treeMapComparator);
        sortedMap.putAll(hashMap);
        System.out.print(sortedMap);
    }


}
