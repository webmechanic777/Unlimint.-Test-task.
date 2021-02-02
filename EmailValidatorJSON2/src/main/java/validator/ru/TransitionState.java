package validator.ru;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransitionState {
    private String firstArgumentTransition;
    private String secondArgumentTransition;
    private String fromState;
    private String toState;

    public TransitionState(String firstArgumentTransition, String fromState, String toState) {
        this.firstArgumentTransition = firstArgumentTransition;
        this.fromState = fromState;
        this.toState = toState;
    }
}
