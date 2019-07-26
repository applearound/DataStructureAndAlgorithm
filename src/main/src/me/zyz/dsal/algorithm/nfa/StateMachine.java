package me.zyz.dsal.algorithm.nfa;

public class StateMachine {
    private State s0;

    public StateMachine(State s0) {
        this.s0 = s0;
    }

    public boolean judge(CharSequence charSequence) {
        State currentState = s0;
        for (int i = 0; i < charSequence.length(); i++) {
            currentState = currentState.nextState(charSequence.charAt(i));
            if (currentState.err()) {
                System.out.println(String.format("Illegal char at %d, %c", i, charSequence.charAt(i)));
                return false;
            }
        }

        if (!currentState.end()) {
            System.out.println(String.format("Illegal char at %d, %c", charSequence.length() - 1, charSequence.charAt(charSequence.length() - 1)));
            return false;
        } else {
            return true;
        }
    }
}
