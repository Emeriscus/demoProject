package catalog;

import java.util.ArrayList;
import java.util.List;

public class StringListConverters {

    public static String ListToString(List<String> listToTransfer) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String actual : listToTransfer) {
            if (first) {
                first = false;
            } else {
                sb.append(", ");
            }
            sb.append(actual);
        }
        return sb.toString();
    }

    public static List<String> StringToList(String stringToTransfer) {
        String[] parts = stringToTransfer.split(", ");
        List<String> result = new ArrayList<>();
        for (int i = 0; i < parts.length; i++) {
            result.add(parts[i]);
        }
        return result;
    }
}
