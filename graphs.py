class graph:
	def __init__(self):
		self.nodes = []

	def add_node(self,new_node):
		if type(new_node)!= node:
			raise TypeError("only node type is allowed")
		self.nodes.append(new_node)

	def get_status(self):
		print(f"total nodes in graph -> {len(self.nodes)}")
		print("connection status :")
		for nodes in self.nodes:
			nodes.get_status()
	
class node:
	def __init__(self,name):
		self.connected_nodes = []
		self.name = name

	def connect_to(self,new_node):
		if type(new_node)!=node:
			raise TypeError("only node type is allowed")
		self.connected_nodes.append(new_node)
		new_node.connected_nodes.append(self)

	def get_status(self):
		names = [node_item.name for node_item in self.connected_nodes]
		print(f"{self.name}->",end = '')
		print(names)

if __name__ == '__main__':
	graph1 = graph()
	node1 = node("abc")
	node2 = node("def")
	graph1.add_node(node1)
	graph1.add_node(node2)
	node1.connect_to(node2)
	node1.get_status()
	graph1.get_status()
