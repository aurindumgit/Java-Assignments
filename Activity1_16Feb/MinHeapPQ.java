package Activity1_16Feb;
import java.util.PriorityQueue;
import java.util.Random;

class MinHeapPQ {

    public static void main(String[] args) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        Random rand = new Random();

        System.out.println("Inserted Numbers:");

        for (int i = 0; i < 10; i++) {
            int num = rand.nextInt(100);
            minHeap.add(num);
            System.out.print(num + " ");
        }

        System.out.println("\n\nSorted Order (Min-Heap):");

        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }
    }
}

// //Output:
// Inserted Numbers:
// 71 35 57 92 59 15 45 8 58 4 

// Sorted Order (Min-Heap):
// 4 8 15 35 45 57 58 59 71 92
