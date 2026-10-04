def main():
    g = {}

    while True:
        try:
            s = input("").strip().upper()

            if s not in g:
                g[s] = 1
            else:
                g[s] = g[s] + 1
        except EOFError:
            break
        except Exception:
            pass

    for k, v in sorted(g.items(), key=lambda x: x[0]):
        print(f"{v} {k}")

if __name__ == "__main__":
    main()
