# import string

def shorten(s):
    x = ""
    for _ in s:
        # if _ not in "aeiouAEIOU" and not _.isdigit() and _ not in string.punctuation:
        if _ not in "aeiouAEIOU":
            x += _
    return x

def main():
    s = input("Input: ")
    output = shorten(s)
    print(f"Output: {output}")

if __name__ == "__main__":
    main()
