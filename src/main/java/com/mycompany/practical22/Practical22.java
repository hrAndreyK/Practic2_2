/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practical22;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class Practical22 {

    public static void main(String[] args) {
        System.out.println("Start Program!");
        String server = "https://android-for-students.ru";
        String serverPath = "/materials/practical/hello.php";
        HashMap<String, String> map = new HashMap();
        map.put("name", "Khokhlov");
        map.put("group", "RIBO-03-22");
        HTTPRunnable hTTPRunnable = new HTTPRunnable(server + serverPath, map);
        Thread th = new Thread(hTTPRunnable);
        th.start();
        try {
            th.join();
        } catch (InterruptedException ex) {
        } finally{
            JSONObject jSONObject = new JSONObject(hTTPRunnable.getResponseBody());
            int result = jSONObject.getInt("result_code");
            System.out.println("Result: " + result);
            System.out.println("Type: " + jSONObject.getString("message_type"));
            System.out.println("Text: " + jSONObject.getString("message_text"));
            switch (result) {
                case 1: 
                    JSONArray jSONArray = jSONObject.getJSONArray("task_list");
                    System.out.println("Task list:");
                    for(int i = 0; i < jSONArray.length(); i++){
                        System.out.println((i + 1) + ") " + jSONArray.getString(i));
                    }
                    break;
                case 0: 
                    break;
                default: 
                    break;
            }
        }
    }
}
