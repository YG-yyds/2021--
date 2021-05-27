import random

VERTEX_COUNT = 100
QUERY_COUNT = 30
WEIGHT_RANGE = 100
QUERY_RANGE = 100
DEGREE_RANGE = 10

unselected_edge_list = list(range(1, VERTEX_COUNT + 1, 1))

current_root = random.choice(unselected_edge_list)
unselected_edge_list.remove(current_root)

buffer = []
while len(unselected_edge_list) > 0:
    out_degree = random.randint(1, DEGREE_RANGE)
    for i in range(out_degree):
        destination_vertex = random.choice(unselected_edge_list)
        weight = random.randint(1, WEIGHT_RANGE)
        if random.choice([True, False]):
            buffer.append([current_root, destination_vertex, weight])
        else:
            buffer.append([destination_vertex, current_root, weight])
        unselected_edge_list.remove(destination_vertex)
        if len(unselected_edge_list) == 0:
            break
        if i == out_degree - 1:
            current_root = destination_vertex
random.shuffle(buffer)

queries = []
for i in range(QUERY_COUNT):
    queries.append(str(random.randint(1, QUERY_RANGE)))

text = open("in.txt", "w")
text.write(f"{VERTEX_COUNT} {QUERY_COUNT}\n")

for edge in buffer:
    text.write(f"{edge[0]} {edge[1]} {edge[2]}\n")

text.write(" ".join(queries))
text.close()
