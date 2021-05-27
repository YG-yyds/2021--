#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
typedef long long ll;
struct it{
    ll s,t,w,val;
    it(ll s=0,ll t=0,ll w=0):s(s),t(t),w(w){};
};
int N;
vector<it> its(N);
vector<ll> p(N,0);
vector<ll> match(N,0);
bool compare(it i1,it i2){return i1.val<i2.val;}
bool find(int i, int now){
    if (p[now]>its[i].t) return false;
    if (match[now]==-1){
        match[now]=i;
        return true;
    }
    int j=match[now];
    if (its[i].t>its[j].t) return find(i,now+1);
    else{
        if (find(j,now+1)){
            match[now]=i;
            return true;
        } else return false;
    }
}
int main(){
    ios::sync_with_stdio(false);
    cin>>N;
    for (int i = 0; i < N; ++i) {
        ll si,ti,wi;
        cin>>si>>ti>>wi;
        its.emplace_back(it(si,ti,wi));
        its[i].val=si;
        match.emplace_back(-1);
    }
    sort(its.begin(),its.end(),compare);
    for (int i=0,j=0;i<N;i++,j++){
        j=(j>its[i].s)?j:its[i].s;
        p.emplace_back(j);
    }
    for (int i = 0; i < N; ++i) {
        its[i].val=-its[i].w;
    }
    sort(its.begin(),its.end(),compare);
    ll out=0;
    for (int i = 0; i < N; ++i) {
        int j=0;
        while (p[j]<its[i].s) {
            j++;
        }
        //cout<<j<<" ";
        if (find(i,j)){
            out+=its[i].w;
            //cout<<i<<" "<<its[i].w;
        }
        //cout<<endl;
    }
    cout<<out;
    return 0;
}