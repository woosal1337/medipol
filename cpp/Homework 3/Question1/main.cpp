//---------------------------------------------------------------------
//
// Name:    Vusal Ismayilov
//
// Course:  Computer Engineering, 2nd Year
//
// Student ID: 64190012
//
// Purpose: Given code below is going to take user inputs, such as
// a doctor's name, age, SSN, office number, YOE, patients, patient's
// name, age, SSN, and creates a DB related to it.
//
//---------------------------------------------------------------------


#include <iostream>

using namespace std;
const int LEN = 80;

class Doctor {

private:

    char name[LEN];
    char age[LEN];
    char ssn[LEN];
    char officeNumber[LEN];
    char yoe[LEN];
    char patients[1000];

public:

    void getDoctor() {
        cout << "Enter the Doctor Name : ";
        cin >> name;

        cout << "Enter the age of the Doctor: ";
        cin >> age;

        cout << "Enter the Social Security Number of the Doctor : ";
        cin >> ssn;

        cout << "Enter the Office Number of the Doctor : ";
        cin >> officeNumber;

        cout << "Enter the Doctor's Years of Experience: ";
        cin >> yoe;

        cout << "Enter the name of the patients : ";
        cin >> patients;

    }

    void showDoctor() {
        cout << "\nDoctor's Name : " << name;
        cout << "\nDoctor's Age : " << age;
        cout << "\nDoctor's Social Security Number : " << ssn;
        cout << "\nDoctor's Office Number : " << officeNumber;
        cout << "\nDoctor's Years of Experience : " << yoe;
        cout << "\n Doctor's Patients : " << patients;
    }
};

class Patient {

private:

    char name[LEN];
    char age[LEN];
    char ssn[LEN];

public:

    void getPatient() {
        cout << "\nPatient's Name : ";
        cin >> name;

        cout << "\nPatient's Age : ";
        cin >> age;

        cout << "\nPatient's Social Security Number : ";
        cin >> ssn;

    }

    void showPatient() {
        cout << "\nPatient Name : " << name;
        cout << "\nPatient's Age : " << age;
        cout << "\n Patient's Social Security Number : " << ssn;

    }
};



class Amount {

private:

    int dues;
    Patient patient;
    Doctor doctor;


public:

    void getdata() {
        patient.getPatient();
        doctor.getDoctor();
        cout << "Enter Dues of Patient : ";
        cin >> dues;
    }

    void showdata() {
        patient.showPatient();
        doctor.showDoctor();
        cout << "\nTotal Dues of Patient : " << dues;
    }
};

int main() {

    Amount a1;

    cout << "\nEnter Data ";
    a1.getdata();
    cout << "\nInserted Data is : \n";
    a1.showdata();
    return 0;

}