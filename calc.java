import java.util.*;
class stack
{
	float data[] = new float[50];
	int top;
	stack()
	{
		this.top = -1;
	}

	void push(float value)
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
	float pop()
	{
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
				postfix+=symbol;
			}
			
			else if((s.is_empty() || priority(s,symbol)) && symbol!=')')
			{
				postfix+=',';
				s.push(symbol);
			}
			
			else if (priority(s,symbol) == false && symbol!= ')')
			{
				while(s.is_empty() || priority(s,symbol)==false)
				{
					if(s.is_empty())
					{
						break;
					}
					postfix+=(","+s.pop_char());
				}
				s.push(symbol);
			}
			else if (symbol==')')
			{
				while(s.data[s.top]!='(')
				{
					postfix+=(","+s.pop_char());
				}
				s.pop_char();
			}
		}
		while(!s.is_empty())
		{
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
				while(postfix.charAt(i)!=',')
				{
					symbol= postfix.charAt(i);
					number= (number*10)+Float.parseFloat(String.valueOf(symbol));
					i++;
				}
				s.push(number);	
			}
			else
			{
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
