# Inputs: centroid locations, point, distance metric
# Outputs: centroid index that is closest
def choose_cluster(centroids, x, distance_metric):
    # Hold the min dist and centroid for this point
    (min_dist, min_centroid) = (None, None)
    for i in range(len(centroids)):
        centroid = centroids[i]
        dist = distance_metric(x, centroid)
        if min_centroid is None or dist < min_dist:
            (min_dist, min_centroid) = (dist, i)
    # Assign point to closest centroid
    return min_centroid

# Inputs: 
#  initial_centroids: locations (length=K)
#  data: data points (length=N)
#  distance_metric: how to select the closest point in the e-step
# 
# Outputs:
#  centroids: locations (length=K)
#  new_clusters: for each data point, index of associated cluster
#  iterations: count of iterations taken
#  total_sse: SSE of final clustering
def kmeans(initial_centroids, data, distance_metric):
    centroids = initial_centroids[:]
    new_clusters = [-1] * len(data)
    iterations = 0
    total_sse = 0
    
    # Tracker to see if centroids changed
    centroids_changed = True
    
    while(centroids_changed):
        centroids_changed = False
        
        # Assign points to closest centroids
        for i in range(len(data)):
            point = data[i]
            new_clusters[i] = choose_cluster(centroids, point, distance_metric)
            
        # Find new locations for each centroid
        for i in range(len(centroids)):
            centroid = centroids[i]
            
            # Find points for this centroid
            points = []
            for j in range(len(data)):
                if new_clusters[j] == i:
                    points.append(data[j])
                    
            # Calculate new centroid
            new_centroid = [-1] * len(centroid)
            if (len(points) > 0):
                for j in range(len(centroid)):
                    new_centroid[j] = sum(pt[j] for pt in points) / float(len(points))
                if tuple(new_centroid) != centroid:
                    centroids_changed = True
                    centroids[i] = tuple(new_centroid)
        
        iterations += 1
    
    total_sse = sum(sse(data[i], centroids[new_clusters[i]]) for i in range(len(data)))
        
    return centroids, new_clusters, iterations, total_sse

def cluster_plot(centroids, data, clustering):
    for i,c in enumerate(centroids):
        points = [data[idx] for idx,c in enumerate(clustering) if c == i]
        plt.scatter([d[0] for d in points], [d[1] for d in points], label="{}".format(i))
        plt.plot(c[0], c[1], 'kx', mew=5, ms=10)

def dbscan_plot(data, clustering):
    for cluster in set(clustering):
        points = [data[idx] for idx, c in enumerate(clustering) if c == cluster]
        if cluster == -1:
            plt.scatter([d[0] for d in points], [d[1] for d in points], label="{}".format(cluster), c='black')
        else:
            plt.scatter([d[0] for d in points], [d[1] for d in points], label="{}".format(cluster))
        
def plot_sse(dataset, max_clusters=10, trials=5):
    clusters = range(1, max_clusters + 1)
    sses = []

    seed(notebook_random_state)

    # 1. Perform 5 trials (randomizing initial points ala Forgy) for each of K=1, 2, ... 10
    # 2. Plot SSE vs K
    for k in range(1, 11):
        temp_sse = 0
        for i in range(5):
            initial_centroids = []
            for centroid_num in range(k):
                new_centroid = dataset[randint(0, len(dataset)-1)]
                while new_centroid in initial_centroids:
                    new_centroid = dataset[randint(0, len(dataset)-1)]
                initial_centroids.append(new_centroid)
            (_, _, _, total_sse) = kmeans(initial_centroids, dataset, euclidean_distance)
            temp_sse += (total_sse / 5.)
        sses.append(temp_sse)

    plt.plot(clusters, sses)
    plt.xticks(range(1, len(clusters)+1, 1))
    plt.title('SSE vs Clusters')