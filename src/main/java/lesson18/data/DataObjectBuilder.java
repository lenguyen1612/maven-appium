package lesson18.data;

import com.google.gson.Gson;
import jdk.nashorn.internal.ir.CatchNode;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataObjectBuilder {


    public <T> T buildDataObject(String filePath, Class<T> dataType) {
        String absoluteFilePath = System.getProperty("user.dir").concat("/test-data/").concat(filePath);

        try (Reader reader = Files.newBufferedReader(Paths.get(absoluteFilePath));) {
            Gson gson = new Gson();

            return gson.fromJson(reader, dataType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
