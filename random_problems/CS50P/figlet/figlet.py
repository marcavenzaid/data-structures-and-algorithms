import sys
import random
from pyfiglet import Figlet

def main():
    is_random = False

    if len(sys.argv) == 1:
        is_random = True
    elif len(sys.argv) == 3:
        if sys.argv[1] != "-f" and sys.argv[1] != "--font":
            sys.exit("Invalid usage")
    else:
        sys.exit("Invalid usage")

    figlet = Figlet()

    if is_random:
        fs = figlet.getFonts()
        f = random.choice(fs)
        figlet.setFont(font=f)
    else:
        try:
            figlet.setFont(font=sys.argv[2])
        except:
            sys.exit("Invalid usage")

    s = input("Input: ")
    print(figlet.renderText(s))

if __name__ == "__main__":
    main()
