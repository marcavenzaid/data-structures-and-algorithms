

def main():
    s = input("Expression: ").strip()
    x, y, z = s.split(" ")
    x = float(x)
    z = float(z)

    if y == "+":
        o = round(x + z, 1)
    elif y == "-":
        o = round(x - z, 1)
    elif y == "*":
        o = round(x * z, 1)
    elif y == "/":
        o = round(x / z, 1)
    else:
        print("ERROR")

    print(o)

if __name__ == "__main__":
    main()
