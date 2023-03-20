#include <iostream>
#include <unordered_map>
#include <string>
#include <vector>

using namespace std;

// exercise 7.4, 7.5, 7.9, 7.15
class Person {
friend istream& read(istream&, Person&);
friend ostream& print(ostream&, Person&);
public:
    Person() = default;
    Person(string& name) : name(name) {};
    Person(string& name, string& address) : name(name), address(address) {}
    string getName() const {return name;}
    string getAddress() const {return address;}
private:;
    string name;
    string address;

};
istream& read(istream& is, Person& p) {
    is >> p.name >> p.address;
    return is;
}
ostream& print(ostream& os, Person& p) {
    os << p.name << " " << p.address;
    return os;
}

int main() {
    string name = "zhangwan";
    Person p(name);
    print(cout, p);
}



