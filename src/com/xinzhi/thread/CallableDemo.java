package com.xinzhi.thread;

import com.xinzhi.pojo.Data;
import jxl.Cell;
import jxl.Workbook;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

public class CallableDemo implements Callable<List<Data>> {

    @Override
    public List<Data> call() throws Exception {
//        Workbook w = Workbook.getWorkbook(new File("E:/Data.xls"));
        Workbook w = Workbook.getWorkbook(new File("E:/学习软件/Data2.xls"));
        List<Data> list = new ArrayList<Data>();
        for (int i = 0; i < 10; i++){
            Cell[] row = w.getSheet(0).getRow(i);
            Data d = new Data();
            d.setId(row[0].getContents());
            d.setName(row[1].getContents());
            d.setPassword(row[2].getContents());
            list.add(d);
        }
        return list;
    }
}
