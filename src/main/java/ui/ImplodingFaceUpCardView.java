package ui;

import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ImplodingFaceUpCardView implements CardView {
	private final ResourceBundle labels;

	public ImplodingFaceUpCardView() {
		this.labels = ResourceBundle.getBundle("labels", LocaleContext.getLocale());
	}

	public void getInfo() {
		final String implodingInfo = labels.getString("implodingInfo");
		System.out.println(implodingInfo);
	}

	public void actionMessage() {
		final String implodingFaceUpActionMessage =
				labels.getString("implodingFaceUpActionMessage");
		System.out.println(implodingFaceUpActionMessage);
	}
}
