package hashTable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/** @author: Hongquan Yu
 *  @date: Jan 25, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FindAnagramMappings_760 {
    public int[] anagramMappings(int[] A, int[] B) {
        int n1 = A.length;
        int[] res = new int[n1];
        
        if (A.length != B.length)
            return res;
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < n1; ++i) {
            if (!map.containsKey(B[i]))
                map.put(B[i], new LinkedList<>());
            map.get(B[i]).add(i);
        }
        
        for (int i = 0; i < n1; ++i) {
            res[i] = map.get(A[i]).remove(0);
        }
        
        return res;
    }
    
    public int[] anagramMappings1(int[] A, int[] B) {
        Map<Integer, Integer> D = new HashMap();
        for (int i = 0; i < B.length; ++i)
            D.put(B[i], i);

        int[] ans = new int[A.length];
        int t = 0;
        for (int x: A)
            ans[t++] = D.get(x);
        return ans;
    }
}
