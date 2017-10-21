package string;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ReadNCharactersGivenRead4_157 {
	public int read(char[] buf, int n) {
		  boolean eof = false;      // end of file flag
		  int total = 0;            // total bytes have read
		  char[] tmp = new char[4]; // temp buffer
		  
		  while (!eof && total < n) {
		    int count = read4(tmp);
		    
		    // check if it's the end of the file
		    eof = count < 4;
		    
		    // get the actual count
		    count = Math.min(count, n - total);
		    
		    // copy from temp buffer to buf
		    for (int i = 0; i < count; i++) 
		      buf[total++] = tmp[i];
		  }
		  
		  return total;
		}
	
	/* The read4 API is defined in the parent class Reader4. */
	private int read4(char[] buf) {
		return 0;
	}
}
