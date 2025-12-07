package org.basr.pinpoint.helper;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//https://stackoverflow.com/questions/54518045/how-to-mock-filesystem-function
@Component
public class FileStorage {
    public void writeFile(Path path, byte[] bytes) throws IOException {
        Files.createDirectories(path.getParent());
        Files.write(path, bytes);
    }
}
