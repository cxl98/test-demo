package com.cxl.count;

import com.cxl.count.entry.Entry;
import com.cxl.count.util.PoiUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CountApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void xxx() throws FileNotFoundException {
        PoiUtils poiUtils=new PoiUtils();
        List<Entry> list=new ArrayList<>();
        Entry entry=new Entry();
        entry.setModel("xx");
        entry.setFreight(60);
        entry.setData(200);
        entry.setNumber(12);
        entry.setMachining(10);
        list.add(entry);
        poiUtils.exportExcel(list);
    }
}
