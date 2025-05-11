package com.practice.Bajaj.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.practice.Bajaj.DTO.Request;
import com.practice.Bajaj.DTO.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class RequestService {

    public Response processRequest(Request request) {
         Response response = new  Response();

        try {
            response.setUser_id("john_doe_1709199");
            response.setEmail("john@xyz.com");
            response.setRoll_number("ABCD123");

            List<String> data = request.getData();
            List<String> numbers = new ArrayList<>();
            List<String> alphabets = new ArrayList<>();
            List<String> lowercaseAlphabets = new ArrayList<>();
            boolean primeFound = false;

            for (String item : data) {
                if (item.matches("\\d+")) {
                    numbers.add(item);
                    if (isPrime(Integer.parseInt(item))) {
                        primeFound = true;
                    }
                } else if (item.matches("[a-zA-Z]")) {
                    alphabets.add(item);
                    if (item.matches("[a-z]")) {
                        lowercaseAlphabets.add(item);
                    }
                }
            }

            response.setNumbers(numbers);
            response.setAlphabets(alphabets);

            List<String> highestLowercase = new ArrayList<>();
            if (!lowercaseAlphabets.isEmpty()) {
                String highest = lowercaseAlphabets.stream()
                        .max(Comparator.naturalOrder())
                        .orElse("");
                highestLowercase.add(highest);
            }
            response.setHighest_lowercase_alphabet(highestLowercase);

            response.setIs_prime_found(primeFound);

            MultipartFile file = request.getFile();
            if (file != null && !file.isEmpty()) {
                response.setFile_valid(true);
                response.setFile_mime_type(file.getContentType());
                response.setFile_size_kb(String.valueOf(file.getSize() / 1024));
            } else {
                response.setFile_valid(false);
                response.setFile_mime_type(null);
                response.setFile_size_kb(null);
            }

            response.setIs_success(true);
        } catch (Exception e) {
            response.setIs_success(false);
            response.setFile_valid(false);
            response.setFile_mime_type(null);
            response.setFile_size_kb(null);
        }

        return response;
    }


    public boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}