package com.example.algorithmmodel;

/**
 * DirectSort
 *
 * @author Gene
 * @version 1.0
 * @description
 * @date 2023/2/3 9:55
 */
public class DirectSort {


    public static  void getDirectSort(int[] a) {
        for (int i = 0; i < a.length-1; i++) {
            // 标记比较到哪个位置
            int end = i;
            // 记录end后的一个元素, 用于与前面的所有元素比较
            int tmp = a[end + 1];
            while (end >= 0) {
                if (tmp < a[end]) {
                    a[end + 1] = a[end];
                    end--;
                } else {
                    break;
                }
            }
            a[end + 1] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] a = {2, 5, 9, 1, 7, 4, 2, 10, 6, 8};
        getDirectSort(a);
        System.out.println(a);
    }
}
 