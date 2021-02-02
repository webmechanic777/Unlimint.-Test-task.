package validator.ru;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class State {
    private String id;
    private boolean exit;//конец или нет
    private Map<String, State> transition = new HashMap<>();

    public Map<String, State> getTransition2() {
        return transition;
    }

    public State(String id, boolean isAccept) {
        this.id = id;
        this.exit = isAccept;
    }

    public void addTransition(String key, State state) {
        transition.put(key, state);
    }

    public State getTransition(String key) {
        return transition.get(key);
    }
}
