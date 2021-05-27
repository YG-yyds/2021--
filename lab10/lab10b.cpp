#include <iostream>
#include <sstream>
#include <vector>
#include <climits>
#include <algorithm>
using namespace std;
typedef long long l;
int readNum() {
    char ch = getchar();
    while (ch < '0' or ch > '9') ch = getchar();
    int v = 0;
    while (ch >= '0' and ch <= '9') {
        v = v * 10 + ch - '0';
        ch = getchar();
    }
    return v;
}
struct edge {
    l w;
    l u;
    l v;
    edge(l u, l v, l w) {
        this->u = u;
        this->v = v;
        this->w = w;
    }
};
struct nd {
    l v;
    l size;
    nd *fa;
    nd(l v) {
        this->v = v;
        size = 1;
        fa = NULL;
    }
};
bool compare(edge e1, edge e2) { return e1.w < e2.w; }
l findFa(nd *now) {
    nd *t = now;
    while (t->fa != NULL) {
        t = t->fa;
    }  // find father of the union
    nd *t2 = now;
    while (t2->fa != NULL) {
        t2->fa = t;
        t2 = t2->fa;
    }  // update father
    return t->v;
}
void un(nd *nd1, nd *nd2) {
    nd *t1 = nd1;
    nd *t2 = nd2;
    while (t1->fa != NULL) {
        t1 = t1->fa;
    }
    while (t2->fa != NULL) {
        t2 = t2->fa;
    }
    if (t1 != t2) {
        if (t1->size > t2->size) {
            t2->fa = t1;
            t1->size += t2->size;
        } else {
            t1->fa = t2;
            t2->size += t1->size;
        }
    }
}  // union
l gSize(nd *now) {
    nd *t = now;
    while (t->fa != NULL) {
        t = t->fa;
    }
    return t->size;
}  // get the size of the union
struct kruskal {
    vector<edge> e;
    vector<int> q;
    vector<l> a;
    vector<nd> nds;
    vector<l> wei;
    int N, M;
    kruskal() {
        int n = readNum();
        int m = readNum();
        N = n;
        M = m;
        for (int i = 0; i < n - 1; ++i) {
            l u = readNum() - 1;
            l v = readNum() - 1;
            l w = readNum();
            e.emplace_back(edge(u, v, w));
        }
        for (int i = 0; i < n - 1; ++i) {
            wei.emplace_back(LLONG_MAX);
        }
        for (int i = 0; i < m; ++i) {
            int qi = readNum();
            q.emplace_back(qi);
        }
        for (int i = 0; i < n; ++i) {
            nds.emplace_back(nd(i));  // initial
        }
    }
    void ans() {
        sort(e.begin(), e.end(), compare);
        l max = e[N - 2].w;
        l min = e[0].w;
        for (int i = 0; i < max; ++i) {
            a.emplace_back(0);
        }
        l w0 = e[0].w;
        l cnt = 0;
        for (int i = 0; i < N - 1; ++i) {
            edge ei = e[i];
            while (w0 < ei.w) {
                a[w0 - 1] = cnt;
                w0 += 1;
            }
            cnt += gSize(&nds[ei.u]) * gSize(&nds[ei.v]);
            un(&nds[ei.u], &nds[ei.v]);  // union
        }
        a[w0 - 1] = cnt;
        for (int i = 0; i < M; ++i) {
            if (q[i] < min)
                cout << 0 << " ";
            else if (q[i] > max)
                cout << a[(int)max - 1] << " ";
            else
                cout << a[q[i] - 1] << " ";
        }
    }
};
int main() {
    kruskal k;
    k.ans();
    return 0;
}