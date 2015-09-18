package cf.charleseugeneloubao.gtfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by charl on 9/18/2015.
 */
public class FeedDataReader {

    public static <T extends HashMap<String,String>> LinkedHashSet<T> getFeedObjects(Class<T> tClass, File file) throws FileNotFoundException {
        LinkedHashSet<T> objects = new LinkedHashSet<>();

        Scanner scanner = new Scanner(file);
        String[] fields = scanner.nextLine().split(",");
        while (scanner.hasNextLine()) {
            try {
                T object = tClass.newInstance();

                String[] data = scanner.nextLine().split(",");
                for (int i = 0; i < fields.length; i++) {
                    object.put(fields[i], data[i]);
                }
                objects.add(object);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                break;
            }
        }
        return objects;
    }
}
