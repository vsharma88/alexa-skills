package my.vips.learning.aws.secret_teller;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Created by vipinsharma on 05/04/18.
 */
public class RandomSecretSpeechlet implements Speechlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomSecretSpeechlet.class);
    private static final HashMap<Integer, String> FACTS_MAP = new HashMap<Integer, String>();

    static{
        FACTS_MAP.put(1,"And your heart's against my chest,your lips pressed to my neck.. I'm falling for your eyes, but they don't know me yet And with a feeling I'll forget,I'm in love now");
        FACTS_MAP.put(2,"You remind me of a compass because I’d be lost without you.");
        FACTS_MAP.put(3,"Something happened to me. It was the sweetest thing that ever could be; it was a fantasy a dream come true; It was the day I met you");
        FACTS_MAP.put(4,"I made a wish and... you came true.");
        FACTS_MAP.put(5,"My love for you can never dilute. Off course, I can’t help that it’ll die with me when I die.");

    }

    public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
        LOGGER.info("onSessionEnded requestId={}, sessionId={}",request.getRequestId(),session.getSessionId());
    }

    public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
        LOGGER.info("onSessionEnded requestId={}, sessionId={}",request.getRequestId(),session.getSessionId());
        return getWelcomeResponse();
    }

    public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
        LOGGER.info("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("secretteller".equals(intentName)) {
            return getWelcomeResponse();
        } else if ("AMAZON.HelpIntent".equals(intentName)) {
            return getHelpResponse();
        } else {
            return getAskResponse("Secret Teller", "This is unsupported.  Please try something else.");
        }

    }

    public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
        LOGGER.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
    }

    private SpeechletResponse getWelcomeResponse() {
        String speechText = "Welcome to the Alexa Skills Kit, you can ask me a secret";
        return getAskResponse("Secret Teller", speechText);
    }

    private SpeechletResponse getAskResponse(String cardTitle, String speechText) {
        SimpleCard card = getSimpleCard(cardTitle, speechText);
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);
        Reprompt reprompt = getReprompt(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    private SimpleCard getSimpleCard(String title, String content) {
        SimpleCard card = new SimpleCard();
        card.setTitle(title);
        card.setContent(content);
        return card;
    }

    private PlainTextOutputSpeech getPlainTextOutputSpeech(String speechText) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);
        return speech;
    }

    private Reprompt getReprompt(OutputSpeech outputSpeech) {
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(outputSpeech);
        return reprompt;
    }

    private SpeechletResponse getHelpResponse() {
        String speechText = "You can ask me a secret !";
        return getAskResponse("Secret Teller", speechText);
    }


}
