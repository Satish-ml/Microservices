package com.example.demo.Controller;

import com.example.demo.Service.EmployeeRecordsService;
import com.example.demo.Service.EmployeeRecordsService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api")
public class EmployeeRecordsController {

    @Autowired
    private EmployeeRecordsService employeeRecordsService;
    @Autowired
    private EmployeeRecordsService2 employeeRecordsService2;

    @PostMapping("/hello")
    public ResponseEntity<String> printData() {
        String res = employeeRecordsService.populateData();
        if (res.equals("Data Inserted Successfully")) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(500).body(res);
    }




    @PostMapping("/hello2")
    public ResponseEntity<String> printData2(){
        String res = employeeRecordsService2.populateData();
        if(res.equals("Data Inserted Successfully")){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(500).body(res);
    }
    @PostMapping("/hello3")
    public ResponseEntity<String> saveData(@RequestParam("file")MultipartFile file) throws IOException{
        String res = employeeRecordsService.saveFileData(file.getInputStream());
        if(res.equals("Data Inserted Successfully")){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(500).body(res);
    }
}
