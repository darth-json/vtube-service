package com.jason.rall.vtube.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author jrall
 */
@Configuration
public class FilesConfig {

    @Value("${application.uploadTempFolder}")
    private String uploadTempFolder;

    @Bean(name = "uploadTempFolder")
    public  String getUploadTempFolder(){
        return uploadTempFolder;
    }
}
