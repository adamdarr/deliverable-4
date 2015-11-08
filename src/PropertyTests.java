import java.util.Random;
import java.util.Arrays;

import java.lang.Integer;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/*
 * PropertyTests - Tests the following properties of sorted arrays ...
 * 1. Output array is same size as passed-in array
 * 2. Every element in the input array is in the output array
 * 3. Values in output array are always increasing or equal
 */
public class PropertyTests {
	
	private int[][] arrays = new int[100][];
	
	/*
	 * Generate random arrays and store them in the 
	 * private arrays variable before running tests
	 */
	@Before
	public void setUpArrays() {
		Random random = new Random();

		for(int i = 0; i < arrays.length; i++) {
			// allocate array to be anywhere from 0 to 99
			int[] tempArray = new int[random.nextInt(100)];

			for(int j = 0; j < tempArray.length; j++) {
				// add integers to the array that are from 0 to the 2^31 - 1
				tempArray[j] = random.nextInt(Integer.MAX_VALUE);
			}
			
			arrays[i] = tempArray;
		}
	}
	
	/*
	 * Ensures that arrays stay the same length 
	 * before and after they are sorted.
	 */
	@Test
	public void testEqualArrayLengths() {
		boolean equalLengths = true;
		
		for(int i = 0; i < arrays.length; i++) {
			int originalLength = arrays[i].length;
			
			Arrays.sort(arrays[i]);
			
			int sortedLength = arrays[i].length;
			
			if(originalLength != sortedLength) {
				equalLengths = false;
				break;
			}
		}
		
		assertTrue(equalLengths);
	}
	
	/*
	 * Ensures that arrays contain all the same elements
	 * before and after they are sorted.
	 */
	@Test
	public void testSameArrayElements() {
		boolean sameElements = true;
		
		for(int i = 0; i < arrays.length; i++) {
			int[] originalArray = Arrays.copyOf(arrays[i], arrays[i].length);
			
			Arrays.sort(arrays[i]);
			
			for(int j = 0; j < originalArray.length; j++) {
				// search sorted array for original values
				if(Arrays.binarySearch(arrays[i], originalArray[j]) < 0) {
					sameElements = false;
					break;
				}
			}
			
			if(!sameElements) {
				break;
			}
		}
		
		assertTrue(sameElements);
	}
	
	/*
	 * Ensures that values in sorted array are always 
	 * increasing or the equal
	 */
	@Test
	public void testArrayIncreasingOrEqual() {
		boolean increasingOrEqual = true;
		
		for(int i = 0; i < arrays.length; i++) {
			Arrays.sort(arrays[i]);
			
			for(int j = 0; j < arrays[i].length - 1; j++) {
				if(arrays[i][j] > arrays[i][j+1]) {
					increasingOrEqual = false;
					break;
				}
			}
			
			if(!increasingOrEqual) {
				break;
			}
		}
		
		assertTrue(increasingOrEqual);
	}
		
}