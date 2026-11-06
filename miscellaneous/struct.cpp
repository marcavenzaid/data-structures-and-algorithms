#include <iostream>

struct Student {
    int id;
    std::string name;
    int age;
    int year;

    Student() {
        id = 0;
        name = "No name";
        age = 0;
        year = 1;
    }

    Student(int id, std::string name, int age, int year)
        : id(id), name(name), age(age), year(year) {
    }
} student1;

int main() {
    std::cout << student1.id << "\n" << student1.name << "\n" << student1.age
              << "\n" << student1.year << "\n\n";

    Student student2;
    std::cout << student2.id << "\n" << student2.name << "\n" << student2.age
              << "\n" << student2.year << "\n\n";
    student2.id = 1;
    student2.name = "Foo";
    student2.age = 16;
    student2.year = 1;
    std::cout << student2.id << "\n" << student2.name << "\n" << student2.age
              << "\n" << student2.year << "\n\n";

    Student student3(2, "Aliza", 20, 4);
    std::cout << student3.id << "\n" << student3.name << "\n" << student3.age
              << "\n" << student3.year << "\n\n";

    return 0;
}
