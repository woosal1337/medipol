#include <iostream>
#include <cstdio>
using namespace std;

/*
Add `int max_of_four(int a, int b, int c, int d)` here.
*/

int max_of_four(int a, int b, int c, int d){
    int theMax;

    theMax = a;

    if (b > theMax) {
        theMax = b;
    }

    if(c > theMax) {
        theMax = c;
    }

    if(d > theMax) {
        theMax = d;
    }

    return theMax;

}


int main() {
    int a, b, c, d;
    scanf("%d %d %d %d", &a, &b, &c, &d);
    int ans = max_of_four(a, b, c, d);
    printf("%d", ans);

    return 0;
}