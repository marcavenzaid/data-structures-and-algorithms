import random

def main():

    while True:
        n = int(input("Level: ").strip())
        if n > 0:
            break

    r = random.randint(1, n)
    while True:
        try:
            g = int(input("Guess: ").strip())
        except ValueError:
            continue

        if g < 1:
            continue

        if g < r:
            print("Too small!")
        elif g > r:
            print("Too large!")
        else:
            print("Just right!")
            break

if __name__ == "__main__":
    main()
