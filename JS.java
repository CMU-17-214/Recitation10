import java.util.HashSet;

public class JS {
	protected static class Solution {
	    public static int numJewelsInStones(String J, String S) {
	        J.toUpperCase();
	        S.toUpperCase();
	        HashSet<Character> Jset = new HashSet<>();
	        for (int i = 0; i < J.length(); i++) {
	            Jset.add(J.charAt(i));
	        }
	        int count = 0;
	        for (int i = 0; i < S.length(); i++) {
	            if (Jset.contains(S.charAt(i))) {
	                count ++;
	            }
	        }
	        return count;
	    }


	    public static int sol2(String J, String S) {
	    	int count = 0;
	    	for (int i = 0; i < S.length() ; i++) {
	    		if(J.indexOf(S.charAt(i)) >= 0) {
	    			count++;
	    		}
	    	}
	    	return count;
	    }
	}

	public static void main(String[] args) {
		String J = "aA";
		String S = "aAAbbbb";
		int res = Solution.numJewelsInStones(J, S);
		System.out.println(res);
		res = Solution.sol2(J, S);
		System.out.println(res);
	}
}