from node import Node

# Add your Stack class below:
class Stack:
    def __init__ (self):
        self.top_item = Node(None)
        self.size = 0
        self.limit = 1000

    def peek (self):
        return self.top_item.get_value()

    def push (self, data):
        newItem = Node(data, self.top_item)
        if (self.size <= self.limit):
            self.top_item = newItem
            self.size += 1
        else: 
            print ("Stack is already full")

    def pop (self):
        if (self.size > 0):
            removing_item = self.top_item
            self.top_item = removing_item.get_next_node()
            self.size -= 1
            return removing_item.get_value()
        else:
            print ("Stack is empty")

    def get_print (self):
        index = self.top_item
        print ("root->", end='')
        while (index.value != None):
            print (index.get_value(), end='->')
            index = index.get_next_node()
    
    def has_space (self):
        return self.size < self.limit

    def is_empty (self):
        return self.size == 0

test_stack = Stack()
for numb in range(10):
    test_stack.push(numb)
test_stack.get_print()
print(test_stack.size)