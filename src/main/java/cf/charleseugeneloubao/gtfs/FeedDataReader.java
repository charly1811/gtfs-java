package cf.charleseugeneloubao.gtfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FeedDataReader {

    /**
     * Read the data in the specified file and store the values in an instance of the specified class
     * @param tClass Must implement {@link HashMap}
     * @param file The file to read
     * @throws FileNotFoundException
     */
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
