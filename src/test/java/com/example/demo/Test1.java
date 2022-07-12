package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class Test1 {

    @Autowired
    private KmsProperties kmsProperties;

    @Test
    public void contextLoads() {
        //获取某月第一天
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);//传-1为上一个月
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND,0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        Date date=c.getTime();
        System.out.println(date);

        //获取上一个月最后一天
        Calendar ca = Calendar.getInstance();
        int month = ca.get(Calendar.MONTH);//获取当前月份
        ca.set(Calendar.MONTH, month-1);//设置上一个月
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至11
        ca.set(Calendar.HOUR_OF_DAY, 11);
        //将分钟至59
        ca.set(Calendar.MINUTE, 59);
        //将秒至59
        ca.set(Calendar.SECOND,59);
        Date date1=ca.getTime();
        System.out.println(date1);
    }

    @Test
    void test2(){
        ArrayList<String> list = new ArrayList<>();
        list.add("POST");
        list.add("GET");
        list.remove("GET");
        System.out.println(list);
    }

//    "rules": [
//    {
//        "url_pattern": "^/(?<org>[^/]+)/(?<app>[^/]+)/(user|users)$",
//            "rule_type": "api_flow_limit",
//            "http_method": [
//                  "POST"
//            ],
//        "notice_limit": 80,
//            "limit": 10,
//            "interval": 1000,
//            "api": "user",
//            "target": "APP",
//            "order": 10
//    },
//    {
//        "url_pattern": "^/(?<org>[^/]+)/(?<app>[^/]+)/(messages)$",
//            "rule_type": "api_flow_limit",
//            "http_method": [
//                  "POST"
//            ],
//        "notice_limit": 80,
//            "limit": 10,
//            "interval": 1000,
//            "api": "message",
//            "target": "APP",
//            "order": 10
//    },
//    {
//        "url_pattern": "^/(?<org>[^/]+)/(?<app>[^/]+)/chatrooms/[^/]+$",
//            "rule_type": "api_flow_limit",
//            "http_method": [
//                  "GET"
//            ],
//        "notice_limit": 80,
//            "limit": 10,
//            "interval": 1000,
//            "api": "chatroom",
//            "target": "APP",
//            "order": 10
//    },
//    {
//        "url_pattern": "^/(?<org>[^/]+)/(?<app>[^/]+)/(messages)/(roaming_settings)$",
//            "rule_type": "api_flow_limit",
//            "http_method": [
//                "POST",
//                "GET"
//            ],
//        "notice_limit": 80,
//            "limit": 30,
//            "interval": 10000,
//            "api": "roaming_settings",
//            "target": "APP",
//            "order": 50
//    }
//    ],

}












