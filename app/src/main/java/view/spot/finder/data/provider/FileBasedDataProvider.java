package view.spot.finder.data.provider;

import org.apache.commons.io.IOUtils;

import javax.annotation.Nonnull;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public abstract class FileBasedDataProvider {

    public static String loadJSONObjectFromFile(@Nonnull String filePath) {
        try {
            return loadJsonFromFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String loadJsonFromFile(@Nonnull String filePath) throws IOException {
        InputStream fileInputStream = new FileInputStream(filePath);
        return IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
    }
}