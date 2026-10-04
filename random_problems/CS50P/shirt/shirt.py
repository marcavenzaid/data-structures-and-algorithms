import PIL
from PIL import Image, UnidentifiedImageError, ImageOps
import sys
from pathlib import Path

def is_valid_and_uncorrupted_image(filepath):
    try:
        with Image.open(filepath) as img:
            img.verify()
            return True
    except (UnidentifiedImageError, SystemError, IOError) as e:
        # print(e)
        return False

def main():
    if len(sys.argv) < 3:
        sys.exit("Too few arguments")
    if len(sys.argv) > 3:
        sys.exit("Too many arguments")

    inputpath = sys.argv[1]
    outputpath = sys.argv[2]

    if not is_valid_and_uncorrupted_image(inputpath):
        sys.exit("Input file is corrupted")

    if Path(inputpath).suffix != Path(outputpath).suffix:
        sys.exit("input and output does not have same file extension")

    try:
        shirt = Image.open("shirt.png")
        img = Image.open(inputpath)
        resized_img = ImageOps.fit(img, shirt.size)
        resized_img.paste(shirt, shirt)
        resized_img.save(outputpath)
    except Exception as e:
        sys.exit(e)

if __name__ == "__main__":
    main()
