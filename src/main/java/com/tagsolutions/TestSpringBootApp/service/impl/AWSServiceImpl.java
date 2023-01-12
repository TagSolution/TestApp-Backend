package com.tagsolutions.TestSpringBootApp.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.tagsolutions.TestSpringBootApp.exception.GenericException;
import com.tagsolutions.TestSpringBootApp.model.response.AWSResponse;
import com.tagsolutions.TestSpringBootApp.service.AWSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

@Service
public class AWSServiceImpl implements AWSService {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${amazonProperties.aws.bucketName}")
    private String bucketName;

    @Override
    public AWSResponse uploadFile(MultipartFile file) {

        File mainFile = new File(file.getOriginalFilename());
        AWSResponse response = new AWSResponse();

        try(FileOutputStream stream = new FileOutputStream(mainFile)) {
            stream.write(file.getBytes());
            String newFileName = System.currentTimeMillis() + "_" + mainFile.getName();
            PutObjectRequest request = new PutObjectRequest(bucketName, newFileName, mainFile).withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(request);
            URL imageUrl = amazonS3.getUrl(bucketName, newFileName);
            response.setImageUrl(imageUrl.toExternalForm());
        } catch (FileNotFoundException e) {
            throw new GenericException(e.getMessage(), HttpStatus.CONFLICT);
        } catch (IOException e) {
            throw new GenericException(e.getMessage(), HttpStatus.CONFLICT);
        }

        return  response;
    }

    @Override
    public AWSResponse getFileByName(String fileName) {
        AWSResponse response = new AWSResponse();
        response.setImageUrl(amazonS3.getUrl(bucketName, fileName).toExternalForm());
        return response;
    }


}
