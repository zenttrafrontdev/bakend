package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.domain.service.FileService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import com.amarilo.msobligacionesfinancieras.infraestructure.FileBusinessRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private final FileBusinessRepository fileBusinessRepository;

    public FileServiceImpl(FileBusinessRepository fileBusinessRepository) {
        this.fileBusinessRepository = fileBusinessRepository;
    }

    @Override
    public ByteArrayResource downloadFile(Integer fileBusinessId) throws IOException {
        var fileBusinessEntity = fileBusinessRepository.findById(fileBusinessId)
                .orElseThrow(() -> new BusinessException("El archivo que desea descargar no existe"));
        Path path;
        try {
            File file = new File(fileBusinessEntity.getName());
            path = Paths.get(file.getAbsolutePath());
        } catch (InvalidPathException ex) {
            log.error(ex.getMessage());
            throw new BusinessException("No se pudo descargar el archivo seleccionado");
        }

        return new ByteArrayResource(Files.readAllBytes(path));
    }
}
