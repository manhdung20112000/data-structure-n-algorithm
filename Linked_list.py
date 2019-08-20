from Node import Node
class SinglyLinkedList:
    #Here is the thing, in python, we don't have pointer, so head's and tail'type is Node
    def __init__ (self, value=None):
        self.head = Node()
        self.tail = Node()
    
    def get_head_node (self):
        return self.head
    
    def insert_head (self, data):
        self.head = Node(data, self.get_head_node())
    
    def remove_node (self, del_data):
        index = current_node = self.head
        while index != None:
            if index == self.head and index.get_value() == del_data:
                self.head = index.next_node
            elif index.get_value() == del_data:
                current_node.next_node = index.get_next_node()
            current_node = index
            index = index.next_node

    def get_print (self):
        index = self.head
        print ('head -> ', end = '')
        while index != None:
            print (index.data, '-> ', end = '')
            index = index.next_node

#Test singly linked list
test_list = SinglyLinkedList()
word_list = ['a', 'b', 'c', 'd', 'e', 1, 2, 3, 'e']
for key in word_list:
    test_list.insert_head(key)

test_list.remove_node('a')
test_list.insert_head('Hello World')
test_list.get_print()

