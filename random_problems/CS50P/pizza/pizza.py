import csv
from tabulate import tabulate
import sys
from pathlib import Path

def main():

    if len(sys.argv) < 2:
        sys.exit("Too few arguments")
    if len(sys.argv) > 2:
        sys.exit("Too many arguments")
    if Path(sys.argv[1]).suffix != ".csv":
        sys.exit("File is not a CSV")

    file_name = sys.argv[1]

    try:
        with open(file_name, "r") as f:
            all_data = list(csv.DictReader(f))
            # print(all_data)
            print(tabulate(all_data, headers="keys", tablefmt="grid"))
    except FileNotFoundError as e:
        sys.exit(e)

if __name__ == "__main__":
    main()
