package my.vips.learning.aws.currency_info;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;

/**
 * Created by vipinsharma on 05/04/18.
 */
public class CurrencyNameRequestHandler extends SpeechletRequestStreamHandler {

    public CurrencyNameRequestHandler() {
        super(new CurrencyNameSpeechlet(), new HashSet<String>());
    }
}
