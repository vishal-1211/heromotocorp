package heromotocorp;

public class ZeroOneSorting {

	public static void main(String[] args) {
		Integer[] zeroOne = { 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0 };

		for (int element = 0; element < zeroOne.length - 1; element++) {
			if (zeroOne[element] > zeroOne[element + 1]) {
				int temp = zeroOne[element];
				zeroOne[element] = zeroOne[element + 1];
				zeroOne[element + 1] = temp;

				element = -1;
			}
		}

		System.out.println("Size of original array : " + zeroOne.length);

		for (Integer element : zeroOne) {
			System.out.println("Element : " + element);
		}
	}

}
