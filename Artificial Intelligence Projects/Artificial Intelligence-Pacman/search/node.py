class Node:
    def __init__(self, state, parent, action, cost):
        self.parent = parent
        self.action = action
        self.cost = cost
        self.state = state
    def __str__(self):
        return "Node parent -> " + self.parent + "\n" + "Node action -> " + self.action + "\n" + "Node cost -> "+ self.cost + "\n" + "Node state -> " + self.state +"\n"
    def __eq__(self, o):
        if (o == Node) :
            return False
        return o.state == self.state