package com.juanje.ecommerce.backend.application;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadFileService {
    private final String FOLDER = "src//main//resources//static//images//";
    private final String IMG_DEFAULT = "default.jpg";
    private final String URL = "http://localhost:8085/images/";

    public String upload (MultipartFile multipartFile) throws IOException {
        if (multipartFile!=null){ //esto es cuando el usuario selecciona una imagen
            byte [] bytes = multipartFile.getBytes();
            Path path = Paths.get(FOLDER+multipartFile.getOriginalFilename());
            Files.write(path, bytes);
            return URL+multipartFile.getOriginalFilename();
        }
        return  URL+IMG_DEFAULT; //si el usuario no selecciona ninguna imagen lo que hago es devolver la default
    }
    public void delete(String nameFile){
        File file = new File(FOLDER+nameFile);
        file.delete();
    }

}
