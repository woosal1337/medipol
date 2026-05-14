#include <sstream>
#include <vector>
#include <iostream>
using namespace std;

vector<int> parseInts(string str) {
    // Complete this function
    for (int i=0;i<str.size();i++)
    {
        if (str[i]!=',')
            cout<<str[i];
        else
            cout<<"\n";
    }
}

int main() {
    string str;
    cin >> str;
    vector<int> integers = parseInts(str);

    return 0;
}