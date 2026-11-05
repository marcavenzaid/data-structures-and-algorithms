def mass_energy_equivalence(m):
    c = 300000000
    E = m * c**2
    return E

def main():
    m = int(input("m: "))
    E = mass_energy_equivalence(m)
    print(f"E: {E}")

if __name__ == "__main__":
    main()
