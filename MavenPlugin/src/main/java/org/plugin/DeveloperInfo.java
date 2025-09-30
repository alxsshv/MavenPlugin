package org.plugin;

import org.apache.maven.model.Developer;

/**
 * @author Alexei Shvariov
 * Декоратор для класса Developer, реализующий метод toString
 * для строкового представления класса developer в формате,
 * удобном для вывода в лог.
 */
public class DeveloperInfo {
    private static final String DEVELOPER_INFO_TEMPLATE = "%s email: %s, url: %s, organization: %s, roles: %s %s";

    private static final String DEFAULT_FIELD_VALUE = "is not specified";

    private final Developer developer;

    public DeveloperInfo(Developer developer) {
        this.developer = developer;
    }

    /** Преобразование класса Developer в строку для вывода в log.
     * Если имя разработчика не указано возвращается пустая строка.
     * Если отсутствует дополнительная информация о разработчике,
     * выводится информация, что данные не указаны.
     * @return строковое представление объекта класса Developer.
     */
    public String toString() {
        if (developer.getName().isEmpty()) {
            return "";
        }
        final String email = developer.getEmail() != null ? developer.getEmail() : DEFAULT_FIELD_VALUE;
        final String url = developer.getUrl() != null ? developer.getUrl() : DEFAULT_FIELD_VALUE;
        final String organisation = developer.getOrganization() != null ? developer.getOrganization() : DEFAULT_FIELD_VALUE;
        final String roles = developer.getRoles().isEmpty() ? "are not specified" : String.join(", ", developer.getRoles());
        return String.format(DEVELOPER_INFO_TEMPLATE, developer.getName(), email, url, organisation, roles, System.lineSeparator());
    }
}
