package com.concredito.webapi.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;

import com.concredito.webapi.Models.DocumentoModel;
import com.concredito.webapi.Models.ProspectoModel;
import com.concredito.webapi.Services.DocumentoService;
import com.concredito.webapi.Services.ProspectoService;

@RestController
@CrossOrigin
public class DocumentoController {
    private static final Logger logger = LoggerFactory.getLogger(DocumentoController.class);

    @Autowired
    private DocumentoService fileStorageService;
    @Autowired
    private ProspectoService prospectoService;

    @PostMapping("/uploadFile")
    public DocumentoModel uploadFile(@RequestParam("file") MultipartFile file,
            @RequestParam("prospecto") Long prospecto) {

        String fileName = fileStorageService.storeFile(file, prospecto);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/donwloadFile/")
                .path(fileName)
                .toUriString();

        var responseDocument = new DocumentoModel();
        ProspectoModel newProspecto = new ProspectoModel();

        newProspecto = prospectoService.getById(prospecto).get();

        responseDocument.setNombre(fileName);
        responseDocument.setTamano(file.getSize());
        responseDocument.setUri(fileDownloadUri);
        responseDocument.setProspecto(newProspecto);

        return fileStorageService.create(responseDocument);
    }

    @PostMapping("/uploadMultipleFiles")
    public List<DocumentoModel> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
            @RequestParam("prospecto") Long prospecto) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file, prospecto))
                .collect(Collectors.toList());
    }

    @GetMapping("/donwloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("No se puede determinar el tipo de archivo.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/documento")
    public ArrayList<DocumentoModel> obtenerDocumentos() {
        return fileStorageService.getAll();
    }

    @GetMapping("/documento/{prospectoId}")
    public ArrayList<DocumentoModel> obtenerPorProspecto(@PathVariable("prospectoId") Long prospectoId) {

        return fileStorageService.getById(prospectoId);

    }

}