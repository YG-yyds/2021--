#include <iostream>
#include <cstring>
using namespace std;
int a[100001];
int b[100001];
int opt[100001];
int main(){
    ios::sync_with_stdio(false);
    memset(a,0, sizeof(a));
    memset(b,0, sizeof(b));
    memset(opt,0,sizeof(opt));
    int n;
    cin>>n;
    for (int i = 1; i <= n; ++i) cin>>a[i];
    for (int i = 1; i < n; ++i) cin>>b[i];
    opt[1]=a[1];
    for (int i = 2; i <= n; ++i) {
        opt[i]=min(opt[i-2]+b[i-1],opt[i-1]+a[i]);
    }
    cout<<opt[n];
    return 0;
}