import re
import sys


def convert(s):
    pattern = r"^(\d{1,2})(?::(\d{2}))? (AM|PM) to (\d{1,2})(?::(\d{2}))? (AM|PM)$"

    match = re.fullmatch(pattern, s)

    if not match:
        raise ValueError

    hour1, minute1, period1, hour2, minute2, period2 = match.groups()

    hour1 = int(hour1)
    hour2 = int(hour2)
    if minute1:
        minute1 = int(minute1)
    else:
        minute1 = 0

    if minute2:
        minute2 = int(minute2)
    else:
        minute2 = 0

    if not (1 <= hour1 <= 12 and 1 <= hour2 <= 12):
        raise ValueError
    if not (0 <= minute1 <= 59 and 0 <= minute2 <= 59):
        raise ValueError

    if period1 == "AM":
        hour1 = 0 if hour1 == 12 else hour1
    else:
        hour1 = 12 if hour1 == 12 else hour1 + 12
    if period2 == "AM":
        hour2 = 0 if hour2 == 12 else hour2
    else:
        hour2 = 12 if hour2 == 12 else hour2 + 12

    return f"{hour1:02}:{minute1:02} to {hour2:02}:{minute2:02}"

def main():
    print(convert(input("Hours: ")))


if __name__ == "__main__":
    main()
