package com.concredito.webapi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

// import com.concredito.webapi.exceptions.FileStorageException;
// import com.concredito.webapi.exceptions.MyFileNotFoundException;
import com.concredito.webapi.Models.DocumentoModel;
import com.concredito.webapi.Properties.FileStorageProperties;
import com.concredito.webapi.Repositories.DocumentoRepository;

@Service
public class DocumentoService {

    private final Path ubicacion;
    @Autowired
    DocumentoRepository documentoRepository;

    @Autowired
    public DocumentoService(FileStorageProperties fileStorageProperties) {
        this.ubicacion = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.ubicacion);
        } catch (Exception ex) {
            throw new RuntimeException("No se ha podido crear el documento", ex);
            // throw new FileStorageException("No se ha podido crear el directorio para guardar los documentos.", ex);
        }
    }

    public String storeFile(MultipartFile file, Long prospecto) {
        // Normalize file name
        String fileName = prospecto+"_"+StringUtils.cleanPath(file.getOriginalFilename());

        Path saveTO = Paths.get(this.ubicacion.resolve(fileName).toString());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
            throw new RuntimeException("No se ha podido crear el documento");
            // throw new FileStorageException("Se ha detectado un caracter invalido en el archivo " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Files.copy(file.getInputStream(), saveTO);

            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("No se ha podido crear el documento", ex);
            // throw new FileStorageException("No se ha podido guardar el archivo,  " + fileName + ".", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.ubicacion.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
            throw new RuntimeException("No se ha podido crear el documento");
            // throw new MyFileNotFoundException("No se ha encontrado el archivo " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("No se ha podido crear el documento", ex);
            // throw new MyFileNotFoundException("No se ha encontrado el archivo " + fileName, ex);
        }
    }

    public DocumentoModel create(DocumentoModel entity) {
        return documentoRepository.save(entity);
    }

    public ArrayList<DocumentoModel> getAll() {
        return (ArrayList<DocumentoModel>) documentoRepository.findAll();
    }

    public ArrayList<DocumentoModel> getById(Long prospecto_Id){
        return (ArrayList<DocumentoModel>) documentoRepository.findDocumentoModelByProspecto_Id(prospecto_Id);
    }
}