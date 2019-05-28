import com.google.gson.Gson;
import com.google.gson.JsonObject;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Base64MQTT {
    public static void main(String[] args) throws IOException {

        final BASE64Encoder encoder = new BASE64Encoder();
        final BASE64Decoder decoder = new BASE64Decoder();
        final String text = "MTIz";

//        Gson gson = new Gson();
//        JsonObject object = new JsonObject();
//        object.addProperty("hello","world");
//        String encode = new BASE64Encoder().encode(gson.toJson(object).getBytes());
//        System.out.println(encode);

//        final byte[] textByte = gson.toJson(object).getBytes("UTF-8");
//编码

        final String encodedText = "Mw==";
        System.out.println(encodedText);
//解码
//        System.out.println(new String(decoder.decodeBuffer(encodedText), "UTF-8"));
        System.out.println( new String(decoder.decodeBuffer(encodedText)));
        byte[] data = decoder.decodeBuffer(encodedText);
//        System.out.println(data.length);
//        for (byte datum : data) {
//            System.out.println(datum);
//        }

    }
}
