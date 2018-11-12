package Util;

public class StringUtil {
    public static String checkValue(String value, int maxLength, String emptyValue)
    {
        if (value == null || value.isEmpty())
            return emptyValue;

        if (maxLength > 0)
        {
            if (value.length() > maxLength)
                return (value.substring(0, maxLength) + "...");
        }

        return value;
    }
}
