def shorten(s):
    x = ""
    for _ in s:
        if _.lower() not in "aeiou":
            x += _
    return x

def main():
    s = input("Input: ")
    output = shorten(s)
    print(f"Output: {output}")

if __name__ == "__main__":
    main()
