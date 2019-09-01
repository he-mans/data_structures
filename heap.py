from typing import List

class Heap():
    def __init__(self, list: List[int] = []):
        self.heap = list
        self.size = len(list)
        self.buildHeap()

    def hasLeftChild(self,index: int) -> bool:
        return True if(self.size > (index*2 + 1)) else False

    def hasRightChild(self,index: int) -> bool:
        return True if(self.size > (index*2 + 2)) else False
    
    def leftChild(self, index: int) -> int:
        return index*2 +1
    
    def rightChild(self, index: int) -> int:
        return index*2 +2

    def hasParent(self,index: int) -> bool:
        return False if self.size-index == self.size else True
    
    def parent(self, index) -> int:
        return (index//2 + index%2) - 1

    def elementAt(self,index: int) -> int:
        return self.heap[index];

    def swapElementsAt(self,index1, index2):
        self.heap[index1],self.heap[index2] = self.heap[index2], self.heap[index1]
    
    def heapify(self, index: int):
        indexLargest: int = index
        indexLeftChild: int = self.leftChild(index)
        indexRightChild: int = self.rightChild(index)
        if(self.hasLeftChild(index) and self.elementAt(indexLargest) < self.elementAt(indexLeftChild)):
            indexLargest = indexLeftChild

        if(self.hasRightChild(index) and self.elementAt(indexLargest) < self.elementAt(indexRightChild)):
            indexLargest = indexRightChild
        
        if(indexLargest != index):
            self.swapElementsAt(indexLargest, index)
            self.heapify(indexLargest)

    def heapifyUp(self, index:int):
        parentIndex: int = self.parent(index)
        if(self.hasParent(index) and self.elementAt(index)>self.elementAt(parentIndex)):
            self.swapElementsAt(index,parentIndex)
            self.heapifyUp(parentIndex)

    def buildHeap(self):
        startingIndex: int = (self.size // 2) -1
        for index in range(startingIndex,-1,-1):
            self.heapify(index)

    def addElement(self, element: int):
        self.heap.append(element)
        self.size+=1
        self.heapifyUp(self.size-1)

    def sort(self):
        for i in range(self.size-1):
            self.swapElementsAt(0,self.size-1)
            self.size-=1
            self.heapify(0)


if __name__ == "__main__":
    heap = list(map(int, input("enter list items to enter into heap: ").split()))
    heap = Heap(heap)
    print(heap.heap)
    heap.addElement(int(input("enter element to add into heap: ")))
    print(heap.heap)
    heap.sort()
    print(heap.heap)
