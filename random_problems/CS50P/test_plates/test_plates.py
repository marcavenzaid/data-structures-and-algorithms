from archive_CS50P.test_plates.plates import is_valid

def test_valid():
    assert is_valid("CS50") == True

def test_invalid():
    assert is_valid("CS05") == False
    assert is_valid("CS50P") == False
    assert is_valid("PI3.14") == False
    assert is_valid("H") == False
    assert is_valid("OUTATIME") == False

def test_beginning_alpha():
    assert is_valid("CS") == True
    assert is_valid("C5") == False

    assert is_valid("0A") == False
    assert is_valid("00") == False
    assert is_valid(" 7") == False

def main():
    test_valid()
    test_invalid()

if __name__ == "__main__":
    main()

