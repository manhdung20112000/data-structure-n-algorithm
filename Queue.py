from Node import Node
# Create the Queue class below:

class Queue:
    def __init__ (self):
        self.head = Node(None, None)
        self.tail = Node(None, None)
        self.size = 0
        self.limit = 20

    def enqueue (self, data):
        newNode = Node (data, self.head)
        if self.size == 0:
            self.tail = newNode
            self.size += 1
        elif self.size < self.limit:
            self.head = newNode
            self.size += 1
        else: 
            print ('Sorry, no more room!')

    def dequeue (self):
        remove_head = self.head
        self.head = remove_head.get_next_node()
        return remove_head.get_value()
    
    def peek (self):
        return self.head.get_value()

    def get_size (self):
        return self.size

    def has_space (self):
        if self.size >= self.limit: return False
        return True

    def is_empty (self):
        if self.size > 0: 
            return False
        return True

    def get_print (self):
        index = self.head
        print ("root->", end='')
        while (index.get_value() != None):
            print (index.get_value(), end='->')
            index = index.get_next_node()
        print ('tail')
    
test_queue = Queue ()
for numb in range(21):
    test_queue.enqueue(numb)

test_queue.get_print()

for numb in range(5):
    test_queue.dequeue()

test_queue.get_print()

print('Head list data:', test_queue.peek())