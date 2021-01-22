import com.alibaba.fastjson.*;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
//API接口获取数据，解析json
public class db2{
    private Object LocalDateTime;

    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException
    {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonText = sb.toString();
            JSONObject json = JSONObject.parseObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public void GetData(String country) {
        String url="https://covid-api.mmediagroup.fr/v1/cases?country="+country;
        JSONObject json=null;
        try {
            json= readJsonFromUrl(url);
            Iterator<String> it=json.keySet().iterator();
            while(it.hasNext()){
                String key=it.next();
                JSONObject j=json.getJSONObject(key);
                Integer confirmed=j.getInteger("confirmed");
                Integer recovered=j.getInteger("recovered");
                Integer deaths=j.getInteger("deaths");

                db3 d=new db3();
                if(key=="All"){
                    if(country=="United%20Kingdom") key="United Kingdom";
                    else key=country;
                    d.InsertintoAll(key,confirmed,recovered,deaths);
                }
                else {
                    String tempDate=j.getString("updated");
                    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss+00");
                    LocalDateTime updated= java.time.LocalDateTime.parse((String) tempDate,formatter);
                    d.Insertinto(key,confirmed,recovered,deaths,updated);
                    System.out.println(key+' '+confirmed+' '+recovered+' '+deaths+' '+updated);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}