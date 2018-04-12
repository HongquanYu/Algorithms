package Trie;

/** @author: Hongquan Yu
 *  @date: Feb 4, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class TrieNode {
	char val;
	boolean isWord;
	TrieNode[] children = new TrieNode[26];
	
	public TrieNode() {}
	public TrieNode(char val) { this.val = val; }
}
