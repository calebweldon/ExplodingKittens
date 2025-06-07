package ui;

public class ExplodiaCardView {
	private static final int THREE_EXPLODIA = 3;
	private static final int FOUR_EXPLODIA = 4;

	public void getInfo() {
		// TODO: add locale
		System.out.println("An ancient mysterious card. Try collecting all 5 pieces");
		System.out.println("May have unintended side-effects if played");
	}

	public void actionMessage() {
		// TODO: add locale
		System.out.println("A piece of Explodia has been played! " +
				"Something random will happen!");
	}

	public void drawMessage(int numExplodia) {
		// TODO: add locale
		switch (numExplodia) {
			case 1:
				System.out.println("Another piece of Explodia has been drawn. " +
						"The cards resonate softly.");
				break;
			case 2:
				System.out.println("Another piece of Explodia has been drawn. " +
						"The cards are drawn to each other.");
				break;
			case THREE_EXPLODIA:
				System.out.println("Another piece of Explodia has been drawn. " +
						"The cards begin to glow ominously.");
				break;
			case FOUR_EXPLODIA:
				System.out.print("The final piece of Explodia has been drawn. ");
				System.out.println("A blinding flash erupts" +
						"—the ritual is complete.");
				break;
			default:
				System.out.println("A piece of Explodia has been drawn. " +
						"Nothing happens.");
		}
	}

}
