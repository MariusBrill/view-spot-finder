package view.spot.finder.data.provider;

import org.apache.commons.io.IOUtils;

import javax.annotation.Nonnull;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * This class is used to read data from the file system.
 */
public abstract class FileBasedDataProvider {

    /**
     * Takes a String resembling a file path and attempts to load the file. Returns null if the file cannot be loaded.
     * @param filePath The path of the file to load.
     * @return The contents of the given file or null if there was an error while reading the file.
     */
    public static String load(@Nonnull String filePath) {
        try {
            InputStream fileInputStream = new FileInputStream(filePath);
            return IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.printf("An error occurred while loading data from %s : %s", filePath, e.getMessage());
        }
        return null;
    }
}
