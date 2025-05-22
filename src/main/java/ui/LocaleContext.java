package ui;

import java.util.Locale;

public class LocaleContext {
	private static Locale currentLocale;

	public static void setLocale(Locale locale) {
		currentLocale = locale;
	}

	public static Locale getLocale() {
		return currentLocale;
	}
}
