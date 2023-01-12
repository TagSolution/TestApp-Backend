package com.tagsolutions.TestSpringBootApp.controller;

import com.tagsolutions.TestSpringBootApp.model.response.AWSResponse;
import com.tagsolutions.TestSpringBootApp.service.AWSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage")
public class AWSBucketController {

    @Autowired
    private AWSService awsService;

    @PostMapping(
            value = "/upload",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<AWSResponse> uploadFile(@RequestPart(value = "file") MultipartFile file) {
        AWSResponse response = awsService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}