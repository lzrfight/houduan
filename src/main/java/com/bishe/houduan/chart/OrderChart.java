package com.bishe.houduan.chart;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderChart {
    String ordertime;
    int onlineorder;
    int offlineorder;
}
