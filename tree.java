class node
{
	int data;
	node left;
	node right;
	node(int data)
	{
		this.left = null;
		this.right = null;
		this.data = data;
	}
}
	
class tree
{
	node root;
	tree()
	{
		this.root = null;
	}
	
	void add_node(int data)
	{
		if (this.root == null)
		{
			this.root = new node(data);
		}
		else
		{
			node root = this.root;
			node parent = root;
			while(root!=null)
			{
				if(data>root.data)
				{
					root = root.right;
				}
				else
				{
					root = root.left;
				}
			}
			if(data>parent.data)
			{
				parent.right = new node(data);
			}
			else
			{
				parent.left = new node(data);
			}
		}
	}

	void traverse_inorder(node root)
	{
		if(root!=null)
		{
			traverse_inorder(root.left);
			System.out.println(root.data);
			traverse_inorder(root.right);
		}
	}

	void traverse_preorder(node root)
	{
		if(root!=null)
		{
			System.out.println(root.data);
			traverse_preorder(root.left);
			traverse_preorder(root.right);
		}
	}
}

class implement
{
	public static void main(String args[])
	{
		tree binary = new tree();
		binary.add_node(2);
		binary.add_node(3);
		binary.add_node(1);
		binary.traverse_inorder(binary.root);
		binart.traverse_preorder(binary.root);
	}
}