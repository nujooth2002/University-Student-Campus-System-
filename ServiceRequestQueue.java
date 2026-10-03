

public class ServiceRequestQueue {

    private static class RequestNode {
        String request;
        RequestNode next;

        RequestNode(String request) {
            this.request = request;
        }
    }

    private RequestNode front;
    private RequestNode rear;

    public boolean addRequest(String request) {
        if (request == null || request.trim().isEmpty()) {
            return false;
        }

        RequestNode newNode = new RequestNode(request);

        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        return true;
    }

    public String processNextRequest() {
        if (front == null) {
            return null;
        }

        String request = front.request;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        return request;
    }

    public void displayRequests() {
        if (front == null) {
            System.out.println("No service requests waiting.");
            return;
        }

        RequestNode current = front;
        while (current != null) {
            System.out.println(current.request);
            current = current.next;
        }
    }
}