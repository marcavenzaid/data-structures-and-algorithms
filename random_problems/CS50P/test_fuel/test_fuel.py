from archive_CS50x.test_fuel.fuel import convert, gauge
import pytest

"""
- convert expects a str in X/Y format as input,
- wherein X is a non-negative integer and Y is a positive integer,
  and returns that fraction as a percentage rounded to the nearest int between 0 and 100, inclusive.

- If X and/or Y is not an integer,
- or if X is greater than Y, then convert should raise a ValueError.

- If Y is 0, then convert should raise a ZeroDivisionError.

gauge expects an int and returns a str that is:
    - "E" if that int is less than or equal to 1,
    - "F" if that int is greater than or equal to 99,
    - and "Z%" otherwise, wherein Z is that same int.
"""

def test_convert():
    assert convert("1/4") == 25
    assert convert("2/4") == 50
    assert convert("4/4") == 100
def test_convert_non_integer():
    with pytest.raises(ValueError):
        assert convert("x/y")
def test_convert_negative_fractions():
    with pytest.raises(ValueError):
        assert convert("-1/4")
    with pytest.raises(ValueError):
        assert convert("1/-4")
    with pytest.raises(ValueError):
        assert convert("-1/-4")
def test_x_greater_than_y():
    with pytest.raises(ValueError):
        assert convert("5/1")
def test_y_is_0():
    with pytest.raises(ZeroDivisionError):
        assert convert("1/0")
def test_gauge_E():
    assert gauge(0) == "E"
    assert gauge(1) == "E"
def test_gauge_F():
    assert gauge(99) == "F"
    assert gauge(100) == "F"
def test_gauge_other():
    assert gauge(25) == "25%"
    assert gauge(50) == "50%"
    assert gauge(75) == "75%"

def main():
    test_convert()
    test_convert_non_integer()
    test_convert_negative_fractions()
    test_x_greater_than_y()
    test_y_is_0()
    test_gauge_E()
    test_gauge_F()
    test_gauge_other()

if __name__ == "__main__":
    main()
