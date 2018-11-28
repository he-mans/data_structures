import java.util.*;

class node
{
	String name;
	long number;
	node next;
	node(String name,long number)
	{
		this.name = name;
		this.number = number;
		this.next = null;
	}
}

class linked_list
{
	node head;
	
	linked_list()
	{
		this.head=null;
	}

	void append(String name,long number)
	{
		node current = this.head;
		if (current==null)
		{
			this.head = new node(name,number);
		}
		else
		{
			for(int i=0;current.next!=null;i++)
			{
				current = current.next;
			}
			current.next = new node(name,number);
		}
	}
	int remove(String name)
	{
		node current = this.head;
		node prev = current;
		int i;
		for(i=0;current!=null && !current.name.equals(name);i++)
		{
			prev = current;
			current = current.next;
		}
		if(current==null)
		{
			return -1;
		}
		if (i==0)
		{
			this.head = this.head.next;
			return 1;
		}
		prev = current.next;
		return 1;
	}

	long search(String name)
	{
		node current = this.head;
		while(current!=null && !current.name.equals(name))
		{
			current = current.next;
		}
		if(current==null)
		{
			return -1L;
		}
		return current.number;
		
	}
}

class hash_table
{
	int size = 5;
	linked_list mappings[][] = new linked_list[2*size][size];

	hash_table()
	{
	
		for(int i=0;i<(this.size*2);i++)
		{
			for(int j=0;j<(this.size);j++)
			{
				mappings[i][j] = new linked_list();
			}
		}
	}
	
	int convert_to_key(String name)[]
	{
		int keys[] = new int[2];
		keys[0] = name.length()%(2*size);
		keys[1] = (int)name.charAt(0)%(size);
		return keys;
	}

	void add_contact(String name, long number)
	{
		int keys[] = convert_to_key(name);
		this.mappings[keys[0]][keys[1]].append(name,number);
		System.out.println("contact added\n");
	}

	void remove_contact(String name)
	{
		int keys[] = convert_to_key(name);
		int status = this.mappings[keys[0]][keys[1]].remove(name);
		if (status==-1)
		{
			System.out.println("no contact named "+name+" found\n");
		}
		else
		{
			System.out.println("contact sucessfully removed\n");
		}
	}
	
	void search_contact(String name)
	{
		int keys[] = convert_to_key(name);
		long number = this.mappings[keys[0]][keys[1]].search(name);
		if (number!=-1L)
		{
			System.out.println("name: "+name);
			System.out.println("number: "+number);	
		}
		else
		{
			System.out.println("no contact named "+name+" found");
		}
	}
}
class phonebook
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);

		hash_table phonebook = new hash_table();
		System.out.println("press 1 to add new conact");
		System.out.println("press 2 a remove conact");
		System.out.println("press 3 search conact");
		System.out.println("press 4 to exit");

		String name;
		long number;

		int choice = sc.nextInt();
		while(choice!=4)
		{
			if (choice==4) break;
			
			switch(choice)
			{
				case 1:{
					System.out.println("Enter name");
					name = sc.next();
					System.out.println("Enter phone number");
					number = sc.nextLong();
					phonebook.add_contact(name,number);
					break;
				}
				case 2:{
					System.out.println("Enter name of contact to be removed");
					name = sc.next();
					phonebook.remove_contact(name);
					break;
				}
				case 3:{ 
					System.out.println("Enter name to search");
					name = sc.next();
					phonebook.search_contact(name);
					break;
				}
			}
			System.out.println("\nEnter choice");
			choice = sc.nextInt();
		}
	}
}