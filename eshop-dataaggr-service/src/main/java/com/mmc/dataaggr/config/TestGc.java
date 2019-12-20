package com.mmc.dataaggr.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-13 15:42
 **/

public class TestGc {

    public void gc1(){
        byte[] a=new byte[6*1024*1024];
//        int c=10;
        System.gc();
    }

    public void gc2(){
        {
            byte[] a=new byte[6*1024*1024];
        }
        int c=10;
        System.gc();
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().maxMemory());
        System.out.println(Runtime.getRuntime().freeMemory());
        List list=new ArrayList();
        while (true){
            list.add(new byte[1024*1024]);
        }
    }
}
