package com.learn.sort;

public class SortLearn {
	
	static int arr[] = {4,55,-1,-255,33,99,55,4,31};
	
	/**
	 * 插入排序，其实和按顺序整理扑克牌是一个原理
	 * 假设扑克牌是    K , A , Q ,7 , 9
	 * 每次拿当前这张牌和前面的牌比较，插入到合适的位置
	 * 第一趟 拿A与K 比较  ，维持原序，得到  [K, A ] ,Q , 7 , 9
	 * 第二趟 拿 Q 与A比较，交换一次，然后继续与K比较，再次交换 ， 得到[Q, K ,A] , 7 , 9 
	 * 第三趟 拿 7 与 A比较，交换一次，然后与K比较，再次交换，再与Q比较，再次交换，得到[7, Q , K , A]， 9
	 * 第四趟 拿 9 与 A比较，交换一次，然后与K比较，再次交换，
	 * 再与Q比较，再次交换，再与7比较，不交换，得到最终序列 [7， 9 ,Q, K ,A] （有序）
	 * 
	 * 任何元素的比较是相同的原理
	 * 
	 */
	 static void insertSort(int arr[]){
		 System.out.println("------------------insertSort---------------------");
		 //外层为比较的趟数  N-1
		 for (int i = 1; i < arr.length; i++) {
			 //第一趟直接从 下标1开始
			 //后面第几趟就从第几个元素开始向前比较,比较 j -1次
			 int j = i;
			 int cur = arr[j];
			 //拿当前这个数字往前扫描，找到一个不大于这个数字的就停下来
			 while (j > 0 && cur < arr[j-1]) {
				arr[j] = arr[j-1]; //扫描到的数字比当前数字大，那把扫描到的数字往后挪
				j--;
			}
			 arr[j] = cur; //这个数字在前面的有序列表中找到了位置插进去
		}
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			res.append(arr[i] + " ");
		}
		System.out.println(res);
	 }
	 
	 /**
	  * 通用的插入排序，但是不适用于基本类型，因为基本类型没有compareTo
	  * 这里的唯一区别就是比较大小，如果是C语言，这里用函数指针就可以实现，代码不会重复
	  */
	 static <T extends Comparable<? super T>> 
	 void genericInsertSort(T []arr){
		 System.out.println("---------------genericInsertSort------------");
		 for (int i = 1; i < arr.length; i++) {
			 //第一趟直接从 下标1开始
			 //后面第几趟就从第几个元素开始向前比较,比较 j -1次
			 int j = i;
			 T cur = arr[j];
			 //拿当前这个数字往前扫描，找到一个不大于这个数字的就停下来
			 while (j > 0 && cur.compareTo(arr[j-1]) < 0) {
				arr[j] = arr[j-1]; //扫描到的数字比当前数字大，那把扫描到的数字往后挪
				j--;
			}
			 arr[j] = cur; //这个数字在前面的有序列表中找到了位置插进去
		}
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			res.append(arr[i] + " ");
		}
		System.out.println(res);
	 }
	 
	 
	 public static void main(String[] args) {
		insertSort(arr);
		Double testDouble[] = {4.5, 3.4,-0.9,0.08,433.2};
		genericInsertSort(testDouble);
	}
}
