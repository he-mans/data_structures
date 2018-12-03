import java.util.*;
class stack
{
	//float stack to store numbers for operation
	float data[] = new float[50];
	int top;
	stack()
	{
		this.top = -1;
	}

	void push(float value)
	{
		//function to add item to stack
		if(this.top==50)
		{
			System.out.println("overflow");
			return;
		}
		this.top+=1;
		this.data[this.top] = value;
		return;
	}
	float pop()
	{
		//function to remove item from stack
		if(this.top==-1)
		{
			System.out.println("underflow");
			return -1;
		}
		float value = this.data[this.top];
		this.top-=1;
		return value;
	}
	boolean is_empty()
	{
		//checks if stack is empty or not
		if (this.top == -1)	
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

class stack_char extends stack
{

	//char stack to store operands while conversion form infix to postfix
	char data[] = new char[50];
	int top;
	stack_char()
	{
		this.top = -1;
	}
	
	void push(char value)
	{
		if(this.top==50)
		{
			System.out.println("overflow");
			return;
		}
		this.top+=1;
		this.data[this.top] = value;
		return;
	}
	
	char pop_char()
	{
		if(this.top==-1)
		{
			System.out.println("underflow");
			return '$';
		}
		char value = this.data[this.top];
		this.top-=1;
		return value;
	}

	boolean is_empty()
	{
		if (this.top == -1)	
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

class convert
{

	boolean priority(stack_char s, char symbol)
	{
		//compares prioirty of the symbol encountered in infix expression 
		//and the symbol on top of stack. 
		//if priority of encountered symbol is gerater than returns true
		//else false
		char symbol_in_stack = s.data[s.top];
		switch(symbol)
		{
			case '+':
			case '-':{
				if (symbol_in_stack=='(')
				{
					return true;
				}
				else 
				{
					return false;
				}
			}

			case '*':
			case '/':
			case '%':{
				if (symbol_in_stack=='+' || symbol_in_stack=='-' || symbol_in_stack=='(')
				{
					return true;
				}
				else
				{
					return false;
				}
			}

			case '(':
			{
				return true;
			}
			case ')':
			{
				return false;
			}
		}
		return false;
	}

	String to_postfix(String infix)
	{

		stack_char s = new stack_char();
		String postfix = "";
		for(int i=0; i<infix.length(); i++)
		{
			char symbol = infix.charAt(i);
			if (symbol>='0' && symbol<='9')
			{
				//appending number to postfix expression
				postfix+=symbol;
			}

			else if((s.is_empty() || priority(s,symbol)) && symbol!=')')
			{
				//in order to identify in postfix expression when a number has
				//finished as we are going to deal with multi digit numbers as 
				//suppose to single we add a comma between them.
				//and while evaluating we just add a loop until we encounter
				//a comma storing all the numbers encountered previously in a string and then when loop 
				//ends we parse the string to float thus giving back out multi digit
				//number back
				postfix+=',';
				//after appending comma pushing symbol encountered to char stack
				s.push(symbol);
			}
			
			else if (priority(s,symbol) == false && symbol!= ')')
			{
				//if prioirty of encountered symbol is less then
				//popping stack until stack is empty or we found
				//a symbol in stack that has smaller priority then our symbol
				//and then push our symbol in stack
				while(s.is_empty() || priority(s,symbol)==false)
				{
					if(s.is_empty())
					{
						break;
					}
					//adding a comma to identify when a number has ended
					postfix+=(","+s.pop_char());
				}
				
				s.push(symbol);
			}
			else if (symbol==')')
			{
				//if encountered a closing paranthesis
				//popping stack until we encounter
				//opening paranthesis in stack
				while(s.data[s.top]!='(')
				{
					postfix+=(","+s.pop_char());
				}
				s.pop_char();
			}
		}
		while(!s.is_empty())
		{
			//popping stack until it becomes empty and adding symbol to postfix expression
			postfix+=(","+s.pop_char());
		}
		
		return postfix;
	}

}

class calculate
{
	String get_ans(String postfix)
	{
		stack s = new stack();
		float operand1,operand2;
		char symbol;
		for(int i=0; i<postfix.length();i++)
		{
			symbol = postfix.charAt(i);
			if(symbol>='0' && symbol<='9')
			{
				float number = 0.0f;
				//while loop with a condition which specifies that until we 
				//encountr a comma in postfix expression the number hasn't ended
				//and thus storing it to a number variable which after the loop ends 
				//will be pushed to stack
				while(postfix.charAt(i)!=',')
				{
					symbol= postfix.charAt(i);
					//parsing the symbol and multiplying previous number value by 10 to 
					//increase it's unit size so as to add current number is added appended
					//to it at unit's place
					number= (number*10)+Float.parseFloat(String.valueOf(symbol));
					i++;
				}
				s.push(number);	
			}
			else
			{
				//performing operation according to symbol encountered in expression
				switch(symbol)
				{
					case '+':{		
						operand2=s.pop();
						operand1=s.pop();
						s.push(operand1+operand2);
						break;
					}
					case '-':{
						operand2=s.pop();
						operand1=s.pop();
						s.push(operand1-operand2);
						break;
					}
					case '/':{
						operand2=s.pop();
						operand1=s.pop();
						s.push(operand1/operand2);
						break;
					}
					case '*':{
						operand2=s.pop();
						operand1=s.pop();
						s.push(operand1*operand2);
						break;
					}
				}
			}
		}
		return Float.toString(s.pop());

	}
}

class calc
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("enter your expression:-");
		String input = sc.nextLine();
		convert infix = new convert();
		String postfix = infix.to_postfix(input);
		//System.out.println(postfix);
		calculate calculator = new calculate();
		System.out.println("your ans is:-");
		System.out.println(calculator.get_ans(postfix));
	}
}
