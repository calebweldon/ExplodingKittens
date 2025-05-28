package ui;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ImplodingFaceDownCardView implements CardView {
	private final Scanner scanner;

	public ImplodingFaceDownCardView() {
		this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
	}

	public void getInfo() {
		// TODO: add locale
		System.out.println("Will blow your head smoove off");
	}

	public void actionMessage() {
		// TODO: add locale
		System.out.println("Tremble! The Imploding Kitten has been drawn!");
	}

	public int getIndex(int size) {
		// TODO: add locale
		while (true) {
			System.out.printf("Choose and index between 0 and %d: ", size);
			try {
				return scanner.nextInt();
			} catch (Exception E) {
				System.out.println("Invalid index. Try again.");
			}
		}
	}
}
