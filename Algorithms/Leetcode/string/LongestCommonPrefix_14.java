package string;

public class LongestCommonPrefix_14 {
    public String longestCommonPrefix(String[] strs) {
    	/*
    	 * Basic idea is that it could 
    	 */
    	
        if(strs == null || strs.length == 0)    return "";
        String pre = strs[0];
        int i = 1;
        while(i < strs.length){ 
            while(strs[i].indexOf(pre) != 0)    
                pre = pre.substring(0,pre.length()-1);
            i++;
        }
        return pre;
    }
}
