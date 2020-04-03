package com.cxl.dom;

import com.cxl.entry.EquipMsg;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipMsgMapper {
    EquipMsg add(EquipMsg msg);
    int delete(int id);
    List<EquipMsg> getEquipMents(List<Integer> list);
    List<EquipMsg> getAllEquipMents();
}
