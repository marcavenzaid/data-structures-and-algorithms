import pytest
from archive_CS50P.jar.jar import Jar

def test_init():
    jar = Jar()
    assert jar.capacity == 12
    assert jar.size == 0

    jar = Jar(5)
    assert jar.capacity == 5
    assert jar.size == 0

    with pytest.raises(ValueError):
        Jar(-1)

    with pytest.raises(ValueError):
        Jar("5")

def test_str():
    jar = Jar()
    assert str(jar) == ""

    jar.deposit(1)
    assert str(jar) == "🍪"

    jar.deposit(11)
    assert str(jar) == "🍪🍪🍪🍪🍪🍪🍪🍪🍪🍪🍪🍪"

def test_capacity():
    jar = Jar(5)
    assert jar.capacity == 5

    jar.deposit(5)
    assert jar.capacity == 5

def test_deposit():
    jar = Jar(5)

    jar.deposit(3)
    assert jar.size == 3

    jar.deposit(2)
    assert jar.size == 5

    with pytest.raises(ValueError):
        jar.deposit(1)

    assert jar.size == 5

def test_withdraw():
    jar = Jar(5)

    jar.deposit(5)

    jar.withdraw(2)
    assert jar.size == 3

    jar.withdraw(3)
    assert jar.size == 0

    with pytest.raises(ValueError):
        jar.withdraw(1)

    assert jar.size == 0

def main():
    test_init()
    test_str()
    test_capacity()
    test_deposit()
    test_withdraw()

if __name__ == "__main__":
    main()
