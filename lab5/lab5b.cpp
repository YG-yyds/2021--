//#include <bits/stdc++.h>
#include <iostream>
#include <fstream>
#include <queue>
#include <vector>
#include <algorithm>
using namespace std;
struct edge {
    int nd, wei;
    edge(int nd, int wei) {
        this->nd = nd;    //
        this->wei = wei;  // weight
    }
};
struct nd {
    int id;
    int food;
    long long inv = 0;
    long long inc = 0;
    long long prof = 0;
    nd(int id = 0, int food = 0) {
        this->id = id;
        this->food = food;
    }
};

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

vector<edge> e[200000];
int food[200000];
long long inv[200000];
long long inc[200000];
long long prof[200000];
long long dfs1(int now, int fa) {
    int size = e[now].size();
    long long ninc = 0;
    for (int i = 0; i < size; i++) {
        int son = e[now][i].nd;
        if (son == fa)
            continue;
        inv[son] = e[now][i].wei;
        inc[son] = dfs1(son, now) - inv[son] + food[son];
        prof[son] = inc[son] - inv[son];
        ninc += prof[son];
    }
    return ninc;
}
bool compare(edge e1, edge e2) {
    int o1 = e1.nd;
    int o2 = e2.nd;
    if (prof[o1] >= 0 && prof[o2] >= 0) {
        if (inv[o1] < inv[o2])
            return 1;  //-1;
        else
            return 0;  // 1;
    } else if (prof[o1] >= 0 && prof[o2] < 0)
        return 1;  //-1;
    else if (prof[o1] < 0 && prof[o2] >= 0) {
        return 0;  // 1;
    } else if (prof[o1] < 0 && prof[o2] < 0) {
        if (inc[o1] > inc[o2])
            return 1;  //-1;
        else
            return 0;  // 1;
    }
}
long long hp = 0, wait = 0;
void dfs2(int now, int fa) {
    int size = e[now].size();
    sort(e[now].begin(), e[now].end(), compare);
    for (int i = 0; i < size; ++i) {
        int son = e[now][i].nd;
        if (son == fa)
            continue;
        hp -= e[now][i].wei;
        if (hp < 0) {
            wait -= hp;
            hp = food[son];
        } else
            hp += food[son];
        dfs2(son, now);
        hp -= e[now][i].wei;
        if (hp < 0) {
            wait -= hp;
            hp = 0;
        }
    }
}
int main() {
    int n;
    // ios::sync_with_stdio(false);
    n = readNum();
    for (int i = 0; i < n; i++) {
        food[i] = readNum();
    }
    for (int i = 0; i < n - 1; i++) {
        int u, v, w;
        u = readNum();
        v = readNum();
        w = readNum();
        e[u - 1].emplace_back(edge(v - 1, w));
        e[v - 1].emplace_back(edge(u - 1, w));
    }
    dfs1(0, -1);
    hp = food[0];
    dfs2(0, -1);
    cout << wait;
}