def solve(camel):
    snake = ""
    for c in camel:
        if c.isupper():
            snake = snake + "_" + c.lower()
        else:
            snake = snake + c

    print(f"snake_case: {snake}")

def main():
    camel = input("camelCase: ")
    solve(camel)

if __name__ == "__main__":
    main()
