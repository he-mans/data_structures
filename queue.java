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
			rear[i]=-1;
			front[i]=-1;
			queue[i] = new int[size];
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
		this.front[i] = (item_index+1)%this.size;
		int item = this.queue[i][item_index+1];
		return item;
	}

	void add(int value, int priority)
	{
		if(this.front[priority] == (this.rear[priority]+1)%this.size|| (this.front[priority]==-1 && this.rear[priority]==this.size-1))
		{
			System.out.println("overflow");
			return;
		}
		int rear_index = this.rear[priority];
		this.rear[priority] = (rear_index+1)%this.size;
		this.queue[priority][rear_index+1] = value;
		return;
	}	
}

class implement
{
	public static void main(String args[])
	{
		pqueue q = new pqueue(5);
		q.add(4,1);
		q.add(4,1);
		q.add(4,1);
		q.add(4,1);
		q.add(4,1);
		//q.add(4,1);
		q.remove();
		q.remove();
		q.remove();
		q.remove();
		q.remove();
		q.remove();
		//q.remove();

		//System.out.println(q.remove(1));
	}
}
