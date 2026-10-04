from cs50 import get_float, get_int, get_string

"""
You can do this exactly as you did in Problem Set 1,
except that your program this time should be written in Python,
and you should assume that the user will input their change in dollars (e.g., 0.50 dollars instead of 50 cents).
"""

"""
Use get_float from the CS50 Library to get the user’s input and print to output your answer.
Assume that the only coins available are quarters (25¢), dimes (10¢), nickels (5¢), and pennies (1¢).

    We ask that you use get_float so that you can handle dollars and cents, albeit sans dollar sign.
    In other words, if some customer is owed $9.75 (as in the case where a newspaper costs 25¢ but the customer pays with a $10 bill),
    assume that your program’s input will be 9.75 and not $9.75 or 975. However, if some customer is owed $9 exactly,
    assume that your program’s input will be 9.00 or just 9 but, again, not $9 or 900. Of course,
    by nature of floating-point values, your program will likely work with inputs like 9.0 and 9.000 as well;
    you need not worry about checking whether the user’s input is “formatted” like money should be.

Incidentally, so that we can automate some tests of your code, we ask that your program’s last line of output be only the minimum number of coins possible: an integer followed by a newline.
"""

change = -1

# If the user fails to provide a non-negative value, your program should re-prompt the user for a valid amount again and again until the user complies.
while (change < 0):
    change = get_float("Change: ")

change_hundredfold = change * 100
coins = 0
while (change_hundredfold > 0):

    if (change_hundredfold >= 25):
        change_hundredfold -= 25
    elif (change_hundredfold >= 10):
        change_hundredfold -= 10
    elif (change_hundredfold >= 5):
        change_hundredfold -= 5
    elif (change_hundredfold >= 1):
        change_hundredfold -= 1

    coins += 1

print(coins)
