
def convert(f):
    try:
        x, y = f.split("/")
        x = int(x)
        y = int(y)
        if y == 0:
            raise ZeroDivisionError
        if x < 0 or y < 0 or x > y:
            raise ValueError
        q = x/y
    except (ValueError, ZeroDivisionError):
        raise
    qp = int(round(q * 100))
    return qp

def gauge(p):
    if p <= 1:
        return("E")
    elif p >= 99:
        return("F")
    else:
        return(f"{p}%")

def main():
    while True:
        try:
            f = input("Fraction: ")
            p = convert(f)
            print(f"p = {p}")
            s = gauge(p)
            print(f"s = {s}")
        except (ValueError, ZeroDivisionError) as e:
            print(f"Error: {e}")
        else:
            break

if __name__ == "__main__":
    main()
