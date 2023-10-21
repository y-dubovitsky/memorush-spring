package ru.dubovitsky.memorush.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

//    https://stackoverflow.com/questions/57466273/filenotfoundexception-in-spring-boot-jar-but-the-file-is-present
//    FileNotFoundException in spring boot jar but the file is present
@Slf4j
public class ResourcesUtils {

    public static File getResourceFile(String filepath) {
        try {
            InputStream stream = ResourcesUtils.class.getClassLoader().getResourceAsStream(filepath);
            File file = File.createTempFile("tempfile", ".json");
            FileUtils.copyToFile(stream, file);
            return file;
        } catch (IOException e) {
            throw new RuntimeException("Can not read resource file with error: " , e);
        }
    }

}
