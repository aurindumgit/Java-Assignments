package Heap_16_Feb;

// import java.util.*;

class MinHeap {
    private int[] heap;
    private int size;
    private int capacity;

    MinHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void insert(int value) {
        if (size == capacity) {
            System.out.println("Heap is full");
            return;
        }

        heap[size] = value;
        size++;
        heapifyUp(size - 1);
    }

    private void heapifyUp(int index) {
        while (index > 0 && heap[parent(index)] > heap[index]) {
            swap(parent(index), index);
            index = parent(index);
        }
    }

    public int extractMin() {
        if (size <= 0) {
            System.out.println("Heap is empty");
            return -1;
        }

        if (size == 1) {
            size--;
            return heap[0];
        }

        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);

        return root;
    }

    private void heapifyDown(int index) {
        int smallest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < size && heap[left] < heap[smallest])
            smallest = left;

        if (right < size && heap[right] < heap[smallest])
            smallest = right;

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    public void decreaseKey(int index, int newValue) {
        if (index >= size) return;

        heap[index] = newValue;
        heapifyUp(index);
    }

    public void delete(int index) {
        decreaseKey(index, Integer.MIN_VALUE);
        extractMin();
    }

    public void printHeap() {
        for (int i = 0; i < size; i++)
            System.out.print(heap[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        MinHeap heap = new MinHeap(10);

        System.out.println("Insert elements:");
        heap.insert(10);
        heap.insert(5);
        heap.insert(20);
        heap.insert(2);
        heap.insert(15);
        heap.printHeap();

        System.out.println("\nExtract Min:");
        System.out.println(heap.extractMin());
        heap.printHeap();

        System.out.println("\nDecrease Key (index 2 → value 1):");
        heap.decreaseKey(2, 1);
        heap.printHeap();

        System.out.println("\nDelete element at index 3:");
        heap.delete(3);
        heap.printHeap();

        System.out.println("\nExtract Min again:");
        System.out.println(heap.extractMin());
        heap.printHeap();
    }
}
