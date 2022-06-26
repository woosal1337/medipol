#include <iostream>

using namespace std;

// Defining the general Animal (base) class to be extended by the
// other classes below
class Animal {
public:

    Animal(string name, double weight, int age, double distance) : name_{name}, weight_{weight}, age_{age},
                                                                   distance_{distance} {
        cout << "General Animal" << endl;
    };


    string getName() { return name_; };

    double getWeight() { return weight_; };

    int getAge() { return age_; }

    double getDistance() {
        return distance_;
    }


protected:

    string name_;
    double weight_;
    int age_;
    double distance_;
};


class Mammals : public Animal {
public:

    Mammals(string name, double weight, int age, double distance, int birthPerYear) : Animal{name, weight, age,
                                                                                             distance},
                                                                                      birthPerYear_{birthPerYear} {
        cout << "Mammal" << endl;
    }

    bool operator>=(Animal animal) {
        return (weight_ == animal.getWeight());
    }

    string getName() {
        return name_;
    }

    double getWeight() {
        return weight_;
    }

    int getAge() {
        return age_;
    }

    double getDistance() {
        return distance_;
    }

    int getBirthPerYear() {
        return birthPerYear_;
    }

protected:

    string name_;
    double weight_;
    int age_;
    double distance_;
    int birthPerYear_;

};


class Birds : public Animal {
public:

    Birds(string name, double weight, int age, double distance, int laidEggs) : Animal{name, weight, age, distance},
                                                                                laidEggs_{laidEggs} {
        cout << "Birds" << endl;
    }

    string getName() {
        return name_;
    }

    double getWeight() {
        return weight_;
    }

    int getAge() {
        return age_;
    }

    double getDistance() {
        return distance_;
    }

    int laidEggs() {
        return laidEggs_;
    }

protected:

    string name_;
    double weight_;
    int age_;
    double distance_;
    int laidEggs_;
};

class Fish : public Animal {
public:

    Fish(string name, double weight, int age, double distance = 10, double length = 5, double width = 5,
         double thickness = 5) : Animal{
            name, weight, age, distance}, length_{length}, width_(width), thickness_(thickness) {
        cout << "Fish" << endl;
    }

    string getName() {
        return name_;
    }

    double getWeight() {
        return weight_;
    }

    int getAge() {
        return age_;
    }

    double getDistance() {
        return distance_;
    }

    int laidEggs() {
        return laidEggs_;
    }

    double getLength() {
        return length_;
    }

    double getWidth() {
        return width_;
    }

    double getThickess() {
        return thickness_;
    }

protected:

    string name_;
    double weight_;
    int age_;
    double distance_;
    int laidEggs_;
    double length_;
    double width_;
    double thickness_;
};


int main() {

    return 0;
}