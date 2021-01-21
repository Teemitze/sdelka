package app.sdelka.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
public class UploadFileService {
    @SneakyThrows
    private void save(MultipartFile file, UUID uuid) {
        if (!file.isEmpty()) {
            BufferedOutputStream stream =
                    new BufferedOutputStream(
                            new FileOutputStream(
                                    new File("src/main/resources/static/" + uuid, UUID.randomUUID().toString())));
            stream.write(file.getBytes());
            stream.close();
        }
    }

    public void saveFiles(MultipartFile[] files, UUID uuid) {
        for (MultipartFile file : files) {
            save(file, uuid);
        }
    }
}
