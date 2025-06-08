package ui;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ImplodingFaceDownCardView implements CardView {
	private final Scanner scanner;
	private final ResourceBundle labels;

	public ImplodingFaceDownCardView() {
		this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
		this.labels = ResourceBundle.getBundle("labels", LocaleContext.getLocale());
	}

	public void getInfo() {
		final String implodingInfo = labels.getString("implodingInfo");
		System.out.println(implodingInfo);
	}

	public void actionMessage() {
		final String implodingFaceDownActionMessage =
				labels.getString("implodingFaceDownActionMessage");
		System.out.println(implodingFaceDownActionMessage);
	}

	public int getIndex(int size) {
		final String implodingFaceDownIndexSelection =
				labels.getString("implodingFaceDownIndexSelection");
		String indexPrompt = MessageFormat.format(implodingFaceDownIndexSelection, size);

		final String implodingFaceDownInvalidIndex =
				labels.getString("implodingFaceDownInvalidIndex");

		while (true) {
			System.out.println(indexPrompt);
			try {
				return scanner.nextInt();
			} catch (Exception E) {
				System.out.println(implodingFaceDownInvalidIndex);
			}
		}
	}
}
