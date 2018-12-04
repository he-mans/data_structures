import java.util.*;

class pqueue
{
	int max_priorities = 5;
	int rear[] = new int[max_priorities];
	int front[] = new int[max_priorities];
	int queue[][] = new int[max_priorities][];
	int size;
	pqueue(int size)
	{
		this.size = size;
		for(int i=0; i<max_priorities;i++)
		{
			rear[i]=0;
			front[i]=0;
			queue[i] = new int[this.size];
		}
	} 

	int remove()
	{
		int i=0;
		for(i=0;i<this.max_priorities;i++)
		{
			if(this.rear[i] == this.front[i])
			{
				continue;
			}
			else
			{
				break;
			}
		}
		if (i>=this.max_priorities)
		{
			System.out.println("underflow");
			return -1;
		}
		int item_index = this.front[i];
		int item = this.queue[i][item_index];
		this.front[i] = (item_index+1)%this.size;
		return item;
	}

	void add(int value, int priority)
	{
		if(this.front[priority] == this.rear[priority]+1)
		{
			System.out.println("overflow");
			return;
		}
		if(this.rear[priority] == this.size && this.front[priority] == 0)
		{
			System.out.println("overflow");
			return;
		}
		else if(this.rear[priority] == this.size && this.front[priority] != 0)
		{
			this.rear[priority] = 0 ;
		}
		int rear_index = this.rear[priority];
		this.queue[priority][rear_index] = value;
		this.rear[priority] = rear_index+1;
		return;
	}	
}

class implement
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("enter size of queue");
		pqueue q = new pqueue(sc.nextInt());
		System.out.println("\nenter 1 to add item to queue\n2 to remove\n3 to exit\n");
		int choice = sc.nextInt();
		while(choice!=3)
		{
			switch(choice)
			{
				case 1:{
					System.out.println("\nenter number and priority (max priority = 5)");
					q.add(sc.nextInt(),sc.nextInt());
					break;
				}

				case 2:{
					int item = q.remove();
					System.out.println("item removed = "+item+"\n");
					break;
				}
			}
			System.out.println("\nenter 1 to add item to queue\n2 to remove\n3 to exit\n");
			choice = sc.nextInt();
		}

	}
}
