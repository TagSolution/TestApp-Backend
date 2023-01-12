package com.tagsolutions.TestSpringBootApp.service;

import com.tagsolutions.TestSpringBootApp.model.response.AWSResponse;
import org.springframework.web.multipart.MultipartFile;

public interface AWSService {

    AWSResponse uploadFile(MultipartFile file);

}
