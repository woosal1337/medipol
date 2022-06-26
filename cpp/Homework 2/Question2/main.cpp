//---------------------------------------------------------------------
//
// Name:    Vusal Ismayilov
//
// Course:  Computer Engineering, 2nd Year
//
//  Question: Homework 2; Question 2
//
// Student ID: 64190012
//
// Purpose: Define a patient structure to keep the name, a constant ID, and
// the doctor(s) treating the patient. Notice that you need to keep the number
// of doctors for each patient. Define the doctor as another structure where you
// keep name, specialty, office number and age.
//
//---------------------------------------------------------------------

#include<iostream>

#include<fstream>

#include <sstream>

using namespace std;

struct patient {
    string name;
    int ID;
    int numberOfDoctors;
};

struct doctor {
    string name;
    string specialty;
    int officeNumber;
    int age;
};

void patientDisplay(patient p[3]) {

    cout << "\nPatient Information: " << endl;

    cout << endl;

    for (int i = 0; i < 3; i++) {

        cout << "Name of the Patient: " << p[i].name << endl;

        cout << "ID of the Patient: " << p[i].ID << endl;

        cout << "Number of doctors: " << p[i].numberOfDoctors << endl;

        cout << endl;

    }
}

void displayDoctors(doctor p[7]) {

    cout << "\nDoctor Information: " << endl;

    cout << endl;

    for (int i = 0; i < 7; i++) {

        cout << "Name of the Doctor: " << p[i].name << endl;

        cout << "Specialty of the Doctor: " << p[i].specialty << endl;

        cout << "Office Number of the Doctor: " << p[i].officeNumber << endl;

        cout << "Age of the Doctor: " << p[i].age << endl;

        cout << endl;

    }
}

void doctorInfo(doctor p[7]) {

    cout << "Now the program will read 7 Doctors Data: " << endl;

    cout << endl;

    for (int i = 0; i < 7; i++) {

        cout << "Enter Doctor's Name: ";

        cin >> p[i].name;

        cout << "\nEnter Doctor's Specialty': ";

        cin >> p[i].specialty;

        cout << "\nEnter Doctor's Office Number: ";

        cin >> p[i].officeNumber;

        cout << "\nEnter Doctor's Age: " << endl;

        cin >> p[i].age;
    }
}

void patientInfo(patient p[3]) {

    cout << "Now the program will read 3 Patients Data: " << endl;

    cout << endl;

    for (int i = 0; i < 3; i++) {

        cout << "Enter Patient Name: ";

        cin >> p[i].name;

        cout << "\nEnter Patient ID: ";

        cin >> p[i].ID;

        cout << "\nEnter number of doctors treating the patient: ";

        cin >> p[i].numberOfDoctors;

        cout << endl << endl;
    }
}

void doctorEqualCheck(doctor p1, doctor p2) {
    int res = 0;

    if (p1.officeNumber == p2.officeNumber) {

        res = (p1.name).compare(p2.name);

        if (res == 0)

            cout << "Doctor's is equal." << endl;

    }
}

void patientCheck(patient p1, patient p2) {

    if (p1.ID > p2.ID) {

        if (p1.numberOfDoctors > p2.numberOfDoctors) {

            cout << "Patient Name " << p1.name << " should be treated first." << endl;

        } else {

            cout << "Patient can be treated in any order" << endl;

        }

    } else if (p2.ID > p1.ID) {

        if (p1.numberOfDoctors < p2.numberOfDoctors) {

            cout << "Patient Name " << p2.name << " should be treated first." << endl;

        } else {

            cout << "Patient can be treated in any order" << endl;
        }
    }
}

void dataRead(char * s, patient p1[3], doctor p2[7]) {

    ifstream file;

    string line;

    int checkDoctor = -1, checkPatient = -1, temp = 0;

    file.open(s);

    while (!file.eof()) {

        getline(file, line);

        checkDoctor = line.compare("Doctors:");

        checkPatient = line.compare("Patients:");

        if (checkDoctor == 0) {

            for (int i = 0; i < 7; i++) {

                getline(file, line);

                p2[i].name = line;

                getline(file, line);

                p2[i].specialty = line;

                getline(file, line);

                stringstream offNum(line);

                offNum >> temp;

                p2[i].officeNumber = temp;

                getline(file, line);

                stringstream Age(line);

                Age >> temp;

                p2[i].age = temp;

            }

        } else if (checkPatient == 0) {

            for (int i = 0; i < 3; i++) {

                getline(file, line);

                p1[i].name = line;

                getline(file, line);

                stringstream id(line);

                id >> temp;

                p1[i].ID = temp;

                getline(file, line);

                stringstream nod(line);

                nod >> temp;

                p1[i].numberOfDoctors = temp;
            }
        }
    }
    file.close();

    ofstream firstFile, secondFile;

    firstFile.open("Doctors.txt");

    firstFile << "Doctors: \n" << endl;

    for (int i = 0; i < 7; i++) {

        firstFile << "Doctor Name: " << p2[i].name << endl;

        firstFile << "Doctor Specialty: " << p2[i].specialty << endl;

        firstFile << "Doctor OfficeNumber: " << p2[i].officeNumber << endl;

        firstFile << "Doctor Age: " << p2[i].age << endl;

        firstFile << endl;
    }

    secondFile.close();

    secondFile.open("Patients.txt");

    secondFile << "Patients: \n" << endl;

    for (int i = 0; i < 3; i++) {

        secondFile << "Patient Name: " << p1[i].name << endl;

        secondFile << "Patient ID: " << p1[i].ID << endl;

        secondFile << "Patient's attending doctor count: " << p1[i].numberOfDoctors << endl;

        secondFile << endl;

    }
}

int main() {

    patient patientData[3];

    doctor doctorData[7];

    char * s = (char * )
            "Data.txt";

    cout << "Output for the function which reads the values from text file" << endl << endl;

    dataRead(s, patientData, doctorData);

    patientDisplay(patientData);

    displayDoctors(doctorData);

    cout << "Patient Checker for patients: " << patientData[0].name << " & " << patientData[1].name << endl;

    patientCheck(patientData[0], patientData[1]);

    cout << "Equality Checker for doctors: " << doctorData[0].name << " & " << doctorData[0].name << endl;

    doctorEqualCheck(doctorData[0], doctorData[1]);

    cout << "\n\nUser Prompt function to read the data: " << endl << endl;

    doctorInfo(doctorData);

    patientInfo(patientData);

    cout << "\nDisplay output for user prompt data: " << endl << endl;

    patientDisplay(patientData);

    displayDoctors(doctorData);
}
