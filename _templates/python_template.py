import sys
input = sys.stdin.readline

MOD = 1000000007

def input_int():
    """Read a single integer from standard input.
    Example input:
    5

    Example usage:
    n = input_int()  # n = 5
    """
    return int(input())

def input_list():
    """Read a list of integers from standard input.
    Example input:
    1 2 3 4 5

    Example usage:
    lst = input_list()  # lst = [1, 2, 3, 4, 5]
    """
    return list(map(int, input().split()))

def input_str():
    """Read a string from standard input and return it as a list of characters.
    Example input:
    hello

    Example usage:
    char_list = input_str()  # char_list = ['h', 'e', 'l', 'l', 'o']
    """
    return list(input().strip())

def input_ints():
    """Read space separated integer variable inputs.
    Example input:
    1 2 3

    Example usage:
    a, b, c = input_ints()  # a = 1, b = 2, c = 3
    """
    return map(int, input().split())