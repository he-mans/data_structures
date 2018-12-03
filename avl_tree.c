#include<stdio.h>
#include<stdlib.h>

struct treenode
{
	int data;
	struct treenode *left;
	struct treenode *right;
};

typedef struct treenode node;

int get_height(node *root)
{	
	//function to get height of tree
	if (root!=NULL)
	{
		int left_height = get_height(root->left);
		int right_height = get_height(root->right);
		if (left_height>right_height)
			return left_height+1;
		else
			return right_height+1;
	}
	else 
		return 0;
}

int get_balance(node *root)
{
	//uses height to get balance of root and returns it;
	return (get_height(root->left) - get_height(root->right));
}
int is_balanced(node *root)
{
	//checks if a node is balanced or not and returns 1 if yes
	int balance = (get_height(root->left) - get_height(root->right));
	if (balance >=2 || balance <=-2)
		return 0;
	else
		return 1;
}

node *balance_zig_zac(node *root, int flag)
{
	//1 if heavy on left
	//0 if heavy on right
	
	//balancing all zig zac heavy pattern which may occure in
	//future nodes from this point
	node *temp;
	int current_balance = get_balance(root);
	if (flag==0)
	{
		while(root->right->left!=NULL)
			{
				if (current_balance*get_balance(root->right)<0)
					root->right = balance_zig_zac(root->right,1);
				else
					break;
			}
		temp = root;
		root = root->right;
		temp->right = root->left;
		root->left = temp;
	}
	else if (flag==1)
	{
		while(root->left->right!=NULL)
		{
			if(current_balance*get_balance(root->left)<0)
				root->left = balance_zig_zac(root->left,0);
			else
				break;
		}
		temp = root;
		root = root->left;
		temp->left = root->right;
		root->right = temp;		
	}
	return root;
}

node *balance(node *root)
{
	//performs balancing
	if(root == NULL || is_balanced(root))
	{	
		//checks if root is nul or is alredy balanced or not.
		//if root is already balanced then no need of any further operation and
		//thus return
		return root;
	}
	else
	{
		//balances the left tree of the current node.
		root->left = balance(root->left);
		//balances the right tree of the current node
		root->right = balance(root->right);
		//temp varable to store the root node while swapping
		node *temp;
		int current_balance = get_balance(root);
		//checks in which direction the node is heavy/unbalanced
		//balance >= -2 if right heavy and balance<=2 means left heavy
		if (current_balance<=-2)
		{
			//checks if root node and child node is heavy on the same side.
			//if they are then product of their balance will always > 0 i.e. positive
			//else it will be negative
			if(current_balance*get_balance(root->right)>0)
			{
				temp = root;
				root = root->right;
				temp->right = root->left;
				root->left = temp;
			}
			else
			{
				//child node is left whereas parent is right heavy thus 
				//balancing this sort of zig zac pattern in regard to it
				//and every other pattern like this which may occure in future
				//nodes
				root->right = balance_zig_zac(root->right,1);
				//balancing lead to new root than previous 
				//so to register this change in acutall root
				//we do this assignment
				root = balance(root);
			}
		}
		else if(current_balance>=2)
		{	
			if(current_balance*get_balance(root->left)>0)
			{
				temp = root;
				root = root->left;
				temp->left = root->right;
				root->right = temp;
			}
			else
			{
				//child node is right heavy and parent is left thus balancing in regard to it;
				root->left = balance_zig_zac(root->left,0);
				root = balance(root);
			}
		}
	}
	return root;
}

node *add(node *root, int data)
{

	node *newnode = (node *)malloc(sizeof(node));
	newnode->data = data;
	newnode->left = NULL;
	newnode->right = NULL;

	if (root == NULL)
	{
		//root==null meanse first node in tree
		root = newnode;
	}
	
	else if (root->left == NULL && data<root->data)
	{	
		//meanse the current node is leaf node
		//and the newnode is to be its child node
		root->left = newnode;
		//newnode added is always balanced thus we
		//only need to balance nodes from its parent node
		root = balance(root);
	}

	else if (root->right == NULL && data>root->data)
	{
		root->right = newnode;
		root = balance(root);	
	}

	else
	{
		if (data>root->data)
			//due to balancing new root of the subtree may differ then before
			//so to register the change in parent node we do this assignment
			root->right = add(root->right,data);
		else
			root->left = add(root->left,data);
		root = balance(root);
	}
	return root;
}

void traverse_inorder(node *root)
{
	if (root!=NULL)
	{
		traverse_inorder(root->left);
		printf("%d\n",root->data);
		traverse_inorder(root->right);
	}
}

int main()
{
	node *root = NULL;
	int i,data;
	printf("\n1 to add new node\n2 for inorder traversal\n3 to exit\n");
	scanf("%d",&i);
	while(i==1 || i==2)
	{
		switch(i)
		{
			case 1:{
				printf("\nEnter new data for new node\n");
				scanf("%d",&data);		
				root = add(root,data);
				break;
			}

			case 2:{
				printf("\n");
				traverse_inorder(root);
				break;
			}
		}
		printf("\n1 to add new node\n2 for inorder traversal\n3 to exit\n");
		scanf("%d",&i);
	}
}
