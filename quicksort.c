#include<stdio.h>
#include<stdlib.h>

int *quicksort(int *arr, int size)
{
	//dividing array until size of array is >1
	//if size == 1 we will just return the
	//array received as it cannot be divided further
	if (size>1)
	{
		int pivot = arr[0];
		
		//creating two ararys which will store all the values smaller than pivot in main array
		//and all the values higher than the pivot
		int *smaller_values_array = (int *)malloc(size*sizeof(int));
		int *higher_values_array = (int *)malloc(size*sizeof(int));
		
		//initializing their sizes as 0 as there is no element in them as of now
		int i,size_smaller_array=0,size_higher_array=0;
		
		//feedign values in respective arrays
		for(i=0,size_smaller_array=0,size_higher_array=0 ; i<size ; i++)
		{
			if(arr[i]<pivot)
			{
				smaller_values_array[size_smaller_array]=arr[i];
				//increasing sizes as feeding values to array
				size_smaller_array++;
			}
			else if(arr[i]>pivot)
			{
				higher_values_array[size_higher_array]=arr[i];
				size_higher_array++;
			}
		}

		//recursively calling quicksort function on both smaller values array and higher values
		//array to sort them
		int *smaller_values_sorted_array = quicksort(smaller_values_array,size_smaller_array);
		int *higher_values_sorted_array = quicksort(higher_values_array,size_higher_array);

		//creating sorted array which will sotre sorted version of our main array
		int *sorted_array = (int *)malloc(size*sizeof(int));
		int j;
	
		//storing all values smaller than pivot from sorted smaller values array
		for(i=0;i<size_smaller_array;i++)
		{
			sorted_array[i] = smaller_values_sorted_array[i];
		}
		
		//now since all values smaller than pivot are already in sorted array
		//storting pivout
		sorted_array[i] = pivot;

		//now storing all values higher than pivot in sorted manner
		for(++i,j=0;j<size_higher_array;i++,j++)
		{
			sorted_array[i] = higher_values_sorted_array[j];
		}

		//returning sorted array 
		return sorted_array;
	}

	else
	{
		//returning array as it cannot be divided further
		//since size==1
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
