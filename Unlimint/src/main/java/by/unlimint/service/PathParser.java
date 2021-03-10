package by.unlimint.service;

import org.springframework.stereotype.Component;

@Component
public class PathParser {

//нужно из "src/main/resources/orders.jsonl" получить orders.jsonl
    public static String getFileName(String path) {
        String[] pathArray = path.split("/");
        return pathArray[pathArray.length - 1];
    }
//приходит String.class надо получить String
    public String getNameClass(String nameClass) {
        String[] name = nameClass.split("[.]");
        return name[0];
    }
//приходит orders.jsonl надо получить jsonl
    public String getExtension(String nameClass) {
        String[] name = nameClass.split("[.]");
        return name[name.length - 1];
    }
//приходит "src/main/resources/orders.jsonl" надо получить jsonl
    public String getTheFileExtensionFromThePath(String path) {
        String extension = getExtension(getFileName(path));
        return extension;
    }
}
