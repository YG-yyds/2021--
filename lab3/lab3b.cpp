#include <iostream>
#include <cstring>

using namespace std;

int ans = 0;
int x[21][21] = { 0 };
int hi[21] = { 0 };

int fuyin(char c) {
    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
        return 0;
    }
    return 1;
}
int ch(char c) {
    if (c < 101)
        return c - 98;
    else if (c < 105)
        return c - 99;
    else if (c < 111)
        return c - 100;
    else if (c < 117)
        return c - 101;
    else
        return c - 102;
}
void dfs(int d, int sum) {
    if (d == 21) {
        if (ans < sum)
            ans = sum;
        return;
    }
    dfs(d + 1, sum);
    hi[d] = 1;
    for (int i = 0; i < 21; ++i) {
        if (hi[i] == 0)
            sum = sum + x[d][i];
        else
            sum = sum - x[d][i];
    }
    if (ans < sum)
        ans = sum;
    dfs(d + 1, sum);
    hi[d] = 0;
    /*for (int i = 0; i < 21; ++i) {
        if (hi[i] == 1) sum = sum + x[d][i];
        else sum = sum - x[d][i];
    }
    if (ans < sum) ans = sum;*/
}

int main() {
    int T;
    // ifstream inf("input.txt");
    cin >> T;
    // inf >> T;
    for (int t = 0; t < T; ++t) {
        // clock_t start, finish;
        string str;
        cin >> str;
        // inf >> str;
        // start = clock();
        int l = str.length();
        for (int i = 0; i < l - 1; ++i) {
            if (fuyin(str[i]) && fuyin(str[i + 1]) && str[i] != str[i + 1]) {
                int i1 = ch(str[i]);
                int i2 = ch(str[i + 1]);
                x[i1][i2]++;
                x[i2][i1]++;
            }
        }
        dfs(1, 0);
        cout << ans << endl;
        ans = 0;
        memset(hi, 0, sizeof(hi));
        memset(x, 0, sizeof(x));
        // finish = clock();
        // double totaltime = (double)(finish - start) / CLOCKS_PER_SEC;
        // cout << "time: " << totaltime << endl;
    }
    return 0;
}