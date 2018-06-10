package my.vips.learning.aws.secret_teller;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;

/**
 * Created by vipinsharma on 05/04/18.
 */
public class RandomSecretRequestHandler extends SpeechletRequestStreamHandler {

    public RandomSecretRequestHandler() {
        super(new RandomSecretSpeechlet(), new HashSet<String>());
    }
}
