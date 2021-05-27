#include <iostream>
#include <vector>
using namespace std;
int main() {
    ios::sync_with_stdio(false);
    int n;
    cin >> n;
    vector<vector<int>> Lr(n, vector<int>(2));
    for (int i = 0; i < n; ++i) {
        cin >> Lr[i][0] >> Lr[i][1];
    }
    vector<int> happy(n, 0);
    for (int i = 0; i < n; ++i) {
        int cnt = 0;
        for (int j = 0; j < n; ++j) {
            if (j != i && Lr[j][0] <= Lr[i][0] && Lr[j][1] <= Lr[i][1])
                cnt++;
        }
        happy[cnt]++;
    }
    for (int i = 0; i < n; ++i) {
        cout << happy[i] << endl;
    }
    return 0;
}