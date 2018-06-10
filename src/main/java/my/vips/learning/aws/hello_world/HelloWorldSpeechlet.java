package my.vips.learning.aws.hello_world;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This sample shows how to create a simple speechlet for handling speechlet requests.
 */
public class HelloWorldSpeechlet implements Speechlet {
    private static final Logger log = LoggerFactory.getLogger(HelloWorldSpeechlet.class);

    /**
     * Creates and returns a {@code SpeechletResponse} with a welcome message.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private SpeechletResponse getWelcomeResponse() {
        String speechText = "Welcome to the Alexa Skills Kit, you can say hello";
        return getAskResponse("HelloWorld", speechText);
    }

    /**
     * Creates a {@code SpeechletResponse} for the hello intent.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private SpeechletResponse getHelloResponse(String personToGreet) {
        String speechText = "Hello "+personToGreet ;

        // Create the Simple card content.
        SimpleCard card = getSimpleCard("Hello", speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }

    /**
     * Creates a {@code SpeechletResponse} for the help intent.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private SpeechletResponse getHelpResponse() {
        String speechText = "You can say hello to me!";
        return getAskResponse("Hello", speechText);
    }

    /**
     * Helper method that creates a card object.
     * @param title title of the card
     * @param content body of the card
     * @return SimpleCard the display card to be sent along with the voice response.
     */
    private SimpleCard getSimpleCard(String title, String content) {
        SimpleCard card = new SimpleCard();
        card.setTitle(title);
        card.setContent(content);

        return card;
    }

    /**
     * Helper method for retrieving an OutputSpeech object when given a string of TTS.
     * @param speechText the text that should be spoken out to the user.
     * @return an instance of SpeechOutput.
     */
    private PlainTextOutputSpeech getPlainTextOutputSpeech(String speechText) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return speech;
    }

    /**
     * Helper method that returns a reprompt object. This is used in Ask responses where you want
     * the user to be able to respond to your speech.
     * @param outputSpeech The OutputSpeech object that will be said once and repeated if necessary.
     * @return Reprompt instance.
     */
    private Reprompt getReprompt(OutputSpeech outputSpeech) {
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(outputSpeech);

        return reprompt;
    }

    /**
     * Helper method for retrieving an Ask response with a simple card and reprompt included.
     * @param cardTitle Title of the card that you want displayed.
     * @param speechText speech text that will be spoken to the user.
     * @return the resulting card and speech text.
     */
    private SpeechletResponse getAskResponse(String cardTitle, String speechText) {
        SimpleCard card = getSimpleCard(cardTitle, speechText);
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);
        Reprompt reprompt = getReprompt(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    /**
     * Used to notify that a new session started as a result of a user interacting with the device.
     * This method enables {@code Speechlet}s to perform initialization logic and allows for session
     * attributes to be stored for subsequent requests.
     *
     * @param request the session started request
     * @param session the session associated with the user starting a {@code Speechlet}
     * @throws SpeechletException for any errors encountered in the processing of the request
     */
    public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
        log.info("onSessionStarted sessionId={}", session.getSessionId());
        // any initialization logic goes here
    }

    /**
     * Entry point for {@code Speechlet}s for handling a speech initiated request to start the skill
     * without providing an {@code Intent}.<br>
     * <p>
     * This method is only invoked when {@link Session#isNew()} is {@code true}.
     *
     * @param request the launch request
     * @param session the session associated with the request
     * @return the response, spoken and visual, to the request
     * @throws SpeechletException for any errors encountered in the processing of the request
     */
    public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
        log.info("onLaunch sessionId={}", session.getSessionId());
        return getWelcomeResponse();

    }

    /**
     * Entry point for handling speech initiated requests.<br>
     * <p>
     * This is where the bulk of the {@code Speechlet} logic lives. Intent requests are handled by
     * this method and return responses to render to the user.<br>
     * <p>
     * If this is the initial request of a new {@code Speechlet} session, {@link Session#isNew()}
     * returns {@code true}. Otherwise, this is a subsequent request within an existing session.
     *
     * @param request the intent request to handle
     * @param session the session associated with the request
     * @return the response, spoken and visual, to the request
     * @throws SpeechletException for any errors encountered in the processing of the request
     */
    public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("SayHello".equals(intentName)) {
            String helloToPerson = intent.getSlot("person").getValue();
            return getHelloResponse(helloToPerson);
        } else if ("AMAZON.HelpIntent".equals(intentName)) {
            return getHelpResponse();
        } else {
            return getAskResponse("HelloWorld", "This is unsupported.  Please try something else.");
        }
    }

    /**
     * Callback used to notify that the session ended as a result of the user interacting, or not
     * interacting with the device. This method is not invoked if the {@code Speechlet} itself ended
     * the session using {@link SpeechletResponse#setShouldEndSession(boolean)}.
     *
     * @param request the end of session request
     * @param session the session associated with the request
     * @throws SpeechletException for any errors encountered in the processing of the request
     */
    public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
        log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
    }
}