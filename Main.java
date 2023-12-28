import java.util.ArrayList;

class RedBlackCustomerQueue {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class CustomerNode {
        int queueNumber;
        String customerName;
        boolean color;
        CustomerNode left, right;

        CustomerNode(int queueNumber, String customerName) {
            this.queueNumber = queueNumber;
            this.customerName = customerName;
            this.color = RED;
        }
    }

    //metode2
    private CustomerNode root;

    private CustomerNode rotateLeft(CustomerNode h) {
        CustomerNode x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private CustomerNode rotateRight(CustomerNode h) {
        CustomerNode x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(CustomerNode h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    //enqueue
    public void enqueue(int queueNumber, String customerName) {
        root = enqueue(root, queueNumber, customerName);
        root.color = BLACK;
    }

    private CustomerNode enqueue(CustomerNode h, int queueNumber, String customerName) {
        if (h == null) return new CustomerNode(queueNumber, customerName);

        int cmp = Integer.compare(queueNumber, h.queueNumber);

        if (cmp < 0) {
            h.left = enqueue(h.left, queueNumber, customerName);
        } else if (cmp > 0) {
            h.right = enqueue(h.right, queueNumber, customerName);
        } else {
            // Handle jika nomor antrian sudah ada
            // Bisa ditambahkan logika spesifik atau abaikan
            return h;
        }

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        return h;
    }

    //isred
    private boolean isRed(CustomerNode x) {
        if (x == null) return false;
        return x.color == RED;
    }

    //getqueuestatus
    public ArrayList<String> getQueueStatus() {
        ArrayList<String> queueStatus = new ArrayList<>();
        inOrderTraversal(root, queueStatus);
        return queueStatus;
    }

    private void inOrderTraversal(CustomerNode x, ArrayList<String> result) {
        if (x != null) {
            inOrderTraversal(x.left, result);
            result.add("Queue Number: " + x.queueNumber + ", Customer Name: " + x.customerName);
            inOrderTraversal(x.right, result);
        }
    }
}
