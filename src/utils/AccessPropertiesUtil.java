package utils;

/**
 * Enum class which contains access info for properties file.
 */
public enum AccessPropertiesUtil {
    WRONG_INPUT("WRONG_INPUT"),
    ASSUMPTION("ASSUMPTION"),
    RESULT("RESULT"),
    PROPERTIES_PATH("resource/config"),
    PATH("PATH");

    String propertyAccess;

    AccessPropertiesUtil(String propertyAccessString) {
        this.propertyAccess = propertyAccessString;
    }

    public String getPropertyAccess() {
        return propertyAccess;
    }
}
