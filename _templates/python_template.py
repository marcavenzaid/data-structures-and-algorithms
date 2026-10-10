import sys
input = sys.stdin.readline

MOD = 1000000007

def input_int() -> int:
    """Read a single integer from standard input.

    Example input:
    5

    Example usage:
    n = input_int()  # n = 5
    """
    return int(input())

def input_int_list() -> list[int]:
    """Read a list of integers from standard input.

    Example input:
    1 2 3

    Example usage:
    lst = input_int_list()  # lst = [1, 2, 3]
    a, b, c = input_int_list()  # a = 1, b = 2, c = 3
    """
    return list(map(int, input().split()))

def input_int_grid(n: int) -> list[list[int]]:
    """Read n lines of space separated integers as a 2D list.

    Example input (n = 2):
    1 2 3
    4 5 6

    Example usage:
    grid = input_int_grid(2)  # grid = [[1, 2, 3], [4, 5, 6]]
    """
    return [input_int_list() for _ in range(n)]

def input_char_list() -> list[str]:
    """Read a string from standard input and return it as a list of characters
    In Python, Strings are immutable.
    So, converting it into list of Characters may help in some cases where we need to modify the string.

    Example input:
    hello

    Example usage:
    char_list = input_char_list()  # char_list = ['h', 'e', 'l', 'l', 'o']
    """
    return list(input().strip())

def input_char_grid(n: int) -> list[list[str]]:
    """Read n lines, each as a list of characters (e.g. mazes, boards).

    Example input (n = 2):
    #.#
    ..#

    Example usage:
    grid = input_char_grid(2)  # grid = [['#', '.', '#'], ['.', '.', '#']]
    """
    return [input_char_list() for _ in range(n)]

def input_str_list() -> list[str]:
    """Read a line of space separated strings from standard input.

    Example input:
    apple banana cherry

    Example usage:
    words = input_str_list()  # words = ['apple', 'banana', 'cherry']
    """
    return input().split()

def input_str_lines(n: int) -> list[str]:
    """Read n strings, each on its own line.

    Example input:
    apple
    banana
    cherry

    Example usage:
    words = input_str_lines(3)  # words = ['apple', 'banana', 'cherry']
    """
    return [input().strip() for _ in range(n)]
