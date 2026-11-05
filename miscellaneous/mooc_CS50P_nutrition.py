
NUTRITION = {
    "apple": 130,
	"avocado": 50,
	"banana": 110,
	"cantaloupe": 50,
	"grapefruit": 60,
	"grapes": 90,
	"honeydew melon": 50,
	"kiwifruit": 90,
	"lemon": 15,
	"lime": 20,
	"mectarine": 60,
	"orange": 80,
	"peach": 60,
	"pear": 100,
	"pineapple": 50,
	"plums": 70,
	"strawberries": 50,
	"sweet cherries": 100,
	"tangerine": 50,
	"watermelon": 80
}

def solve(x):
    x = x.lower()
    if x in NUTRITION:
        return NUTRITION[x]

def main():
    x = input("Input: ")
    c = solve(x)
    if c is not None:
        print(f"Calories: {c}")

if __name__ == "__main__":
    main()
