import inflect

def main():
    p = inflect.engine()

    ns = []
    while True:
        try:
            n = input("Name: ")
            ns.append(n)
        except EOFError:
            print()
            break

    j = p.join(ns)
    print(f"Adieu, adieu, to {j}")

if __name__ == "__main__":
    main()
