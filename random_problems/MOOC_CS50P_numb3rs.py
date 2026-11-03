import re
import sys


def validate(ip):
    r"""
    r'...'      Python raw string. Prevents Python from interpreting \ specially.
    ^	        Start of the string
    (?: ... )	A non-capturing group
    [0-9]	    Any digit from 0 to 9
    {1,3}	    Repeat the previous thing 1 to 3 times
    \.	        A literal .
    {3}	        Repeat the entire group exactly 3 times
    $	        End of the string

    is basically saying:
        Start
        -> three groups of 1–3 digits followed by a dot
        -> one final group of 1–3 digits
        -> end
    """
    pattern = r"^(?:[0-9]{1,3}\.){3}[0-9]{1,3}$"

    if (re.search(pattern, ip)
        and all(str(int(x)) == x for x in ip.split("."))
        and all(0 <= int(x) <= 255 for x in ip.split("."))):
        return True
    else:
        return False

def main():
    print(validate(input("IPv4 Address: ")))

if __name__ == "__main__":
    main()
