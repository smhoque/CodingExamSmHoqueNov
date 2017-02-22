package algorithm;

public class Sort {

    long executionTime = 0;
    /*
     * Please implement all the sorting algorithm. Feel free to add helping methods.
	 * Store all the sorted data into one of the databases.
	 */

    public void quicksort(int[] array) {
        int left = 0;
        int right = 1;

        if (left >= right) {
            return;
        }
        int pivot = array[(left + right) / 2];
        int index = partition(array, left, right, pivot);
        quicksort(array);
        quicksort(array);
    }

    public int partition(int[] array, int left, int right, int pivot) {
        final long startTime = System.currentTimeMillis();
        while (left <= right) {
            while (array[left] < pivot) {
                left++;
            }
            while (array[right] > pivot) {
                right--;
            }
            if (left <= right) {
                //swap(array, left, right);
                left++;
                right--;

            }

        }
        final long endTime = System.currentTimeMillis();
        final long executionTime = endTime - startTime;
        this.executionTime = executionTime;
        return left;

    }

    public int[] selectionSort(int[] array) {
        final long startTime = System.currentTimeMillis();

        //int[] list = array;

        int[] list = array.clone();


        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
            for (int n = 0; n < array.length; n++) {
                System.out.println(array[n]);

            }

        }

        final long endTime = System.currentTimeMillis();
        final long executionTime = endTime - startTime;
        this.executionTime = executionTime;
        return list;
    }

    public int[] insertionSort(int[] array) {
        final long startTime = System.currentTimeMillis();

        int[] list = array;
        int temp = 0;
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }

        for (int n = 0; n < array.length; n++) {
            System.out.println(array[n]);
        }
        final long endTime = System.currentTimeMillis();
        final long executionTime = endTime - startTime;
        this.executionTime = executionTime;
        return list;


    }

    public int[] bubbleSort(int[] array) {
        final long startTime = System.currentTimeMillis();
        int[] list = array;

        for (int i = array.length; i >= 0; i--) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }

        }
        for (int n = 0; n < array.length; n++) {
            System.out.println(array[n]);
        }

        return list;
    }

    public int[] mergeSort(int[] array) {
        final long startTime = System.currentTimeMillis();
        //int[] list = array;
        //implement here
        if (array.length <= 1) ;
        int[] first = new int[array.length / 2];
        int[] second = new int[array.length - first.length];
        for (int i = 0; i < array.length; i++) {
            first[i] = array[i];
            for (int j = 0; i < second.length; j++) {
                second[j] = array[first.length + 1];
            }
            Sort firstSorter = new Sort();
            Sort secondSort = new Sort();
            for (int n = 0; n < array.length; n++) {
                System.out.println(array[n]);
            }
        }

        final long endTime = System.currentTimeMillis();
        final long executionTime = endTime - startTime;
        this.executionTime = executionTime;


        return first;
    }
    //public int [] quickSort(int [] array){
    //    int [] list = array;
    //implement here


    //   return list;
    //}


    public int[] heapSort(int[] array) {


        int i;
        int arr[] = {1, 3, 4, 5, 2};
        for (i = 0; i < arr.length; i++)
            System.out.print(" " + arr[i]);
        for (i = arr.length; i > 1; i--) {
            SortHeap(arr, i - 1);
        }

        for (i = 0; i < arr.length; i++)
            return arr;


        return array;
    }

    public void SortHeap(int array[], int arr_ubound) {
        final long startTime = System.currentTimeMillis();
        int i, o;
        int lChild, rChild, mChild, root, temp;
        root = (arr_ubound - 1) / 2;

        for (o = root; o >= 0; o--) {
            for (i = root; i >= 0; i--) {
                lChild = (2 * i) + 1;
                rChild = (2 * i) + 2;
                if ((lChild <= arr_ubound) && (rChild <= arr_ubound)) {
                    if (array[rChild] >= array[lChild])
                        mChild = rChild;
                    else
                        mChild = lChild;
                } else {
                    if (rChild > arr_ubound)
                        mChild = lChild;
                    else
                        mChild = rChild;
                }

                if (array[i] < array[mChild]) {
                    temp = array[i];
                    array[i] = array[mChild];
                    array[mChild] = temp;
                }
            }
        }
        temp = array[0];
        array[0] = array[arr_ubound];
        array[arr_ubound] = temp;

        final long endTime = System.currentTimeMillis();
        final long executionTime = endTime - startTime;
        this.executionTime = executionTime;
        return;

    }

    public void bucketsort(int[] array, int startTime) {


        int[] bucket = new int[(int) (startTime + 1)];

        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = 0;
        }

        for (int i = 0; i < array.length; i++) {
            bucket[array[i]]++;
        }

        int outPos = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                array[outPos++] = i;
            }
        }

    }

    public void shellsort(int[] array) {
        final long startTime = System.currentTimeMillis();
        int inner, outer;

        int h = 1;
        while (h <= array.length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            for (outer = h; outer < array.length; outer++) {
                h = array[outer];
                inner = outer;

                while (inner > h - 1 && array[inner - h] >= h) {
                    array[inner] = array[inner - h];
                    inner -= h;
                }
                array[inner] = h;
            }
            h = (h - 1) / 3;
        }

        final long endTime = System.currentTimeMillis();
        final long executionTime = endTime - startTime;
        this.executionTime = executionTime;
    }
}
