package my.vips.learning.aws.recipies_teller;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vipinsharma on 26/04/18.
 */
public class RecipeTellerRequestHandler extends SpeechletRequestStreamHandler {

    private static final Set<String> supportedApplicationIds = new HashSet<String>();

    public RecipeTellerRequestHandler() {
        super(new RecipeTellerSpeechlet(), supportedApplicationIds);
    }
}
