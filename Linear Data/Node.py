class Node:
    def __init__(self, data=None, link=None):
        self.data = data
        self.link = link
        
    def get_value (self):
        return self.data

    def get_next_node (self):
        return self.link

    def set_next_node (self, link):
        self.link = link

    def get_print (self):
        print("Data:", self.data, 'Link:', self.link)

# value1 = Node (5)
# value2 = Node (6)
# value3 = Node (7)

# value1.get_print()
# value2.get_print()
# value3.get_print()

# print(value1.get_value())
# print(value1.get_link())

# value2.set_link(value1)
# value3.set_link(value1)

# value1.get_print()
# value2.get_print()
# value3.get_print()

# print("Value 1 data:", value2.get_link().get_value())