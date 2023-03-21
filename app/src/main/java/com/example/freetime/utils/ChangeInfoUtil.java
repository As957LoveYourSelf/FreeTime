package com.example.freetime.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ChangeInfoUtil {



    public static void saveInfo(Integer age,String sex,String email,String phone,String introduce){
        String info = age.toString() + "\n" + sex+"\n"+email+"\n"+phone+"\n"+introduce;
        File file =  new File("data/data/com.example.freetime/old.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //将字符串写入到文件中
            fos.write(info.getBytes());
            //关闭数据流
            fos.close();
//            System.out.println("用户信息写入成功");
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("用户信息写失败");
        }
    }

    public static void clear() {
        File file = new File("data/data/com.example.freetime/old.txt");
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
        File file =  new File("data/data/com.example.freetime/old.txt");
        try {
            // 如果直接reader.readLine() != null会读一行少一行！
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            List<String> list = new ArrayList<String>();
            String str = null;
            while ((str = reader.readLine()) != null){
                list.add(str);
            }
            String[] arr = list.toArray(new String[0]);
            reader.close();
            return arr;
        }catch (Exception e) {
            e.printStackTrace();
            return new String[]{"","","","","",""};
        }
    }
}
