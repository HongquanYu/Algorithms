package substring;

/** 普林斯顿的 KMP 实现，通过构建一个DFA来做 */

public class KMP {
    private final int R;       // the radix
    private int[][] dfa;       // the KMP automoton

    private char[] pattern;    // either the character array for the pattern
    private String pat;        // or the pattern string

    /** Preprocesses the pattern string. */
    
    public KMP(String pat) {
        this.R = 256;
        this.pat = pat;

        // build DFA from pattern
        int m = pat.length();
        dfa = new int[R][m]; 
        dfa[pat.charAt(0)][0] = 1; 	// 初始状态进入下一个状态
        
        for (int x = 0, j = 1; j < m; j++) {	// x 存储
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][x];     // Copy mismatch cases. 
            }
            dfa[pat.charAt(j)][j] = j+1;   // Set match case. 
            x = dfa[pat.charAt(j)][x];     // Update restart state. 
        } 
    } 

    /** Preprocesses the pattern string. */
    
    public KMP(char[] pattern, int R) {
        this.R = R;
        this.pattern = new char[pattern.length];
        for (int j = 0; j < pattern.length; j++)
            this.pattern[j] = pattern[j];

        // build DFA from pattern
        int m = pattern.length;
        dfa = new int[R][m]; 
        dfa[pattern[0]][0] = 1; 
        
        for (int x = 0, j = 1; j < m; j++) {
            for (int c = 0; c < R; c++) 
                dfa[c][j] = dfa[c][x];     // Copy mismatch cases. 
            dfa[pattern[j]][j] = j+1;      // Set match case. 
            x = dfa[pattern[j]][x];        // Update restart state. 
        } 
    } 

    /** Returns the index of the first occurrrence of the pattern string
     * in the text string. */
    
    public int search(String txt) {

        // simulate operation of DFA on text
        int m = pat.length();
        int n = txt.length();
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == m) return i - m;    // found
        return n;                    // not found
    }

    /** Returns the index of the first occurrrence of the pattern string
     * in the text string. */
    
    public int search(char[] text) {

        // simulate operation of DFA on text
        int m = pattern.length;
        int n = text.length;
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++) {
            j = dfa[text[i]][j];
        }
        if (j == m) return i - m;    // found
        return n;                    // not found
    }


    /** Takes a pattern string and an input string as command-line arguments;
     * searches for the pattern string in the text string; and prints
     * the first occurrence of the pattern string in the text string. */
    
    public static void main(String[] args) {
        String pat = "abracadabra";
        String pat1 = "rab";
        String pat2 = "bcara";
        String pat3 = "rabrabracad";
        String pat4 = "abacad";
        String txt = "abacadabrabracabracadabrabrabracad";
        char[] pattern = pat.toCharArray();
        char[] pattern1 = pat1.toCharArray();
        char[] pattern2 = pat2.toCharArray();
        char[] pattern3 = pat3.toCharArray();
        char[] pattern4 = pat4.toCharArray();
        char[] text    = txt.toCharArray();

        KMP kmp1 = new KMP(pat);
        int offset1 = kmp1.search(txt);

        KMP kmp2 = new KMP(pattern, 256);
        int offset2 = kmp2.search(text);
        
        KMP kmp3 = new KMP(pattern1, 256);
        int offset3 = kmp3.search(text);
        
        KMP kmp4 = new KMP(pattern2, 256);
        int offset4 = kmp4.search(text);
        
        KMP kmp5 = new KMP(pattern3, 256);
        int offset5 = kmp5.search(text);
        
        KMP kmp6 = new KMP(pattern4, 256);
        int offset6 = kmp6.search(text);

        // print results
        System.out.println("text:    " + txt);

        System.out.print("pattern: ");
        for (int i = 0; i < offset1; i++)
            System.out.print(" ");
        System.out.println(pat);

        System.out.print("pattern: ");
        for (int i = 0; i < offset2; i++)
            System.out.print(" ");
        System.out.println(pat);
        
        System.out.print("pattern: ");
        for (int i = 0; i < offset3; i++)
        	System.out.print(" ");
        System.out.println(pat1);
        
        System.out.print("pattern: ");
        for (int i = 0; i < offset4; i++)
        	System.out.print(" ");
        System.out.println(pat2);
        
        System.out.print("pattern: ");
        for (int i = 0; i < offset5; i++)
        	System.out.print(" ");
        System.out.println(pat3);
        
        System.out.print("pattern: ");
        for (int i = 0; i < offset6; i++)
        	System.out.print(" ");
        System.out.println(pat4);
    }
}
