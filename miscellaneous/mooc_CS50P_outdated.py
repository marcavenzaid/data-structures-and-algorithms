months = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December"
]

def main():
    while True:
        try:
            s = input("Date: ").strip()

            if s.find(",") != -1:
                md, y = s.split(",")
                m, d = md.split(" ")

                for i, _m in enumerate(months):
                    if _m == m:
                        m = str(i + 1)
            else:
                m, d, y = s.split("/")

            if int(m) > 12:
                continue
            if int(d) > 31:
                continue

            if len(m) == 1:
                m = "0" + m
            if len(d) == 1:
                d = "0" + d
            ymd = y + "-" + m + "-" + d

            print(ymd)
        except Exception:
            pass
        else:
            break

if __name__ == "__main__":
    main()

