package libs;

/**
 * Provides useful methods to center a String  
 */
public class StringUtils {

    /**
     * Returns a {@code String} centered and separated by a space
     * @param s
     * @param size
     * @return
     */
    public static String center(String s, int size) {
        return center(s, size, ' ');
    }

    /**
     * Returns a {@code String} centered and separated by the provided pad
     * @param s
     * @param size
     * @param pad
     * @return
     */
    public static String center(String s, int size, char pad) {
        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) / 2; i++) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }
}
