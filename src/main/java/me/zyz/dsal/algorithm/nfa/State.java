package me.zyz.dsal.algorithm.nfa;

import java.util.HashMap;
import java.util.Map;

public class State {
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
