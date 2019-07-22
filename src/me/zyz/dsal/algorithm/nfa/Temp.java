package me.zyz.dsal.algorithm.nfa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Temp {
}

class NfaState {
    private static final NfaState ERR_STATE = new NfaState(true, true);
    private Map<Character, List<NfaState>> stateMap;
    private final boolean err;
    private final boolean end;

    public NfaState(boolean end) {
        this(false, end);
    }

    public NfaState(boolean err, boolean end) {
        this.err = err;
        this.end = end;
        this.stateMap = new HashMap<>();
    }
}

class StateMachine {
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

class State {
    private static final State ERR_STATE = new State(true, true);
    private Map<Character, State> stateMap;
    private final boolean err;
    private final boolean end;

    public State(boolean end) {
        this(false, end);
    }

    public State(boolean err, boolean end) {
        this.err = err;
        this.end = end;
        this.stateMap = new HashMap<>();
    }

    public boolean end() {
        return end;
    }

    public boolean err() {
        return err;
    }

    public void add(char c, State s) {
        stateMap.put(c, s);
    }

    public State nextState(char c) {
        return stateMap.getOrDefault(c, ERR_STATE);
    }
}
