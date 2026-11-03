
def main():
    s = input("What is the Answer to the Great Question of Life, the Universe, and Everything?")
    s = s.lower().strip()
    if s == "42" or s == "forty two" or s == "forty-two" :
        print("Yes")
    else:
        print("No")

if __name__ == "__main__":
    main()
