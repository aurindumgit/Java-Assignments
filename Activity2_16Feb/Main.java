package Activity2_16Feb;

import java.util.*;

enum ActionType {
    DEPOSIT, WITHDRAW, TRANSFER, LOGIN, FAILED_LOGIN
}

enum Status {
    SUCCESS, FAILED
}

class LogEntry {
    private final int logId;
    private final String accountNumber;
    private final ActionType actionType;
    private final double amount;
    private final long timestamp;
    private final Status status;

    public LogEntry(int logId, String accountNumber, ActionType actionType, double amount, Status status) {
        this.logId = logId;
        this.accountNumber = accountNumber;
        this.actionType = actionType;
        this.amount = amount;
        this.status = status;
        this.timestamp = System.currentTimeMillis();
    }

    public int getLogId() { return logId; }
    public String getAccountNumber() { return accountNumber; }
    public ActionType getActionType() { return actionType; }
    public double getAmount() { return amount; }
    public long getTimestamp() { return timestamp; }
    public Status getStatus() { return status; }

    @Override
    public String toString() {
        return "LogId=" + logId +
                ", Acc=" + accountNumber +
                ", Action=" + actionType +
                ", Amount=" + amount +
                ", Status=" + status +
                ", Time=" + new Date(timestamp);
    }
}

interface SuspiciousDetector {
    boolean isSuspicious(LogEntry log, List<LogEntry> accountLogs);
}

class BasicSuspiciousDetector implements SuspiciousDetector {
    @Override
    public boolean isSuspicious(LogEntry log, List<LogEntry> accountLogs) {
        if (log.getActionType() == ActionType.WITHDRAW && log.getAmount() > 50000) {
            return true;
        }

        if (log.getActionType() == ActionType.FAILED_LOGIN) {
            int count = 0;
            int size = accountLogs.size();
            for (int i = Math.max(0, size - 5); i < size; i++) {
                if (accountLogs.get(i).getActionType() == ActionType.FAILED_LOGIN) {
                    count++;
                }
            }
            return count > 3;
        }

        return false;
    }
}

class LogManager {
    private int idCounter = 1;
    private final List<LogEntry> allLogs = new ArrayList<>();
    private final Map<String, List<LogEntry>> accountMap = new HashMap<>();
    private final Map<ActionType, List<LogEntry>> actionMap = new HashMap<>();
    private final Deque<LogEntry> recentStack = new ArrayDeque<>();
    private final SuspiciousDetector detector;

    public LogManager(SuspiciousDetector detector) {
        this.detector = detector;
        for (ActionType type : ActionType.values()) {
            actionMap.put(type, new ArrayList<>());
        }
    }

    public LogEntry addLog(String acc, ActionType action, double amount, Status status) {
        LogEntry log = new LogEntry(idCounter++, acc, action, amount, status);

        allLogs.add(log);
        recentStack.push(log);

        accountMap.computeIfAbsent(acc, k -> new ArrayList<>()).add(log);
        actionMap.get(action).add(log);

        return log;
    }

    public List<LogEntry> getLogsByAccount(String acc) {
        return accountMap.getOrDefault(acc, Collections.emptyList());
    }

    public List<LogEntry> getRecentLogs(int n) {
        List<LogEntry> result = new ArrayList<>();
        Iterator<LogEntry> it = recentStack.iterator();
        while (it.hasNext() && result.size() < n) {
            result.add(it.next());
        }
        return result;
    }

    public List<LogEntry> searchByAction(ActionType action) {
        return actionMap.getOrDefault(action, Collections.emptyList());
    }

    public List<LogEntry> detectSuspicious() {
        List<LogEntry> suspicious = new ArrayList<>();
        for (LogEntry log : allLogs) {
            List<LogEntry> accLogs = accountMap.get(log.getAccountNumber());
            if (detector.isSuspicious(log, accLogs)) {
                suspicious.add(log);
            }
        }
        return suspicious;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LogManager manager = new LogManager(new BasicSuspiciousDetector());

        while (true) {
            System.out.println("\n1.Add Log  2.Get Logs by Account  3.Recent Logs  4.Detect Suspicious  5.Search by Action  6.Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Account: ");
                    String acc = sc.next();
                    System.out.print("Action (DEPOSIT/WITHDRAW/TRANSFER/LOGIN/FAILED_LOGIN): ");
                    ActionType action = ActionType.valueOf(sc.next().toUpperCase());
                    double amt = 0;
                    if (action == ActionType.DEPOSIT || action == ActionType.WITHDRAW || action == ActionType.TRANSFER) {
                        System.out.print("Amount: ");
                        amt = sc.nextDouble();
                    }
                    System.out.print("Status (SUCCESS/FAILED): ");
                    Status status = Status.valueOf(sc.next().toUpperCase());

                    LogEntry log = manager.addLog(acc, action, amt, status);
                    System.out.println("Added: " + log);
                    break;

                case 2:
                    System.out.print("Account: ");
                    acc = sc.next();
                    manager.getLogsByAccount(acc).forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("N: ");
                    int n = sc.nextInt();
                    manager.getRecentLogs(n).forEach(System.out::println);
                    break;

                case 4:
                    List<LogEntry> suspicious = manager.detectSuspicious();
                    if (suspicious.isEmpty()) System.out.println("No suspicious logs");
                    else suspicious.forEach(System.out::println);
                    break;

                case 5:
                    System.out.print("Action Type: ");
                    action = ActionType.valueOf(sc.next().toUpperCase());
                    manager.searchByAction(action).forEach(System.out::println);
                    break;

                case 6:
                    sc.close();
                    return;
            }
            
        }
    }
}
