package com.lee.boardsystem.service.impl;

import com.lee.boardsystem.model.Msg;
import com.lee.boardsystem.repository.MsgRepo;
import com.lee.boardsystem.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MsgServiceImpl implements MsgService {

    @Autowired
    private MsgRepo msgRepo;

    @Override
    public List<Msg> getPagedMsg(int page, int size) {

        Page<Msg> pageResult = msgRepo.findAll(
                PageRequest.of(page,size, Sort.by("publish").descending())
        );

        pageResult.getNumberOfElements(); // 當前頁數
        pageResult.getTotalElements(); // 總共頁數
        pageResult.getSize(); // 每頁筆數
        pageResult.getTotalPages(); // 總共筆數

        List<Msg> msgList = pageResult.getContent();
//        msgList.add(pageResult.getTotalPages());
        System.out.println(pageResult.getTotalPages());
        return msgList;
//        return Arrays.asList(msgList, pageResult.getTotalElements());
    }
    public Integer getPagedAllMsg(int page, int size) {

        Page<Msg> pageResult = msgRepo.findAll(
                PageRequest.of(page,size, Sort.by("publish").descending())
        );

        pageResult.getNumberOfElements(); // 當前頁數
        pageResult.getTotalElements(); // 總共頁數
        pageResult.getSize(); // 每頁筆數
        pageResult.getTotalPages(); // 總共筆數

        return pageResult.getTotalPages();
//        return Arrays.asList(msgList, pageResult.getTotalElements());
    }

    @Override
    public Msg getMsg(Integer msgId) {
        Msg msg = msgRepo.findById(msgId).orElse(null);
        return msg;
    }

    @Override
    public List<Msg> getAllMsg() {
        List<Msg> allMsg = msgRepo.findAll();
        return allMsg;
    }

    @Override
    public String addMsg(Msg msg) {
        msgRepo.save(msg);
        return "ServiceImpl save msg";
    }

    @Override
    public String updateMsg(Integer msgId, Msg msg) {
        Msg m = msgRepo.findById(msgId).orElse(null);
        if(m!=null){
            m.setTitle(msg.getTitle());
            m.setContent(msg.getContent());
            m.setDeadline(msg.getDeadline());
            msgRepo.saveAndFlush(m);
            return "ServiceImpl update msg";
        }else{
            return "no data update error";
        }
    }

    @Override
    public String deleteMsg(Integer msgId) {
        msgRepo.deleteById(msgId);
        return "ServiceImpl delete msg";
    }

    @Override
    public Msg findMsgByUserId(Integer userId) {
        return null;
    }
}
