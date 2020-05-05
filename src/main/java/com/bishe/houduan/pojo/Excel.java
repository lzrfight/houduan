package com.bishe.houduan.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Excel {
    @ExcelProperty("sadasd")
    String string;
    @ExcelProperty("sadasdsa")
    Date date;
}
