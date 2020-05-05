package Servicios.WebServices;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class TestWebService {
    public static void main(String[] args) {
            try {
                URL url = new URL("http://194.224.79.42:44080/wstutorial/User/signup.php");
                URLConnection con = url.openConnection();
                HttpURLConnection http = (HttpURLConnection) con;
                http.setRequestMethod("POST");
                http.setDoOutput(true);

                Map<String, String> arguments = new HashMap<>();
                arguments.put("username", "Damia");
                arguments.put("password", "1234");
                StringJoiner sj = new StringJoiner("&");

                for(Map.Entry<String, String> entry : arguments.entrySet()){
                    sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
                }

                byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
                int length = out.length;
                http.setFixedLengthStreamingMode(length);
                http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                http.connect();
                try{
                    System.out.println(new String(out));
                    OutputStream os = http.getOutputStream();
                    os.write(out);

                    InputStream is = http.getInputStream();
                    byte[] a = new byte[200];
                    is.read(a);
                    System.out.println(new String(a));
                }
                catch(Exception e){
                    e.printStackTrace();
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}