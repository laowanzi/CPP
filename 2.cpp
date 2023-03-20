#include <iostream>
#include <unordered_map>
#include <string>
#include <vector>
#include <sstream>

using namespace std;

int get(vector<int> nums, int k) {
    int n = nums.size();
    if (n <= k) {
        return n;
    }
    unordered_map<int, int> cnt;
    for (int i = 0; i < k; ++i) {
        ++cnt[nums[i]];
    }
    int i = 0, j = k - 1;
    int len = k;
    int maxlen = 0;
    while (j < n - 1) {
        ++cnt[nums[++j]];
        while (cnt.size() > k) {
            --cnt[nums[i]];
            if (cnt[nums[i]] == 0) {
                cnt.erase(nums[i]);
            }
            ++i;
        }
        len = j - i + 1;
        maxlen = max(maxlen, len);
    }
    return maxlen;
}

int main() {
    int n, k;
    int value;
    vector<int> nums;
    cin >> n >> k;
    while (cin >> value) {
        nums.push_back(value);
    }
    cout << get(nums, 3) << endl;
}