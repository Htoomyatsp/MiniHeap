public class MinHeap {

    // Instance variables
    private int[] arr;           // Array to store heap elements
    private int max_size;        // Maximum capacity of the heap
    private int curr_size;       // Current number of elements in the heap

    // Constructor to initialize the heap with a given capacity
    public MinHeap(int capacity) {
        curr_size = 0;            // Initialize current size to 0
        max_size = capacity;      // Set the maximum capacity
        arr = new int[capacity];  // Initialize the array with the specified capacity
    }

    // Helper method to maintain the min-heap property after removal
    private void MinHeapp(int i) {
        int l = 2 * i + 1;         // Calculate left child index
        int r = 2 * i + 2;         // Calculate right child index
        int smallest = i;          // Assume the current index is the smallest

        // Compare with left child
        if (l < curr_size && arr[l] < arr[i])
            smallest = l;

        // Compare with right child
        if (r < curr_size && arr[r] < arr[smallest])
            smallest = r;

        // Swap if needed and recursively call MinHeapp for the swapped position
        if (smallest != i) {
            int temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;
            MinHeapp(smallest);
        }
    }

    // Remove and return the minimum element
    public int removeMin() {
        if (curr_size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        // Handle base case when there's only one element in the heap
        if (curr_size == 1) {
            curr_size--;         // Decrease the size
            return arr[0];       // Return the root (minimum element)
        }

        int root = arr[0];        // Store the minimum element
        arr[0] = arr[curr_size - 1]; // Replace root with the last element
        curr_size--;             // Decrease the size
        MinHeapp(0);             // Restore the min-heap property
        return root;             // Return the minimum element
    }

    // Return the minimum key from the min heap
    public int getMin() {
        if (curr_size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        return arr[0];            // Return the root (minimum element)
    }

    // Insert a new key
    public void insertKey(int k) {
        if (curr_size == max_size) {
            throw new IllegalStateException("Overflow: Could not insert the key");
        }

        curr_size++;              // Increase the size
        int i = curr_size - 1;
        arr[i] = k;               // Insert the new key at the end

        // Restore the min-heap property by comparing with the parent
        int parent = (i - 1) / 2;
        while (i != 0 && arr[parent] > arr[i]) {
            int temp = arr[i];
            arr[i] = arr[parent];
            arr[parent] = temp;
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    // Return the size of the min heap
    public int size() {
        return curr_size;         // Return the current size
    }

    // Check if the min heap is empty
    public boolean isEmpty() {
        return curr_size == 0;    // Check if the current size is 0
    }

    // Main method for testing
    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);
        minHeap.insertKey(14);
        minHeap.insertKey(7);
        minHeap.insertKey(29);

        System.out.println("Minimum element: " + minHeap.getMin());
        System.out.println("Removed minimum element: " + minHeap.removeMin());
        System.out.println("Minimum element after removal: " + minHeap.getMin());
        System.out.println("Is MinHeap empty? " + (minHeap.isEmpty() ? "Yes" : "No"));
        System.out.println("Size of MinHeap: " + minHeap.size());
    }
}
