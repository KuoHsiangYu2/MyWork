package lin.note.book.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTool {

    private static Properties databaseProperties = new Properties();

    static {
        // static block只會執行一次。在這個類別第一次被呼叫時執行並載入相關資料。.
        FileInputStream fileInputStream = null;
        String propertiesPath = PropertiesTool
                .class
                .getClassLoader()
                .getResource("./database.properties")
                .getPath();

        try {
            System.out.println("propertiesPath = [" + propertiesPath + "]");
            fileInputStream = new FileInputStream(propertiesPath);
            System.out.println("fileInputStream = " + fileInputStream);
            databaseProperties.load(fileInputStream);
            System.out.println("load database properties successful");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                    fileInputStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public PropertiesTool() {
        super();
    }

    public static Properties getDatabaseProperties() {
        return databaseProperties;
    }

}
