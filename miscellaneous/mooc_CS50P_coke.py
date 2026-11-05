
def solve():
    amount_due = 50

    while(amount_due > 0):
        print(f"Amount Due: {amount_due}")
        p = int(input("Insert Coin: "))
        if p == 25 or p == 10 or p == 5:
            amount_due -= p

    change_owed = amount_due
    if (change_owed < 0):
        change_owed *= -1

    print(f"Change Owed: {change_owed}")

def main():
    solve()

if __name__ == "__main__":
    main()
