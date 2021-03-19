package by.unlimint.service;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class PathParser {

    public static String getFileName(String path) {
        String[] pathArray = path.split("/");
        return pathArray[pathArray.length - 1];
    }

    public String getExtension(String nameClass) {
        String[] name = nameClass.split("[.]");
        return name[name.length - 1];
    }

    public String getTheFileExtensionFromThePath(File path) {
        String extension = getExtension(getFileName(path.toString()));
        return extension;
    }
}
