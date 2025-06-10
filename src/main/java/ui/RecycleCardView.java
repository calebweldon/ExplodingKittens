package ui;

import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class RecycleCardView implements CardView {
	private final ResourceBundle labels;

	public RecycleCardView() {
		this.labels = ResourceBundle.getBundle("labels", LocaleContext.getLocale());
	}

	public void getInfo() {
		final String recycleInfo = labels.getString("recycleInfo");
		System.out.println(recycleInfo);
	}

	public void actionMessage() {
		final String recycleActionMessage = labels.getString("recycleActionMessage");
		System.out.println(recycleActionMessage);
	}
}
