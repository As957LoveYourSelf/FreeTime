package com.example.freetime.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SaveInfoUtils {
    /**
     * 通过file文件保存用户信息
     */
    public static void saveInfo(String uid,String token){
        String info = uid + "##" + token;
        File file =  new File("data/data/com.example.freetime/info.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //将字符串写入到文件中
            fos.write(info.getBytes());
            //关闭数据流
            fos.close();
            System.out.println("用户信息写入成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("用户信息写失败成功");
        }
    }

    public static void clear() {
        File file = new File("data/data/com.example.freetime/info.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] readInfo() {
        File file =  new File("data/data/com.example.freetime/info.txt");
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String temp = reader.readLine();
//            System.out.println(Arrays.toString(temp.split("##")));
            reader.close();
            return temp.split("##");
        }catch (Exception e) {
            e.printStackTrace();
            return new String[]{"",""};
        }
    }
}
