from archive_CS50P.test_bank.bank import value


def test_hello():
    assert value("hello") == 0
    assert value("hello xxx") == 0

def test_h():
    assert value("h") == 20
    assert value("hi") == 20

def test_other():
    assert value("alksdjaweugianwe") == 100
    assert value("xhhhlkoasdfhhhhh") == 100

def test_case_insensitivity():
    assert value("HELLO") == 0
    assert value("HELLO xxx") == 0
    assert value("H") == 20
    assert value("HI") == 20
    assert value("AKJSFGHIQUWCN") == 100
    assert value("XHHHHASKLDHQWIYC") == 100

def main():
    test_hello()
    test_h()
    test_other()

if __name__ == "__main__":
    main()
