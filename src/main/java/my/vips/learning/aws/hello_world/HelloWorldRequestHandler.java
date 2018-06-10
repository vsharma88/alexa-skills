package my.vips.learning.aws.hello_world;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vipinsharma on 30/03/18.
 */
public class HelloWorldRequestHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds = new HashSet<String>();

    public HelloWorldRequestHandler() {
        super(new HelloWorldSpeechlet(), supportedApplicationIds);
    }
}