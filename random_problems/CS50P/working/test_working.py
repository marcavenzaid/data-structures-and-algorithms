from archive_CS50P.working.working import convert
import pytest

def test_convert():
    assert convert("9 AM to 5 PM") == "09:00 to 17:00"
    assert convert("9:00 AM to 5:00 PM") == "09:00 to 17:00"
    assert convert("10 PM to 8 AM") == "22:00 to 08:00"
    assert convert("10:30 PM to 8:50 AM") == "22:30 to 08:50"

def test_value_error_convert():
    with pytest.raises(ValueError):
        convert("9:60 AM to 5:60 PM")
        convert("13:00 AM to 5 PM")

def test_value_error_extract_hours_minutes():
    with pytest.raises(ValueError):
        convert("9 AM -  5 PM")
        convert("9:00 AM - 17:00 PM")

def main():
    test_convert()
    test_value_error_convert()
    test_value_error_extract_hours_minutes()

if __name__ == "__main__":
    main()
