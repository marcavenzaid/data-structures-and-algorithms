import csv
import sys


def main():

    # TODO: Check for command-line usage
    if len(sys.argv) != 3:
        print("Missing command-line argument")
        sys.exit(1)

    # TODO: Read database file into a variable
    database_rows = []
    with open(sys.argv[1]) as file:
        reader = csv.DictReader(file)
        fieldnames = reader.fieldnames
        for row in reader:
            database_rows.append(row)

    subsequences = fieldnames[1:]

    # TODO: Read DNA sequence file into a variable
    sequence = ""
    with open(sys.argv[2], "r") as file:
        sequence = file.read()

    # TODO: Find longest match of each STR in DNA sequence
    longest_runs = {}
    for s in subsequences:
        longest_run = longest_match(sequence, s)
        longest_runs[s] = longest_run
        # AGATC_longest_run = longest_match(sequence, "AGATC")
        # AATG_longest_run = longest_match(sequence, "AATG")
        # TATC_longest_run = longest_match(sequence, "TATC")
    # print("AGATC_longest_run: ", AGATC_longest_run)
    # print("AATG_longest_run: ", AATG_longest_run)
    # print("TATC_longest_run: ", TATC_longest_run)

    # TODO: Check database for matching profiles
    matched = False
    for row in database_rows:
        # if row["name"] == "Harry":
        #     print("row[AGATC]: ", row["AGATC"])
        #     print("row[AATG]: ", row["AATG"])
        #     print("row[TATC]: ", row["TATC"])
        # if int(row["AGATC"]) == AGATC_longest_run:
        #     print("yeayaeyaeya")
        # if (int(row["AGATC"]) == AGATC_longest_run and int(row["AATG"]) == AATG_longest_run and int(row["TATC"]) == TATC_longest_run):
        #     print(row["name"])
        #     matched = True

        all_same = True
        for s in subsequences:
            if (int(row[s]) != longest_runs[s]):
                all_same = False

        if all_same:
            print(row["name"])
            matched = True

    if not matched:
        print("No match")


def longest_match(sequence, subsequence):
    """Returns length of longest run of subsequence in sequence."""

    # Initialize variables
    longest_run = 0
    subsequence_length = len(subsequence)
    sequence_length = len(sequence)

    # Check each character in sequence for most consecutive runs of subsequence
    for i in range(sequence_length):

        # Initialize count of consecutive runs
        count = 0

        # Check for a subsequence match in a "substring" (a subset of characters) within sequence
        # If a match, move substring to next potential match in sequence
        # Continue moving substring and checking for matches until out of consecutive matches
        while True:

            # Adjust substring start and end
            start = i + count * subsequence_length
            end = start + subsequence_length

            # If there is a match in the substring
            if sequence[start:end] == subsequence:
                count += 1

            # If there is no match in the substring
            else:
                break

        # Update most consecutive matches found
        longest_run = max(longest_run, count)

    # After checking for runs at each character in seqeuence, return longest run found
    return longest_run


main()
