package com.practice.Bajaj.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.practice.Bajaj.DTO.GetResponse;
import com.practice.Bajaj.DTO.Request;
import com.practice.Bajaj.DTO.Response;
import com.practice.Bajaj.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


//import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController {

    @Autowired
    private RequestService bajajService;

    @PostMapping("/bfhl")
    public ResponseEntity<Response> processData(
            @RequestPart("data") String json,
            @RequestPart(value = "file", required = false) MultipartFile file ) {

        ObjectMapper objectMapper = new ObjectMapper();
        List<String> data = null;
        try {

            data = objectMapper.readValue(json, new TypeReference<List<String>>() {});

            Request request = new Request();
            request.setData(data);
            request.setFile(file);

            System.out.println(data);

            Response response = bajajService.processRequest(request);
            return ResponseEntity.ok(response);

        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/bfhl")
    public ResponseEntity<GetResponse> getOperationCode() {
        return ResponseEntity.ok(new GetResponse(1));
    }
}