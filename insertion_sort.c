#include<stdio.h>
int main()
{
	printf("enter array size\n");
	int size,iterator,temp,temp_iterator;
	scanf("%d",&size);
	
	printf("enter elements of array\n");
	int arr[size];
	for(iterator=0;iterator<size;iterator++)
	{
		scanf("%d",&arr[iterator]);
	}

	for(iterator=0;iterator<size-1;iterator++)
	{
		if (arr[iterator]>arr[iterator+1])
		{
			temp_iterator = iterator;
			temp = arr[iterator+1];
			while(arr[temp_iterator]>arr[temp_iterator+1] && temp_iterator>=0)
			{
				arr[temp_iterator+1] = arr[temp_iterator];
				arr[temp_iterator] = temp;
				temp_iterator--;
			}
		}
	}

	printf("\nsorted array is:\n");
	for(iterator=0;iterator<size;iterator++)
	{
		printf("%d\n",arr[iterator]);
	}
}