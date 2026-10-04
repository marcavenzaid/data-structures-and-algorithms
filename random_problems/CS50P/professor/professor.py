import random

def get_level():
    while True:
        try:
            l = int(input("Level: ").strip())
            if l >= 1 and l <= 3:
                return l
        except ValueError:
            continue

def generate_integer(level):
    if level == 1:
        return random.randint(0, 9)
    elif level == 2:
        return random.randint(10, 99)
    elif level == 3:
        return random.randint(100, 999)

def main():
    l = get_level()

    score = 0
    for i in range(10):
        r0 = generate_integer(l)
        r1 = generate_integer(l)
        sum = r0 + r1

        correct = False
        wrongcount = 0
        while not correct:
            # print(f"{r0} + {r1} = ", end="")
            try:
                x = int(input(f"{r0} + {r1} = "))
            except ValueError:
                print("EEE")
                wrongcount += 1

            if x == sum:
                score += 1
                correct = True
            else:
                print("EEE")
                wrongcount += 1
                
            if wrongcount >= 3:
                print(f"{r0} + {r1} = {sum}")
                break

    print(f"Score: {score}")

if __name__ == "__main__":
    main()
