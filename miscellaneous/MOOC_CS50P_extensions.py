
def main():
    s = input("File name: ")
    s = s.strip()
    dot = s.rfind(".")
    t = s[dot+1:]
    t = t.lower()

    if t == "gif":
        print("image/gif")
    elif t == "jpg" or t == "jpeg":
        print("image/jpeg")
    elif t == "png":
        print("image/png")
    elif t == "pdf":
        print("application/pdf")
    elif t == "txt":
        print("text/plain")
    elif t == "zip":
        print("application/zip")
    else:
        print("application/octet-stream")

if __name__ == "__main__":
    main()
