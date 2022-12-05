class Vertex(object):
    """vertex with key and optional data payload"""

    def __init__(self, key, index, payload=None):
        self.key = key
        self.payload = payload  # data associated with Vertex
        self.index = index

    def __str__(self):
        return self.key

    def __repr__(self):
        return str(self)


class Graph(object):

    def __init__(self, max_vertexes=100):
        self.matrix = [[None] * max_vertexes for _ in range(max_vertexes)]  # 2d array (list of lists)
        self.num_vertexes = 0  # current number of vertexes
        self.vertexes = {}  # vertex dict
        self.i_to_key = []  # list of keys to look up from index

    def add_vertex(self, key, payload=None):
        """ add vertex named key if it is not already in graph"""
        assert self.num_vertexes < len(self.matrix), "max vertexes reached,  can not add more!"
        if key not in self.vertexes:
            self.i_to_key.append(key)
            i = self.num_vertexes
            self.vertexes[key] = Vertex(key, i, payload)
            self.num_vertexes = i + 1

    def add_edge(self, from_key, to_key, weight=None):
        """ create vertexes if needed and then set weight into matrix"""
        self.add_vertex(from_key)
        self.add_vertex(to_key)
        self.matrix[self.vertexes[from_key].index][self.vertexes[to_key].index] = weight

    def get_vertex(self, key):
        return self.vertexes[key]

    def get_vertices(self):
        """returns the list of all vertices in the graph."""
        return self.vertexes.values()

    def __contains__(self, key):
        return key in self.vertexes

    def edges(self, from_key):
        """ return list of tuples (to_vertex, weight) of all edges from vertex_key key"""
        to_dim = self.matrix[self.vertexes[from_key].index]
        return [(g.vertexes[g.i_to_key[i]], w) for i, w in enumerate(to_dim) if w]
        # result = []
        # for i,w in enumerate(to_dim):
        #     if w:  # if weigh not None
        #         result.append((self.vertexes[self.i_to_key[i]], w))
        # return result


if __name__ == '__main__':
    # test case figure 2 @
    # https://runestone.academy/runestone/static/pythonds/Graphs/VocabularyandDefinitions.html#fig-dgsimple

    g = Graph()
    g.add_edge('v0', 'v1', 5)
    g.add_edge('v1', 'v2', 4)
    g.add_edge('v2', 'v3', 9)
    g.add_edge('v3', 'v4', 7)
    g.add_edge('v4', 'v0', 1)
    g.add_edge('v0', 'v5', 2)
    g.add_edge('v5', 'v4', 8)
    g.add_edge('v3', 'v5', 3)
    g.add_edge('v5', 'v2', 1)
    g.add_vertex('v6')  # extra vertex with no connections

    print("The Matrix from this graph:")
    print(" ", " ".join([v.key for v in g.get_vertices()]))
    for i in range(g.num_vertexes):
        row = map(lambda x: str(x) if x else '.', g.matrix[i][:g.num_vertexes])
        print(g.i_to_key[i], "  ".join(row))

    print('\ng.edges("v5")', g.edges("v5"))
    print('"v5" in g', "v5" in g)
    print('"v6" in g', "v6" in g)
    print('"v7" in g', "v7" in g)