package sort;

import java.util.Random;

public class Sort {
    
	private static void swap(int[]a,int i,int j){
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	
	public static void print(int[]a){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	
	public static void print(int[]a,int m,int n){
		for(int i=m;i<=n;i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	
/**
 * 冒泡排序BubbleSort，一种交换排序，基本思想是两两比较相邻记录的关键字，
 * 如果逆序则交换，知道没有逆序的记录为止。
 */	
	
	/*
	 * 最简单的交换排序，每一个关键字依次和后面的关键字比较，如果逆序则交换。
	 */
	public static void bubbleSort1(int[]a){
		int i,j;
		for(i=0;i<a.length;i++){
			for(j=i+1;j<a.length;j++){
				if(a[i]>a[j]){
					swap(a,i,j); //如果逆序则交换
				}
			}
		}
	}
	
	/*
	 * 从最后一个关键字开始两两比较，如逆序则交换
	 */
	public static void bubbleSort2(int[]a){
		int i,j;
		for(i=0;i<a.length;i++){
			for(j=a.length-1;j>i;j--){
				if(a[j]<a[j-1]){
					swap(a,j-1,j);
				}
			}
		}
	}
	
	/*
	 * 加入标记flag减小比较交换次数，当一轮比较结束还没有数据交换则已排好序。
	 */
	public static void bubbleSort3(int[]a){
		int i,j;
		boolean flag=true;
		for(i=0;i<a.length&&flag;i++){
			flag=false;
			for(j=a.length-1;j>i;j--){
				if(a[j]<a[j-1]){
					swap(a,j-1,j);
					flag=true;
				}
			}
		}
	}
	
	/*
	 * 简单选择排序,在第i轮，通过n－i次关键字间的比较，
	 * 从n－i＋1个记录中选出关键字最小的记录，并和第i（1≤i≤n）个记录交换
	 */
	public static void simpleSelectSort(int[]a){
		int i,j,min;//min记录每一轮最小值的位置
		for(i=0;i<a.length-1;i++){
			min=i;
			for(j=i+1;j<a.length;j++){
				if(a[min]>a[j]){
					min=j;//标记较小值的位置，最终得到最小值的位置
				}
			}
			if(min!=i){
				swap(a,i,min);//如果i位置不是最小值，则交换
			}
		}
	}
	
	/*
	 * 直接插入排序，基本思想是将记录插入到已经排好序的数组中
	 */
	public static void insertSort(int[]a){
		int i,j,temp;
		for(i=1;i<a.length;i++){
			if(a[i]<a[i-1]){//逆序，需要进行插入排序
				temp=a[i];//哨兵
				a[i]=a[i-1];
				for(j=i-2;j>=0&&temp<a[j];j--){					
					a[j+1]=a[j];//如逆序，则后移					
				}
				a[j+1]=temp;
			}
		}		
	}
	
	/*
	 * 希尔排序，将序列以某一增量分割成多个子序列，然后进行直接插入排序，
	 * 重复直到增量为1
	 */
	public static void shellSort(int[]a){
		int i,j,temp;
		int increment=a.length;//序列增量初值
		do{
			increment=increment/3+1;
			for(i=increment;i<a.length;i++){
				if(a[i]<a[i-increment]){
					temp=a[i];
					a[i]=a[i-increment];
					for(j=i-2*increment;j>=0&&temp<a[j];j-=increment){
						a[j+increment]=a[j];
					}
					a[j+increment]=temp;
				}
			}
		}while(increment>1);
	}
	
	/*
	 * 堆排序，基本思想是将待排序序列构造成一个大顶堆，序列的最大值就是堆的根节点，
	 * 然后将根节点与堆的末尾元素交换，再将末尾元素外地n-1个元素重新构建大顶堆，
	 * 就得到了n个元素的次最大值，反复执行得到有序序列。
	 */
	public static void heapSort(int[]a){
		int i;
		for(i=a.length/2-1;i>=0;i--){
			heapify(a,i,a.length);//构建大顶堆
		}
		for(i=a.length-1;i>0;i--){
			swap(a,i,0);
			heapify(a,0,i);
		}
	}
	
	private static void heapify(int[]a,int i,int heapsize){
		while(true){
			int l=(i<<1)+1;
			int r=l+1;
			int max=i;
			if(l<heapsize&&a[max]<a[l])
				max=l;
			if(r<heapsize&&a[max]<a[r])
				max=r;
			if(i!=max){
				swap(a,i,max);
				i=max;
			}else break;
		}
	}
	
	/*
	 *2路归并排序就是将序列看成是n个长度为1的有序的子序列，然后两两归并，
	 *得到多个个长度为2或1的有序子序列，再两两归并直到序列有序。
	 *其核心是两个有序序列的合并算法，先将序列分割再进行合并。
	 */
    public static void mergeSort(int[]a){
    	mSort(a,0,a.length-1);
    }
    /*
     * 非递归2路归并排序
     */
    public static void mergeSort2(int[]a){
    	int i,low,m,high;
    	for(i=1;i<a.length;i=i*2){//迭代次数
    		low=0;
    		m=low+i-1;
    		high=m+i;
    		while(high<a.length){//当前要合并的序列超界
    			merge(a,low,m,high);
    			low=low+2*i;//下一个待合并序列起始指针
    			m=low+i-1;
    			high=m+i;
    		}    			
    		if(m<a.length-1)//剩下的序列还能进行分割合并
    			merge(a,low,m,a.length-1);   		
    	}
    }
    public static void mSort(int[]a,int s,int t){
    	if(s<t){
    		int m=(s+t)/2;//分割
    		mSort(a,s,m);
    		mSort(a,m+1,t);
    		merge(a,s,m,t);//合并左右部分
    	}
    }
    /*
     * 2路归并排序核心算法，合并a[low...m]和a[m+1...high]
     * low,m,m+1,和high分别为两个要合并序列的边界指针
     */
    public static void merge(int[]a,int low,int m,int high){
    	int i,j,k,l;
    	//i作为a[low...m]的指针,j作为a[m+1...high]的指针，k作为b[low...high]的指针
    	int b[]=new int[high-low+1];//将合并结果暂存到b中
    	for(i=low,j=m+1,k=0;i<=m&&j<=high;k++){
    		if(a[i]<=a[j])
    			b[k]=a[i++];
    		else
    			b[k]=a[j++];
    	}
    	if(i<=m){
    		for(l=i;l<=m;l++)
    			b[k++]=a[l];   		
    	}
    	if(j<=high){
    		for(l=j;l<=high;l++)
    			b[k++]=a[l];
    	}
    	for(l=low;l<=high;l++)
    		a[l]=b[l-low];//合并结果写入a中
    }
    
    /*
     * 快速排序基本思想是通过一趟分割，以某一关键字为枢轴（pivot）将待排序列分割成两个部分，
     * 其中一个部分小于等于枢轴另一个部分大于等于枢轴，这样一次分割就可以将一个关键字排好序，
     * 然后分别对两部分序列继续进行递归分割， 直到序列有序。其核心是partition。
     */    
    public static void quickSort(int[]a,int low,int high){
    	if(low<high){
    		int pivot=partition1(a,low,high);
    		quickSort(a,low,pivot-1);
    		quickSort(a,pivot+1,high);
    	}
    }
    /*
     * 尾递归优化的快速排序
     */
    public static void quickSort1(int[]a,int low,int high){
    	int pivot;
    	while(low<high){
    		pivot=partition1(a,low,high);
    		quickSort1(a,low,pivot-1);
    		low=pivot+1;
    	}
    }
    
    /*
     * 快速排序的核心算法partition，该算法把数组以一点为轴分为左右两个部分，
     * 左边不大于主元，右边不小于主元，则主元就被排好序了。
     * 第一个位置的数作为主元，low和high指向当前需要和主元比较的数，
     * low的左边不大于主元，high的右边不小于主元。
     * 当low<high的时候，不断的依次从high和low位置开始向内部遍历，和主元比较，
     * 找到不满足要求的数，进行移动或者交换使其满足要求，
     * 当循环结束时low=high，此时表示此位置左边数均不大于主元，右边数均不小于主元，
     * 所以low位置就是数组的分割点，再把主元移动到该位置即可。
     */
    public static int partition(int[]a,int low,int high){
    	int pk;
    	pk=a[low];//将主元缓存
    	while(low<high){
    		while(low<high&&pk<=a[high])
    			high--;
    		a[low]=a[high];
    		while(low<high&&pk>=a[low])
    			low++;
    		a[high]=a[low];
    	}
    	a[low]=pk;
    	return low;
    }
   
    public static int partition1(int[]a,int l,int r){
    	int pivotkey=a[l];
    	int pivot=l;
    	while(l<r){
    		while(l<r&&pivotkey<=a[r])
    			r--;
    		while(l<r&&pivotkey>=a[l])
    			l++;
    		if(l<r)
    			swap(a,l,r);  		
    	}
    	swap(a,l,pivot);//l等于r,
    	return l;   	
    }
    /*
     * 最后一个位置作为枢轴
     * a[p...i]<=pivotkey,a[i+1...j-1]>pivotkey,a[j...r-1]随意
     */
    public static int partition2(int[]a,int l,int r){
    	int pivotkey=a[r];
    	int i,j;
    	i=l-1;
    	for(j=l;j<r;j++){
    		if(a[j]<=pivotkey){
    			i++;
    			swap(a,i,j);
    		}    			
    	}
    	swap(a,i+1,r);
    	return i+1;
    }
    public static void randomizedQuickSort(int[]a,int l,int r){
    	if(l<r){
    		int pivot=randomizedPartition(a,l,r);
    		randomizedQuickSort(a,l,pivot-1);
    		randomizedQuickSort(a,pivot+1,r);
    	}
    }
    public static int randomizedPartition(int[]a,int l,int r){
    	Random random=new Random();
    	int p=random.nextInt(r-l+1)+l;
    	swap(a,p,l);
    	return partition(a,l,r);
    }
    /*
     * 查找第k小数算法
     */
    public static int kthSmallestValue(int[]a,int l,int r,int k){
    	if(l==r)
    		return a[l];
    	int pivot=randomizedPartition(a,l,r);
    	int num=pivot-l+1;//l...pivot的数的个数
    	if(num==k)
    		return a[pivot];
    	else if(k<num)
    		return kthSmallestValue(a,l,pivot-1,k);
    	else 
    		return kthSmallestValue(a,pivot+1,r,k-num);
    }
    /*
     * 迭代查找第k小数
     */
    public static int kthSmallestValue2(int[]a,int l,int r,int k){
    	if(l==r)
    		return a[l];
    	int pivot=randomizedPartition(a,l,r);
    	int num=pivot-l+1;
    	while(k!=num){//迭代直到pivot是第k小数的指针
    		if(k<num)
    			pivot=randomizedPartition(a,l,pivot-1);
    		else
    			pivot=randomizedPartition(a,pivot+1,r);
    		num=pivot-l+1;
    	}
    	return a[pivot];
    }
    /*
     * 计数排序,基本思想是对每一个输入元素x，先统计等于x的个数，再确定小于等于x的元素个数，
     * 这样就可以确定x最终排序所在的位置了。
     */
    public static void countingSort(int[]a,int[]b,int k){
    	int i,j;
    	int c[]=new int[k+1];//临时存储区，用于计数
    	for(i=0;i<=k;i++)
    		c[k]=0;
    	for(j=0;j<a.length;j++)
    		c[a[j]]++;//c[k]包含等于k的元素的个数
    	for(i=1;i<=k;i++)
    		c[i]=c[i]+c[i-1];//c[k]包含小于等于k的元素的个数
    	for(j=a.length-1;j>=0;j--){//倒序是为了稳定排序
    		b[c[a[j]]-1]=a[j];//小于等于a[j]的数-1就是a[j]的排序位置
    		c[a[j]]--;
    	}
    }
    /*
     * 基数排序，从最低有效位开始按位排序
     */ 
    public static void radixSort(int[]a,int d,int r){
    	int[] c=new int[1<<r];
    	int[] b=new int[a.length];
    	int base=(1<<r)-1;
    	int shift=0;
    	int i,j;
    	for(i=1;i<=d;i++){
    		for(j=0;j<a.length;j++)
    			c[(a[j]&base)>>shift]++;
    		for(j=1;j<c.length;j++)
    			c[j]=c[j]+c[j-1];
    		for(j=a.length-1;j>=0;j--){
    			b[c[(a[j]&base)>>shift]-1]=a[j];
    			c[(a[j]&base)>>shift]--;
    		}
    		for(j=0;j<a.length;j++)
    			a[j]=b[j];
    		for(j=0;j<c.length;j++)
    			c[j]=0;
    		base=base<<r;
    		shift=shift+r;
    		print(a);
    	}
    }
    
	public static void main(String[]args){
		int a[]={9,1,5,8,3,7,4,6,2};
		int b[]={50,10,90,30,70,40,80,60,20};
		print(a);
		//print(b);
		//bubbleSort1(a);
		//bubbleSort2(a);
		//bubbleSort3(a);
		//simpleSelectSort(a);
		//insertSort(a);
		//shellSort(a);
		//heapSort(a);
		heapSort(b);
		//mergeSort(a);
		//mergeSort2(b);
		//quickSort1(a,0,a.length-1);
		//quickSort1(b,0,b.length-1);
		//countingSort(a,b,9);
		//System.out.println(kthSmallestValue(a,0,a.length-1,4));
		//System.out.println(kthSmallestValue2(a,0,a.length-1,7));
		//radixSort(b,2,6);
		print(b);
		//print(b);
		
	}
}
