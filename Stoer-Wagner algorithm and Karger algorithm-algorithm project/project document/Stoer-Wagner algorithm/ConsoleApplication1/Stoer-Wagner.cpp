#include <vector>
#include <list>
#include <iostream>
#include <fstream>
#include <sstream>
#include <cmath>
#include <ctime>
#include <assert.h>
#include <algorithm>
#include <Windows.h>

using namespace std;
int capacity[500][500];
int n, m, t;

class Graph
{
public:
	void addVertex()
	{
		vertexlist.push_back(list<int>());
	}

	void addEdge(int vertex, int adjacent)
	{
		vertexlist[vertex].push_back(adjacent);
	}

	int vertices()
	{
		return vertexlist.size();
	}

	int edges()
	{
		int count = 0;
		for (int i = 0; i < vertices(); ++i) {
			count = count + vertexlist[i].size();
		}
		return count;
	}

	list<int> &edges_for_vertex(int vertex)
	{
		return vertexlist[vertex];
	}

	bool exist(int s, int t)
	{
		list<int> &edges = vertexlist[s];
		list<int>::iterator it;
		for (it = edges.begin(); it != edges.end(); it++) {
			int adjacent = *it;
			if (adjacent == t) {
				return true;
			}
		}

		return false;
	}

private:
	vector<list<int> > vertexlist;
};

class Unionsearch
{
public:
	Unionsearch(int size)
	{
		vertexlist.resize(size);
		for (int i = 0; i < size; ++i) {
			vertexlist[i] = i;
		}
	}

	void Contraction(int x, int y)
	{
		int root_x = Search(x);
		int root_y = Search(y);

		if (root_x != root_y) {
			vertexlist[root_y] = root_x;
		}
	}

	int Search(int x)
	{
		if (vertexlist[x] == x) {
			return x;
		}

		int root = Search(vertexlist[x]);
		vertexlist[x] = root;
		return root;
	}

private:
	vector<int> vertexlist;
};

class Minimumcut
{
public:
	int Karger(Graph &graph)
	{
		int cut = INT_MAX;
		for (int i = 0; i < t; ++i) {  // repeat n^2*ln(n) times
			Unionsearch searchobj(n);

			for (int j = 0; j < n - 2; ++j) {
				int x, y;
				do {
					x = rand() % n;
					y = rand() % n;
				} while (!(graph.exist(x, y) && searchobj.Search(x) != searchobj.Search(y)));

				searchobj.Contraction(x, y);
			}

			cut = min(cut, Count(graph, searchobj));
		}

		return cut;
	}

private:
	int Count(Graph &graph, Unionsearch &searchobj)
	{
		int count = 0;
		int n = graph.vertices();

		for (int i = 0; i < n; ++i) {
			list<int> &edges = graph.edges_for_vertex(i);
			list<int>::iterator it;
			for (it = edges.begin(); it != edges.end();it++) {
				int adjacent = *it;
				if (searchobj.Search(i) != searchobj.Search(adjacent)) {
					count = count + capacity[i][adjacent];
				}
			}
		}

		assert(~(count & 1));
		return count >> 1;
	}
};

int main(int argc, char *argv[])
{
	ifstream fin("test.txt");
	string line;
	stringstream stream;
	//LARGE_INTEGER t1, t2, tc;

	for (int i = 0; i < 100; i++)
	{
		for (int j = 0; j < 99; j++)
		{
			capacity[i][j] = 0;
		}
	}

	Graph graph;
	while (getline(fin, line)) {
		int vertex, adjacent, cap;

		stream.clear();
		stream << line;
		stream >> vertex;
		graph.addVertex();
		while (stream >> adjacent) {
			graph.addEdge(vertex - 1, adjacent - 1);
			stream >> cap;
			capacity[vertex - 1][adjacent - 1] = cap;
		}
	}

	Minimumcut Minimumcut;
	//QueryPerformanceFrequency(&tc);
	srand((unsigned)clock());
	//QueryPerformanceCounter(&t1);
	n = graph.vertices();
	m = graph.edges();
	t = n * n * (int)ceil(log(n));
	cout << "Vertices : " << n << endl;
	cout << "Edges    : " << m / 2 << endl;
	cout << "Repeat   : " << t << endl;
	cout << "Minimum cut:" << Minimumcut.Karger(graph) << endl;
	//QueryPerformanceCounter(&t2);
	//int useTime = (int)((t2.QuadPart - t1.QuadPart) * 1000000 / tc.QuadPart);
	//cout << "running time of the whole program " << useTime <<us << endl;

	return 0;
}