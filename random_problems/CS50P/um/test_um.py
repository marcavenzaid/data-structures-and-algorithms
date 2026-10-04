import pytest
from archive_CS50P.um.um import count

def test_1():
    assert count("um?") == 1
    assert count("Um, thanks for the album.") == 1

def test_2():
    assert count("Um, thanks, um...") == 2

def test_3():
    assert count("Um, eto, sono, eto, um, um..") == 3
