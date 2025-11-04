// Q6-W6: A Class that Does Something

import java.util.Arrays;

public class Triangle {
	private int length1, length2, length3;
	public Triangle() {
		length1 = 3;
		length2 = 4;
		length3 = 5;
	}
	public Triangle(int length1, int length2, int length3) {
		this.length1 = length1;
		this.length2 = length2;
		this.length3 = length3;
	}
	public boolean isIsosceles() {
		return (length1 == length2 || length1 == length3 || length2 == length3);
	}
	public boolean isEquilateral() {
		return (length1 == length2 && length1 == length3);
	}
	public boolean isScalene() {
		return (length1 != length2 && length2 != length3 && length1 != length3);
	}
	public boolean isRightTriangle() {
		int[] lengths = new int[3];

		lengths[0] = length1;
		lengths[1] = length2;
		lengths[2] = length3;
		Arrays.sort(lengths);
		return Math.pow(lengths[0],2) + Math.pow(lengths[1],2) == Math.pow(lengths[2],2);
	}
}
