package my.vips.learning.aws.currency_info;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

import java.sql.SQLException;

/**
 * Created by vipinsharma on 05/04/18.
 */
public class CurrencyNameSpeechlet implements Speechlet {

    public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
        //do nothing
    }

    public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
        return getWelcomeResponse();
    }

    public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
        String intentName = (request.getIntent() != null) ? request.getIntent().getName() : null;
        String response ;
        SpeechletResponse speechletResponse;
        Intent intentRequest = request.getIntent();
        if(intentRequest.getName().equals("currencyname")){
            response = "Currency name of %s is.. %s";
            String countryName = intentRequest.getSlot("country").getValue();
            String currencyName ;
            try {
                currencyName = CurrencyTableDAO.getTheCurrencyNameForCountry(countryName);
                if(currencyName == null){
                    response = "Sorry no country with that name found..";
                }
                response = String.format(response,countryName,currencyName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            speechletResponse = getResponse(response);

        } else if(intentRequest.getName().equals("dialcode")){
            response = "Dial code for %s is.. %s";
            String countryName = intentRequest.getSlot("country").getValue();
            String dialCode ;
            try {
                dialCode = CurrencyTableDAO.getTheDialCodeForCountry(countryName);
                if(dialCode == null){
                    response = "Sorry no country with that name found";
                }
                response = String.format(response,countryName,dialCode);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            speechletResponse = getResponse(response);
        }
        else if ("AMAZON.HelpIntent".equals(intentName)) {
            return getHelpResponse();
        } else {
            return getAskResponse("Country Geek", "This is unsupported.  Please try something else.");
        }
        return speechletResponse;

    }

    public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
        //do nothing
    }

    private PlainTextOutputSpeech getPlainTextOutputSpeech(String speechText) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);
        return speech;
    }


    private SpeechletResponse getAskResponse(String cardTitle, String speechText) {
        SimpleCard card = getSimpleCard(cardTitle, speechText);
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);
        Reprompt reprompt = getReprompt(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    private Reprompt getReprompt(OutputSpeech outputSpeech) {
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(outputSpeech);

        return reprompt;
    }

    private SimpleCard getSimpleCard(String title, String content) {
        SimpleCard card = new SimpleCard();
        card.setTitle(title);
        card.setContent(content);

        return card;
    }

    private SpeechletResponse getHelpResponse() {
        String speechText = " you can ask Country information!";
        return getAskResponse("Country Geek", speechText);
    }


    private SpeechletResponse getWelcomeResponse() {
        String speechText = "Welcome to the Alexa Skills Kit, you can ask Country information";
        return getAskResponse("Country Geek", speechText);
    }

    private SpeechletResponse getResponse(String resultFromDB) {
        // Create the Simple card content.
        SimpleCard card = getSimpleCard("Country Geek", resultFromDB);
        // Create the plain text output.
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(resultFromDB);
        return SpeechletResponse.newTellResponse(speech, card);
    }
}
