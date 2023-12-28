import java.util.ArrayList;

class RedBlackTree {
    public static void main(String[] args) {
        RedBlackCustomerQueue customerQueue = new RedBlackCustomerQueue();

        // Enqueue customers
        customerQueue.enqueue(3, "Hammid");
        customerQueue.enqueue(1, "Hamzah");
        customerQueue.enqueue(4, "Hanif");
        customerQueue.enqueue(2, "Hambali");

        // Display queue status
        ArrayList<String> queueStatus = customerQueue.getQueueStatus();
        System.out.println("Customer Queue Status:");
        for (String status : queueStatus) {
            System.out.println(status);
        }
    }
}