package com.wannaporn.phoso.menufoodthai;

import android.os.StrictMode;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Phoso on 5/12/2559.
 */
public class MyData {
    String[] dataMenuId=new String[500];
    String[] dataFoodId=new String[500];
    String[] dataMenuName=new String[500];
    String[] dataRaw=new String[500];
    String[] dataHowto=new String[500];
    String[] dataImg=new String[500];
    int i=0;
    String ids="";
    public MyData(){
    }
    public MyData(String i){
        ids=i;
        synJSONtoSQLite();
    }
    private void synJSONtoSQLite() {
        StrictMode.ThreadPolicy myPolicy =new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);
        int intTime=0;
        while (intTime<=1){
            InputStream objInputStream = null;
            String strJSON=null;
            String strMenuDetail="http://csclub.ssru.ac.th/s56122201096/menu/MenuDatail.php?id="+ids;
            HttpPost objHttpPost = null;
            //1.Create InputStream
            try{
                HttpClient objHttpClient =new DefaultHttpClient();
                switch (intTime){
                    case 0:
                        objHttpPost=new HttpPost(strMenuDetail);
                        break;
                }
                HttpResponse objHttpResponse =objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity=objHttpResponse.getEntity();
                objInputStream=objHttpEntity.getContent();
            }catch (Exception e){
                Log.d("masterUNG","InputStream==>"+e.toString());
            }
            //2.Create strJSON
            try {
                BufferedReader objBufferedReader=new BufferedReader(new InputStreamReader(objInputStream,"UTF-8"));
                StringBuilder objStringBuilder =new StringBuilder();
                String strLine=null;
                while((strLine=objBufferedReader.readLine())!=null){
                    objStringBuilder.append(strLine);
                }
                objInputStream.close();
                strJSON=objStringBuilder.toString();
            }catch (Exception e){
                Log.d("masterUNG","strJSON==>"+e.toString());
            }
            //3.Update to SQLite
            try {
                JSONArray objJsonArray =new JSONArray(strJSON);
                for(int i=0;i<objJsonArray.length();i++){
                    JSONObject jsonObject=objJsonArray.getJSONObject(i);
                    switch (intTime){
                        case  0:
                            String strMenuId=jsonObject.getString("menu_id");
                            String strFoodId=jsonObject.getString("food_id");
                            String strMenuName=jsonObject.getString("menu_name");
                            String strMenuRaw=jsonObject.getString("menu_raw_material");
                            String strMenuHowto=jsonObject.getString("menu_howto");
                            String strImg=jsonObject.getString("menu_img");
                            addDataName(strMenuId,strFoodId,strMenuName,strMenuRaw,strMenuHowto,strImg);
                            break;
                    }
                }
            }catch (Exception e){
                Log.d("masterUNG","Update SQLite==>"+e.toString());
            }
            intTime+=1;

        }
        Log.v("showSite",dataImg[1]);
    }
    private void addDataName(String menuid,String foodid,String menuname,String raw,String howto,String img){
        dataMenuId[i]=menuid;
        dataFoodId[i]=foodid;
        dataMenuName[i]=menuname;
        dataRaw[i]=raw;
        dataHowto[i]=howto;
        dataImg[i]=img;
        i++;
    }
}
