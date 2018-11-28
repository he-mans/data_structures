#include<stdio.h>
#include<stdlib.h>

int *quicksort(int *arr, int size)
{
	if (size>1)
	{
		int pivot = arr[0];
		int *smaller_values_array = (int *)malloc(size*sizeof(int));
		int *higher_values_array = (int *)malloc(size*sizeof(int));
		int i,size_smaller_array=0,size_higher_array=0;
		for(i=0,size_smaller_array=0,size_higher_array=0 ; i<size ; i++)
		{
			if(arr[i]<pivot)
			{
				smaller_values_array[size_smaller_array]=arr[i];
				size_smaller_array++;
			}
			else if(arr[i]>pivot)
			{
				higher_values_array[size_higher_array]=arr[i];
				size_higher_array++;
			}
		}

		int *smaller_values_sorted_array = quicksort(smaller_values_array,size_smaller_array);
		int *higher_values_sorted_array = quicksort(higher_values_array,size_higher_array);

		int *sorted_array = (int *)malloc(size*sizeof(int));
		int j;
	
		for(i=0;i<size_smaller_array;i++)
		{
			sorted_array[i] = smaller_values_sorted_array[i];
		}
		sorted_array[i] = pivot;

		for(++i,j=0;j<size_higher_array;i++,j++)
		{
			sorted_array[i] = higher_values_sorted_array[j];
		}

		return sorted_array;
	}

	else
	{
		return arr;
	}
}

int main()
{
	int size,i;
	printf("enter size of array\n");
	scanf("%d",&size);
	int *arr = (int *)malloc(size*sizeof(int));
	printf("enter values in array\n");
	for(i=0;i<size;i++)
	{
		scanf("%d",arr+i);
	}

	arr = quicksort(arr,size);

	printf("sorted array is\n");
	for(i=0;i<size;i++)
	{
		printf("%d\n",*(arr+i));
	}
}