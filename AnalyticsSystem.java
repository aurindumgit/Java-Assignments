//record actions
//once K actions recorded, send to AnalyticsStore
//Actions represented by enumeration called ActionEnum

//Constructor: Analytics(AnalyticsStore analyticsStore, int K)

//Method:
//void recordAction(ActionEnum action)
//analyticsStore.storeActions(Queue<ActionEnum> q)
//int getNumberOfActionRegisteredButNotSentToAnalyticsStore()
//getTotalNumberOfLoggedActions()
//List<ActionEnum> getMostFrequentlyUsedActions()

import java.util.*;

enum ActionEnum {
    LOGIN,
    LOGOUT,
    CLICK,
    PURCHASE
}

class AnalyticsStore {

    private List<ActionEnum> storedActions = new ArrayList<>();

    public void storeActions(Queue<ActionEnum> actions) {
        storedActions.addAll(actions);
    }

    public int getStoredCount() {
        return storedActions.size();
    }
}

public class AnalyticsSystem {
    private AnalyticsStore analyticsStore ;
    private int K;

    private Queue<ActionEnum> buffer;
    private Map<ActionEnum, Integer> freqMap;

    private int totalActions;

    //constructor
    public AnalyticsSystem(AnalyticsStore analyticsStore, int K) {
        this.analyticsStore = analyticsStore;
        this.K = K;
        this.buffer = new LinkedList<>();
        this.freqMap = new HashMap<>();
        this.totalActions = 0;
    }

    public void recordAction(ActionEnum action){
        buffer.add(action);
        totalActions++;

        freqMap.put(action, freqMap.getOrDefault(action,0) + 1);

        if(buffer.size() >= K){
            analyticsStore.storeActions(buffer);
            buffer.clear();
        }
    }

    public int getNumberOfActionRegisteredButNotSentToAnalyticsStore() {
        return buffer.size();
    }

    public int getTotalNumberOfLoggedActions() {
        return totalActions;
    }

    public List<ActionEnum> getMostFrequentlyUsedActions() {

        List<ActionEnum> result = new ArrayList<>();

        if (freqMap.isEmpty()) return result;

        int maxFreq = Collections.max(freqMap.values());

        for (Map.Entry<ActionEnum, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() == maxFreq) {
                result.add(entry.getKey());
            }
        }

        result.sort(Comparator.comparing(Enum::name));

        return result;
    }

    public static void main(String[] args) {

        AnalyticsStore store = new AnalyticsStore();
        AnalyticsSystem system = new AnalyticsSystem(store, 3);

        system.recordAction(ActionEnum.LOGIN);
        system.recordAction(ActionEnum.CLICK);

        System.out.println(system.getNumberOfActionRegisteredButNotSentToAnalyticsStore()); 
        // Expected: 2

        system.recordAction(ActionEnum.PURCHASE);

        System.out.println(system.getNumberOfActionRegisteredButNotSentToAnalyticsStore()); 
        // Expected: 0 (batch flushed)

        System.out.println(system.getTotalNumberOfLoggedActions()); 
        // Expected: 3

        system.recordAction(ActionEnum.LOGIN);
        system.recordAction(ActionEnum.LOGIN);

        System.out.println(system.getMostFrequentlyUsedActions()); 
        // Expected: [LOGIN]
        }


}
