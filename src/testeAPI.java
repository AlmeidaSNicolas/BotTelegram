import com.deepl.api.*;
import com.nicolas.botTelegram.config.AppConfig;

public class testeAPI {
    public static void main(String[] args) throws DeepLException, InterruptedException {
        String authKey = AppConfig.DEEPL_API_KEY; // replace with your key
        DeepLClient client = new DeepLClient(authKey);

        TextResult result = client.translateText("Hello, My name is nicolas", null, "pt-br");
        System.out.println(result.getText());
    }
}