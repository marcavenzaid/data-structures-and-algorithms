import sys

MOD = 10**9 + 7

# NOTE: sys.stdin.readline is faster than built-ininput().
# However, the sys.stdin.readline keeps its trailing "\n".
# - int(input()) and input().split() are fine (they ignore whitespace).
# - For the raw string (len, list, indexing), use input().rstrip("\r\n")
# - At EOF it returns '' instead of raising EOFError. 
# input = sys.stdin.readline

"""
input()

Read a string from standard input. The trailing newline is stripped.
The prompt string, if given, is printed to standard output without a trailing newline before reading input.
"""

def input_int() -> int:
    """Read a single integer from standard input.

    Example input:
    5

    Example usage:
    n = input_int()  # n = 5
    """
    import sys
    input = sys.stdin.readline
    return int(input())

def read_int_list() -> list[int]:
    """Read a list of integers from standard input.

    Example input:
    1 2 3

    Example usage:
    lst = read_int_list()  # lst = [1, 2, 3]
    a, b, c = read_int_list()  # a = 1, b = 2, c = 3
    """
    import sys
    input = sys.stdin.readline
    return list(map(int, input().split()))

def read_int_grid(n: int) -> list[list[int]]:
    """Read n lines of space separated integers as a 2D list.

    Example input (n = 2):
    1 2 3
    4 5 6

    Example usage:
    grid = read_int_grid(2)  # grid = [[1, 2, 3], [4, 5, 6]]
    """
    import sys
    input = sys.stdin.readline
    
    grid = []
    for _ in range(n):
        read_int_list = list(map(int, input().split()))
        grid.append(read_int_list)
    return grid

def read_char_list() -> list[str]:
    """Read a string from standard input and return it as a list of characters
    In Python, Strings are immutable.
    So, converting it into list of Characters may help in some cases where we need to modify the string.

    Example input:
    hello

    Example usage:
    char_list = read_char_list()  # char_list = ['h', 'e', 'l', 'l', 'o']
    """
    import sys
    input = sys.stdin.readline
    return list(input().strip())

def read_char_grid(n: int) -> list[list[str]]:
    """Read n lines, each as a list of characters (e.g. mazes, boards).

    Example input (n = 2):
    #.#
    ..#

    Example usage:
    grid = read_char_grid(2)  # grid = [['#', '.', '#'], ['.', '.', '#']]
    """
    grid = []
    for _ in range(n):
        grid.append(read_char_list())
    return grid

def read_str_list() -> list[str]:
    """Read a line of space separated strings from standard input.

    Example input:
    apple banana cherry

    Example usage:
    words = read_str_list()  # words = ['apple', 'banana', 'cherry']
    """
    import sys
    input = sys.stdin.readline
    return input().split()

def read_lines_until_eof() -> list[str]:
    """Read all remaining lines until EOF, with line endings and
    leading/trailing whitespace removed. Spaces within lines are kept.

    Example input:
    apple
      banana cherry  

    Example usage:
    lines = read_lines_until_eof()  # lines = ['apple', 'banana cherry']
    """
    import sys
    lines = []
    for line in sys.stdin.read().splitlines():
        lines.append(line.strip())
    return lines