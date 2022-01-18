package me.zyz.dsal.algorithm.nfa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yezhou
 */
public class NfaState {
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
