package my.vips.learning.aws.recipies_teller;

import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;

import my.vips.learning.aws.recipies_teller.dao.RecipeDAO;
import my.vips.learning.aws.recipies_teller.dao.RecipeDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by vipinsharma on 09/04/18.
 */

public class RecipeTellerSpeechlet implements Speechlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeTellerSpeechlet.class);

    public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
        LOGGER.info(String.format("Session started for request_id %s and session_id %s",request.getRequestId(),session.getSessionId()));
    }

    public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
        return null;
    }

    public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
        SpeechletResponse speechletResponse = null;
        if(request.getIntent().getName().equals("personalchef")){
            String dishName = request.getIntent().getSlot("dishName").getValue();
            RecipeDAO recipeDAO = new RecipeDAOImpl();
            Recipe recipe = recipeDAO.getRecipeWithCode(dishName);
            speechletResponse = getResponseSpeech("Here is the thing "+recipe.getDescription());
        }else{

        }
        return speechletResponse;
    }

    public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
        LOGGER.info(String.format("Session ended for request_id %s and session_id %s",request.getRequestId(),session.getSessionId()));
    }

    private SpeechletResponse getResponseSpeech(String speech){
        PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
        plainTextOutputSpeech.setText(speech);
        return SpeechletResponse.newAskResponse(plainTextOutputSpeech,new Reprompt());
    }
}
