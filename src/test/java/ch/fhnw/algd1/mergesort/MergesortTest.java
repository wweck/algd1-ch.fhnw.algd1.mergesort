package ch.fhnw.algd1.mergesort;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class MergesortTest {
	private static final class MyInteger implements Comparable<MyInteger> {
		private final int val;

		private MyInteger(int val) {
			this.val = val;
		}

		@Override
		public int compareTo(MyInteger other) {
			return val - other.val;
		}

		@Override
		public boolean equals(Object other) {
			return val == ((MyInteger)other).val;
		}
	}

	private Mergesort sort;

	@Before
	public void init() {
		sort = new Mergesort();
	}

	@Test
	public void testEmptyArray() {
		int[] numbers = new int[] {};
		sortAndCheck(transform(numbers));
	}

	private MyInteger[] transform(int[] numbers) {
		MyInteger[] arr = new MyInteger[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			arr[i] = new MyInteger(numbers[i]);
		}
		return arr;
	}

	@Test
	public void testArrayWithOneElement() {
		int[] numbers = new int[] { 2 };
		sortAndCheck(transform(numbers));
	}

	@Test
	public void testSameNumbers() {
		int[] numbers = new int[] { 2, 2, 2, 2, 2 };
		sortAndCheck(transform(numbers));
	}

	@Test
	public void testAlreadySorted() {
		int[] numbers = new int[] { 1, 2, 3, 4, 5 };
		sortAndCheck(transform(numbers));
	}

	@Test
	public void testRandomOrder() {
		int[] numbers = new int[] { 34, 64, 12, 6, 8, 345, 54 };
		sortAndCheck(transform(numbers));
	}

	@Test
	public void testTwoSections() {
		int[] numbers = new int[] { 5, 6, 7, 8, 1, 2, 3, 4 };
		sortAndCheck(transform(numbers));
	}

	@Test
	public void testDescendingOrder() {
		int[] numbers = new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
		sortAndCheck(transform(numbers));
	}

	@Test
	public void testStrings() {
		String[] strings = new String[] { "z", "b", "x", "z", "b", "x", "a" };
		sortAndCheck(strings);
	}

	@Test
	public void testStability() {
		String one = new String("Z"), two = new String("Z"), three = new String("Z"), four = new String("Z"),
				five = new String("Z"), six = new String("Z"), seven = new String("Z"), eight = new String("Z"),
				nine = new String("Z"), ten = new String("Z");
		String[] original = new String[] { one, two, three, four, five, six, seven, eight, nine, ten };
		String[] strings = new String[] { one, two, three, four, five, six, seven, eight, nine, ten };
		sortAndCheck(strings);
		for (int i = 0; i < original.length; i++) {
			assertTrue(strings[i] == original[i]);
		}
	}

	@Test
	public void testStability2() {
		String one = new String("A"), two = new String("A"), three = new String("A"), four = new String("B"),
				five = new String("B"), six = new String("B"), seven = new String("Z"), eight = new String("Z"),
				nine = new String("Z"), ten = new String("Z");
		String[] original = new String[] { one, two, three, four, five, six, seven, eight, nine, ten };
		String[] strings = new String[] { one, four, two, three, seven, eight, five, six, nine, ten };
		sortAndCheck(strings);
		for (int i = 0; i < original.length; i++) {
			assertTrue(strings[i] == original[i]);
		}
	}

	private <T extends Comparable<T>> void sortAndCheck(T[] numbers) {
		T[] original = Arrays.copyOf(numbers, numbers.length);
		sort.mergesort(numbers);
		assertEquals("Number of elements differs from original", original.length, numbers.length);
		assertTrue("Elements are not in ascending order", isAscending(numbers));
		Arrays.sort(original);
		assertTrue("Sorted and original elements are different", Arrays.equals(original, numbers));
	}

	private <T extends Comparable<T>> boolean isAscending(T[] a) {
		if (a.length == 0) return true;
		int i = 1;
		while (i < a.length && a[i - 1].compareTo(a[i]) <= 0) {
			i++;
		}
		return i == a.length;
	}
}
