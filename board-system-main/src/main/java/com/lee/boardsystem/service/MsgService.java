package com.lee.boardsystem.service;

import com.lee.boardsystem.model.Msg;
import com.lee.boardsystem.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface MsgService {
    public String addMsg(Msg msg);
    public String updateMsg(Integer msgId, Msg msg);

    public String deleteMsg(Integer msgId);

    //List<Msg> getPagedMsg(Integer page, Integer size);

    List<Msg> getPagedMsg(int page, int size);

    public Integer getPagedAllMsg(int page,int size);

    public Msg getMsg(Integer msgId);

    public List<Msg> getAllMsg();

    public Msg findMsgByUserId(Integer userId);
}
