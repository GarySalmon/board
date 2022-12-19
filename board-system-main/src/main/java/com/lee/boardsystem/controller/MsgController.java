package com.lee.boardsystem.controller;

import com.lee.boardsystem.model.Msg;
import com.lee.boardsystem.model.User;
import com.lee.boardsystem.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MsgController {


    @GetMapping("/msgs/{setpage}/{setsize}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<List<Msg>> getPagedMsg(
            @PathVariable Integer setpage,
            @PathVariable Integer setsize){
//        Integer page = 0;
        List<Msg> msgList = msgService.getPagedMsg(setpage,setsize);
        return ResponseEntity.status(HttpStatus.OK).body(msgList);
    }

    @GetMapping("/msgsPages/{setpage}/{setsize}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<Integer> getPagedAllMsg(
            @PathVariable Integer setpage,
            @PathVariable Integer setsize){
//        Integer page = 0;
        Integer pages = msgService.getPagedAllMsg(setpage,setsize);
        return ResponseEntity.status(HttpStatus.OK).body(pages);
    }

    @GetMapping("/msgs")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<List<Msg>> getAllMsg(){
        List<Msg> msgList = msgService.getAllMsg();
        return ResponseEntity.status(HttpStatus.OK).body(msgList);
    }

    @GetMapping("/msgs/{msgId}")
    @CrossOrigin(origins = "http://localhost:8081")
    public Msg getOneMsg(@PathVariable Integer msgId){
        return msgService.getMsg(msgId);
    }

    @Autowired
    private MsgService msgService;

    @PostMapping("/msgs")
    @CrossOrigin(origins = "http://localhost:8081")
    public String addMsg(@RequestBody Msg msg){
        return msgService.addMsg(msg);
    }

    @PutMapping("/msgs/{msgId}")
    @CrossOrigin(origins = "http://localhost:8081")
    public String updateMsg(@PathVariable Integer msgId,
                             @RequestBody Msg msg){
        return msgService.updateMsg(msgId,msg);
    }

    @DeleteMapping("/msgs/{msgId}")
    @CrossOrigin(origins = "http://localhost:8081")
    public String deleteMsg(@PathVariable Integer msgId){
        return msgService.deleteMsg(msgId);
    }


}
