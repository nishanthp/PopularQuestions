/**
 * Copyright 2016 Hewlett-Packard Development Company, L.P.
 */
package practise;


/**
 * TODO Administrator (May 8, 2016) - Insert a description of this type.
 *
 * @author Administrator
 */
public class QuickSort {

    /**
     *
     */


    public QuickSort() {

    }

    public void quickSort(final int a[],
                          final int left,
                          final int right) {
        final int index = partition(a, left, right);
        if (left < index - 1) {
            quickSort(a, left, index - 1);
        }
        if (index < right) {
            quickSort(a, index, right);
        }

    }

    public int partition(final int[] a,
                         int left,
                         int right) {

        final int pivot = a[(left + right) / 2];
        while (left < right) {
            while (a[left] < pivot) {
                left++;
            }
            while (a[right] > pivot) {
                right--;
            }
            swap(a, left, right);
            left++;
            right--;
        }
        return left;
    }

    /**
     * TODO Administrator (May 8, 2016) - Insert a description of what this method does.
     *
     * @param a2
     * @param left2
     * @param right2
     */
    private void swap(final int[] a2,
                      final int left2,
                      final int right2) {
        final int temp = a2[left2];
        a2[left2] = a2[right2];
        a2[right2] = temp;

    }
}
