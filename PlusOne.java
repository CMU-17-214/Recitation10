public class PlusOne {
	protected static class Solution {
	    public static int[] plusOne(int[] digits) {
	        int carry = 0;
	        int i = digits.length - 1;
	        digits[i] += 1;
	        while ((i == digits.length - 1 || carry == 1) && i >= 0) {
	            digits[i] += carry;
	            carry = digits[i] / 10;
	            digits[i] = digits[i] % 10;
	            i--;
	        }
	        int[] res;
	        if (i == -1 && carry == 1) {
	            res = new int[digits.length + 1];
	            res[0] = 1;
	            for (i = 0; i < digits.length; i++) {
	                res[i + 1] = digits[i];
	            }
	        } else {
	            res = digits;
	        }
	        return res;
    	}
    }


    protected static void printNumeber (int[] num) {
    	for (int i : num) {
    		System.out.print(i);
    	}
    	System.out.println("");
    }


    public static void main (String[] args) {
    	int[] toSolve = new int[]{1,3,9};
    	int[] res = Solution.plusOne(toSolve);
    	printNumeber(res);
    }
}