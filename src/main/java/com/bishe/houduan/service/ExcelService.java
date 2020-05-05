package com.bishe.houduan.service;

import com.alibaba.excel.EasyExcel;
import com.bishe.houduan.pojo.Excel;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelService {
    String PATH = "D:\\";

    public List<Excel> data()
    {
        List<Excel>list = new ArrayList<Excel>();
        for (int i=0; i<10;i++)
        {
            Excel data = new Excel();
            data.setDate(new Date());
            data.setString("adsd");
            list.add(data);
        }
        return list;
    }

   public void simpleWrite()
   {
       String fileName = PATH +"test.xlsx";
       EasyExcel.write(fileName, Excel.class).sheet("模板").doWrite(data());
   }
   @Test
    public void caonima()
   {
       Date currentDate = new Date(System.currentTimeMillis());
//          格式化到天
       SimpleDateFormat timetoday =new SimpleDateFormat("yyyy-MM-dd");
       String timeday = timetoday.format(currentDate);
       System.out.println(timeday);
   }
}
