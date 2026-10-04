from cs50 import get_float, get_int, get_string

"""
To make things more interesting, first prompt the user with get_int for the half-pyramid’s height, a positive integer between 1 and 8, inclusive.
If the user fails to provide a positive integer no greater than 8, you should re-prompt for the same again.
Then, generate (with the help of print and one or more loops) the desired half-pyramid.
Take care to align the bottom-left corner of your half-pyramid with the left-hand edge of your terminal window.
"""

# Asked for input
n = -1
while (n < 1 or n > 8):
    n = get_int("Height: ")

for i in range(1, n + 1, 1):
    for j in range(n - i):
        print(" ", end="")
    for j in range(i):
        print("#", end="")
    print()
