package io.github.zhengyhn.practice.bubble_sort;

public class BubbleSort {
    public void sort(int[] arr) {
        int size = arr.length;
        for (int i = size - 1; i >= 0; --i) {
            boolean swap = false;
            for (int j = 0; j < i; ++j) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 4, 3, 1};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(arr);
        for (int i: arr) {
            System.out.println(i);
        }
    }
}
