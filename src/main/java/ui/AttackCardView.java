package ui;

import domain.CardController;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class AttackCardView implements CardView {
	private final Scanner scanner;

	public AttackCardView() {
		this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
	}

	public void getInfo() {
		// TODO: add locale
		System.out.println("End your turn(s) without drawing and force the " +
				"next player to take two turns in a row.");
	}

	public void actionMessage() {
		// TODO: add locale
		System.out.println("Attack Card played!");
	}
}
