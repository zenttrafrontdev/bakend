package com.amarilo.msobligacionesfinancieras.domain.service;

import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;

public interface FileService {
    ByteArrayResource downloadFile(Integer fileBusinessId) throws IOException;
}
