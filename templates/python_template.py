import sys
from collections import defaultdict, deque, Counter
from heapq import heappush, heappop
from bisect import bisect_left, bisect_right
from itertools import accumulate, permutations, combinations
from math import gcd, inf, isqrt

# NOTE: unlike built-in input(), the readline keeps its trailing "\n".
# - int(input()) and input().split() are fine (they ignore whitespace).
# - For the raw string (len, list, indexing), use input().rstrip("\r\n")
# - At EOF it returns '' instead of raising EOFError. 
input = sys.stdin.readline

MOD = 10**9 + 7
# MOD = 998244353

def solve():
    return

def main():
    t = 1
    # t = read_int()

    out = []
    for _ in range(t):
        out.append(solve())
        
    sys.stdout.write("\n".join(out) + "\n")   # one write instead of many print() calls

if __name__ == "__main__":
    main()