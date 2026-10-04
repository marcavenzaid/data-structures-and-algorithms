import sys
from pathlib import Path

def main():
    # print(sys.argv[1].rsplit(".", 1)[1])
    if len(sys.argv) < 2:
        sys.exit("Too few arguments")
    if len(sys.argv) > 2:
        sys.exit("Too many arguments")

    filename = sys.argv[1]
    if Path(filename).suffix != ".py":
        sys.exit("Not a python file")

    lines_count = 0

    try:
        with open(filename, "r") as file:
            for line in file:
                line = line.strip()
                # print(line)
                if line != "" and line[0] != "#":
                    lines_count += 1
    except(FileNotFoundError) as e:
        sys.exit(e)

    print(lines_count)

if __name__ == "__main__":
    main()
