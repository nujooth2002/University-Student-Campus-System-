public class ActionHistoryStack {

    private static class ActionNode {
        String action;
        ActionNode next;

        ActionNode(String action, ActionNode next) {
            this.action = action;
            this.next = next;
        }
    }

    private ActionNode top;

    public void push(String action) {
        if (action == null || action.trim().isEmpty()) {
            return;
        }

        top = new ActionNode(action, top);
    }

    public String pop() {
        if (top == null) {
            return null;
        }

        String action = top.action;
        top = top.next;
        return action;
    }

    public void displayRecentActions() {
        if (top == null) {
            System.out.println("No recent actions.");
            return;
        }

        ActionNode current = top;
        while (current != null) {
            System.out.println(current.action);
            current = current.next;
        }
    }
}