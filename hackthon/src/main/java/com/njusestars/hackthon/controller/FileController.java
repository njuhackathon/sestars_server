package com.njusestars.hackthon.controller;

import com.njusestars.hackthon.util.ResultMessage;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    private String dir;

    @PostConstruct
    public void init(){
        dir = getJarDir() + "/img/";
    }

    private String getJarDir(){
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getDir();
        return jarF.getParentFile().toString();
    }

    private boolean assureExists(String path) {
        File directory = new File(path);
        return directory.isDirectory() || directory.mkdirs();
    }

    @PostMapping(value = "/img")  //ajax请求的url
    @ResponseBody
    public ResultMessage uploadImage(MultipartFile file) {
        String uuid = UUID.randomUUID().toString();
        String path = dir;
        this.assureExists(path);
        //   String noPostfixFileName =  myFileName.substring(0,myFileName.indexOf("."));
        path += uuid;
        File localFile = new File(path);
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResultMessage("Upload Success", true, uuid);
    }

    @GetMapping(value = "/img/{fileName}")
    public ResponseEntity<byte[]> testResponseEntity(@PathVariable String fileName) throws IOException {
        byte[] body;
        String path = dir;
        path += fileName;
        File f = new File(path);
        InputStream in = new FileInputStream(f);
        body = new byte[in.available()];
        in.read(body);
        in.close();
        HttpHeaders headers = new HttpHeaders();
        //响应头的名字和响应头的值
        headers.add("Content-Disposition", "attachment;filename="+fileName);
        HttpStatus statusCode = HttpStatus.OK;
        return new ResponseEntity<>(body, headers, statusCode);
    }
}
