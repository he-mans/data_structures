#include<stdio.h>
#include<stdlib.h>

int *merge(int *a, int *b, int *c,int size_a, int size_b)
{
	int loop_a,loop_b,loop_c;
	for(loop_a=0, loop_b=0, loop_c=0 ; loop_a<size_a && loop_b<size_b ; loop_c++)
	{
		if(a[loop_a]<b[loop_b])
		{
			c[loop_c] = a[loop_a];
			loop_a++;
		}
		else
		{
			c[loop_c] = b[loop_b];
			loop_b++;
		}
	}

	while(loop_a<size_a)
	{
		c[loop_c] = a[loop_a];
		loop_c++;
		loop_a++;
	}

	while(loop_b<size_b)
	{
		c[loop_c] = b[loop_b];
		loop_c++;
		loop_b++;
	}
	
	return c;
}
void *merge_sort(int *arr, int size)
{
	if(size>1)
	{
		int k;
		int size_left_array = size/2;
		int size_right_array = size-size/2;
		int *left_array = (int *)malloc(size_left_array*sizeof(int));
		int *right_array = (int *)malloc(size_right_array*sizeof(int));
		int i,j;
		for(i=0;i<size_left_array;i++)
		{
			left_array[i] = arr[i];
		}

		
		for(i=size_left_array,j=0;i<size;i++,j++)
		{
			right_array[j] = arr[i];	
		}

		//for(k=0;k<size_right_array;k++)
		//{
		//printf("%d\n",*(right_array+k));
		//}
		int *left_sorted_array = merge_sort(left_array,size_left_array);
		int *right_sorted_array = merge_sort(right_array,size_right_array);
		int *merged_array = (int *)malloc((size)*sizeof(int));
		return merge(left_sorted_array,right_sorted_array ,merged_array, size_left_array , size_right_array);
	}
	else
	{
		return arr;
	}
}

int main()
{
	int size;
	int i;
	printf("enter size of array\n");
	scanf("%d",&size);
	int *arr = (int *)malloc(size*sizeof(int));
	printf("enter vlues of array\n");
	for(i=0;i<size;i++)
	{
		scanf("%d",arr+i);
	}
	
	arr = merge_sort(arr,size);
	printf("sorted array is\n");
	for(i=0;i<size;i++)
	{
		printf("%d\n",*(arr+i));
	}
}