package com.servustech.skeleton.features.files;

import com.servustech.skeleton.utils.filestorage.FileStorage;
import com.servustech.skeleton.utils.filestorage.FileStorageException;
import com.servustech.skeleton.utils.filestorage.FileStorageUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@AllArgsConstructor
@RestController
@RequestMapping("/api/files")
public class FilesController {

    private final FileStorage fileStorage;

    @GetMapping("/download")
    public void downloadFile(@RequestParam("key") String key, @RequestParam("filename") String filename, HttpServletResponse response) throws IOException {
        final HttpHeaders responseHeaders = new HttpHeaders();
        try (InputStream inputStream = fileStorage.retrieve(key)) {
            FileStorageUtils.setResponseContentType(response, responseHeaders, filename);
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
            IOUtils.copy(inputStream, response.getOutputStream());
        }
    }

    @DeleteMapping
    public void delete(@RequestParam("filename") String filename) {
        this.fileStorage.delete(filename);
    }

    @PostMapping("/upload")
    public void uploadFile(@RequestBody MultipartFile file) {
        try {
            fileStorage.store(file, file.getOriginalFilename());
        } catch (IOException e) {
            throw new FileStorageException(e.getLocalizedMessage());
        }
    }
}
