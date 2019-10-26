
import java.util.Comparator;
import java.util.HashMap;

public class TreeMapComparator implements Comparator<String>{
    HashMap<String,Integer> hashMap;
    public TreeMapComparator(HashMap<String,Integer> hashMap)
    {
        this.hashMap=hashMap;
    }

    public int compare(String s1, String s2)
    {
        return Integer.compare(hashMap.get(s1),hashMap.get(s2));
    }
}
