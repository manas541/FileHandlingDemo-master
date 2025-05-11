package com.practice.Bajaj.DTO;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class Request {
    private List<String> data;
    private MultipartFile file;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}