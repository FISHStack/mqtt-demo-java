import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

public class PublishSample {

  public static void main(String[] args) throws UnsupportedEncodingException {

//    String topic = "mqtt/test";

    Gson gson = new Gson();
    JsonObject jsonObject = new JsonObject();

    JsonObject data = new JsonObject();
    data.addProperty("happy",1);

    String devEUI = "0000000000000001";
    String sendMessage = "123";

    String encode = new BASE64Encoder().encode(sendMessage.getBytes());

    jsonObject.addProperty("confirmed",false);
    jsonObject.addProperty("data",encode);
    jsonObject.addProperty("fPort",2);
    jsonObject.addProperty("fCnt",1);

    String content = gson.toJson(jsonObject);
    int qos = 0;
//    String broker = "tcp://192.168.3.168:1883";
//    String userName = "admin";
//    String password = "admin";
    String clientId = "pubClient";
    // 内存存储
    MemoryPersistence persistence = new MemoryPersistence();

    try {
      // 创建客户端
      MqttClient sampleClient = new MqttClient(ContactHelp.HOST, clientId, persistence);
      // 创建链接参数
      MqttConnectOptions connOpts = new MqttConnectOptions();
      // 在重新启动和重新连接时记住状态
      connOpts.setCleanSession(false);
      // 设置连接的用户名
//      connOpts.setUserName(userName);
//      connOpts.setPassword(password.toCharArray());
      // 建立连接
      sampleClient.connect(connOpts);
      // 创建消息
      MqttMessage message = new MqttMessage(content.getBytes());
      // 设置消息的服务质量
      message.setQos(qos);
      // 发布消息
      sampleClient.publish("application/1/device/"+devEUI+"/tx", message);
      // 断开连接
      sampleClient.disconnect();
      // 关闭客户端
      sampleClient.close();
    } catch (MqttException me) {
      System.out.println("reason " + me.getReasonCode());
      System.out.println("msg " + me.getMessage());
      System.out.println("loc " + me.getLocalizedMessage());
      System.out.println("cause " + me.getCause());
      System.out.println("excep " + me);
      me.printStackTrace();
    }
  }
}

