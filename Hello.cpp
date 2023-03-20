#include <iostream>
#include <unordered_map>
#include <string>
#include <vector>
#include <sstream>

using namespace std;

void vector_2dim_space()
{
    int row = 5;
    vector<vector<int>> v(row, { 1,2,3 });
    cout << &v << endl;
    for (int i = 0; i < row; i++)
    {
        string temp;
        stringstream ss;
        ss << "v[" << i << "]";
        ss >> temp;

        cout << temp << "地址：" << &v[i] << " ";
        for (int j = 0; j < 3; j++)
        {
            ss.clear();
            ss << "v[" << i << "]" << "[" << j << "]";
            ss >> temp;
            cout << temp << "地址：" << &v[i][j] << " ";
        }
        cout << endl;
    }
}


int lastStoneWeightII(vector<int>& nums) {
        int sum = 0;
        int m;
        for (int i : nums) {
            sum += i;
        }
        m = sum / 2;
        int n = nums.size();
        vector<int> dp(m + 1);
        for (int i = 0; i <= m; ++i) {
            if (i >= nums[0]) dp[i] = nums[0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = m; j >= nums[i]; --j) {
                dp[j] = max(dp[j], dp[j - nums[i]] + nums[i]);             
            }
        }
        return dp[m];
}

int main() {
    vector_2dim_space();
}
