package string;

import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

//This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation

public interface NestedInteger {
//	public NestedInteger();
//	public NestedInteger(int value);
	public boolean isInteger();
	public Integer getInteger();
	public void setInteger(int value);
	public void add(NestedInteger ni);
	public List<NestedInteger> getList();
}
