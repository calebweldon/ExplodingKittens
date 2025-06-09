package ui;

import java.util.ResourceBundle;

public class BasicCardView implements CardView {
    private final ResourceBundle labels;

    public BasicCardView() {
        this.labels = ResourceBundle.getBundle("labels", LocaleContext.getLocale());
    }

    public void getInfo() {
        final String basicCardInfo = labels.getString("basicCardInfo");
        System.out.println(basicCardInfo);
    }

    public void actionMessage() {
        final String basicCardActionMessage = labels.getString("basicCardActionMessage");
        System.out.println(basicCardActionMessage);
    }
}
