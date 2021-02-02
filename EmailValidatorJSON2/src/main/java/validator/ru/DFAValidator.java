package validator.ru;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DFAValidator {
    private JsonObject jsonObject;
    private Map<String, State> states = new HashMap<>();
    private List<TransitionState> transitionStates = new ArrayList<>();

    public DFAValidator(String path) throws IOException {//"src/main/resources/task/transition2.json"
        String jsonString = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
    }

    public void readStatesAndTransition() throws IOException {
        JsonArray arrState = jsonObject.getAsJsonArray("State");
        for (int i = 0; i < arrState.size(); i++) {
            states.put(arrState.get(i).getAsJsonObject().get("id").getAsString(),
                    new State(arrState.get(i).getAsJsonObject().get("id").getAsString(), arrState.get(i).getAsJsonObject().get("exit").getAsBoolean()));
        }
        readTransition();
    }

    public void readTransition() {
        JsonArray arrTranSt = jsonObject.getAsJsonArray("TransitionState");
        for (int i = 0; i < arrTranSt.size(); i++) {
            String firstArgumentTransition = arrTranSt.get(i).getAsJsonObject().get("firstArgumentTransition").getAsString();
            String secondArgumentTransition;
            try {
                secondArgumentTransition = arrTranSt.get(i).getAsJsonObject().get("secondArgumentTransition").getAsString();
            } catch (NullPointerException e) {
                secondArgumentTransition = null;
            }
            String fromState = arrTranSt.get(i).getAsJsonObject().get("fromState").getAsString();
            String toState = arrTranSt.get(i).getAsJsonObject().get("toState").getAsString();
            if (secondArgumentTransition == null) {
                transitionStates.add(new TransitionState(firstArgumentTransition, fromState, toState));
            } else {
                transitionStates.add(new TransitionState(firstArgumentTransition, secondArgumentTransition, fromState, toState));
            }
        }
        addTransition();
    }

    public boolean validate(String str) {
        char[] arr = str.toCharArray();
        State curState = states.get("q0");
        for (int i = 0; i < arr.length; i++) {
            State nextState = curState.getTransition(Character.toString(arr[i]));//Character.toString() вернёт String
            if (nextState == null) {
                return false;
            }
            curState = nextState;
        }
        return curState.isExit();
    }
// универсальный метод создания связей
    public void addTransition() {
        for (int i = 0; i < transitionStates.size(); i++) {
            if (transitionStates.get(i).getSecondArgumentTransition() != null) {
                char fromSymbol = transitionStates.get(i).getFirstArgumentTransition().charAt(0);
                char toSymbol = transitionStates.get(i).getSecondArgumentTransition().charAt(0);
                State fromState = states.get(transitionStates.get(i).getFromState());
                State toState = states.get(transitionStates.get(i).getToState());
                for (char c = fromSymbol; c <= toSymbol; c++) {
                    fromState.addTransition(Character.toString(c), toState);
                }
            } else {
                State fromState = states.get(transitionStates.get(i).getFromState());
                State toState = states.get(transitionStates.get(i).getToState());
                fromState.addTransition(transitionStates.get(i).getFirstArgumentTransition(), toState);
            }
        }
    }
}
