
def convert(time):
    h, m  = time.split(":")
    h = int(h)
    m = int(m)

    mf = m/60
    tf = h + mf
    return tf

def main():
    t = input("What time is it? ").strip()
    tf = convert(t)

    if tf >= 7 and tf <= 8:
        print("breakfast time")
    elif tf >= 12  and tf <= 13:
        print("lunch time")
    elif tf >= 18 and tf <= 19:
        print("dinner time")

if __name__ == "__main__":
    main()
