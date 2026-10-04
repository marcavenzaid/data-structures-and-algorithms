def is_valid(s):
    if len(s) < 2 or len(s) > 6:
        # print("ERROR 2")
        return False
    if not(s[0].isalpha() and s[1].isalpha()):
        # print("ERROR 1")
        return False
    if not(s.isalnum()):
        # print("ERROR 3")
        return False

    first_digit_found = False
    num_part = False
    for c in s[2:]:
        if c.isdigit():
            num_part = True

            if first_digit_found == False:
                if c == "0":
                    # print("ERROR 4")
                    return False
                else:
                    first_digit_found = True

        if num_part and c.isalpha():
            # print("ERROR 5")
            return False
    return True

def main():
    plate = input("Plate: ")
    if is_valid(plate):
        print("Valid")
    else:
        print("Invalid")

if __name__ == "__main__":
    main()
