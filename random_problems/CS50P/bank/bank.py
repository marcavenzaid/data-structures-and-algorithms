

def main():
    s = input("Greeting: ")
    s = s.strip().lower()

    if s[:5] == "hello":
        print("$0")
    elif s[0] == "h":
        print("$20")
    else:
        print("$100")

if __name__ == "__main__":
    main()
