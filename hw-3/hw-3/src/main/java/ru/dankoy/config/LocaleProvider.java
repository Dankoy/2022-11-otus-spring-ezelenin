package ru.dankoy.config;

import java.util.Locale;

/**
 * @author turtality
 * <p>
 * Provides only locale from application settings
 */
public interface LocaleProvider {

  Locale getLocale();

}
