import csv
import sys
from pathlib import Path


def main():
    if len(sys.argv) < 3:
        sys.exit("Too few arguments")
    if len(sys.argv) > 3:
        sys.exit("Too many arguments")

    filename1 = sys.argv[1]
    filename2 = sys.argv[2]
    if Path(filename1).suffix != ".csv":
        sys.exit("File 1 is not a csv file")
    if Path(filename2).suffix != ".csv":
        sys.exit("File 2 is not a csv file")

    datatowrite = []

    try:
        with open(filename1, "r") as f1:
            reader = csv.DictReader(f1)
            for line in reader:
                # print(line)
                name = line["name"]
                last, first  = name.split(",")
                last = last.strip()
                first = first.strip()
                house = line["house"]
                datarow = {"first": first, "last": last, "house": house}
                # print(datarow)
                datatowrite.append(datarow)
    except FileNotFoundError as e:
        sys.exit(e)

    # print(datatowrite)
    # print(list(datatowrite[0].keys()))

    if len(datatowrite) < 1:
        sys.exit("No data to write")
    try:
        with open(filename2, "w") as f2:
            writer = csv.DictWriter(f2, fieldnames=list(datatowrite[0].keys()))
            writer.writeheader()
            for row in datatowrite:
                writer.writerow(row)
    except FileNotFoundError as e:
        sys.exit(e)

if __name__ == "__main__":
    main()
