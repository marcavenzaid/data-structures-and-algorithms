#include <iostream>
#include <fstream>
#include <string>

int main() {
    std::ofstream file;
    file.open("test.txt");
    file << "I have mixed feelings about C++.\n";
    file.close();
    // You also have the option of specifying a path for your file in the open
    // function, since it can be in a location other than that of your project.

    // Because of file stream's constructor and destructor;
    // the destructor closes the file if you forgot to close it, and
    // the constructor can also open the file instead of calling open().
    // So, the code can be reduced to.
    std::ofstream file2("test2.txt");
    file2 << "Shortcut.\n";

    // Under certain circumstances, such as when you don't have file
    // permissions, the open function can fail.
    // The is_open() member function checks whether the file is open and
    // ready to be accessed.
    std::ofstream file3("test3.txt");
    if (file3.is_open()) {
        file3 << "Just checking.\n";
    }
    else {
        std::cout << "Something went wrong";
    }
    file3.close();

    // An optional second parameter of the open function defines the mode in
    // which the file is opened. This list shows the supported modes.
    // All the flags can be combined using the bitwise operator OR (|).
    // ex.
    std::ofstream file4;
    file4.open("test4.txt", std::ios::out | std::ios::app );
    file4 << "Append this.\n";

    // Reading from file.
    std::string line;
    std::ifstream file5("test.txt");
    while (getline(file5, line)) {
        std::cout << line << "\n";
    }
    file5.close();

    const int x = 5;
    std::cout << x-1;

    return 0;
}
