

def main():
    while True:
        try:
            s = input("Fraction: ")
            dd, dr = s.split("/")
            dd = int(dd)
            dr = int(dr)
            if dd < 0 or dd > dr or dr == 0:
                continue
            q = dd/dr
        except (ValueError, ZeroDivisionError):
            pass
        else:
            break

    qp = int(round(q * 100))
    if qp <= 1:
        print("E")
    elif qp >= 99:
        print("F")
    else:
        print(f"{qp}%")

if __name__ == "__main__":
    main()
