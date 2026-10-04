from datetime import date
from archive_CS50P.seasons.seasons import calculate_minutes

def test_calculate_minutes():
    assert calculate_minutes(date(2000, 1, 1), date(2000, 1, 2)) == 1440
    assert calculate_minutes(date(2000, 1, 1), date(2001, 1, 1)) == 527040
    assert calculate_minutes(date(2000, 1, 1), date(2004, 1, 1)) == 2103840
    assert calculate_minutes(date(2020, 2, 29), date(2021, 2, 28)) == 525600
