import emoji

def main():
    s = input().strip()
    e = emoji.emojize(s, language='alias')
    print(e)

if __name__ == "__main__":
    main()

