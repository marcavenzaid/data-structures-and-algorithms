def value(g):
    g = g.strip().lower()

    if g[:5] == "hello":
        return 0
    elif g[0] == "h":
        return 20
    else:
        return 100


def main():
    g = input("Greeting: ")
    x = value(g)
    print(x)

if __name__ == "__main__":
    main()
