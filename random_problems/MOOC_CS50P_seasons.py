from datetime import date
import inflect
import sys

def get_birth_date():
    birthday = input("Date of birth: ")
    try:
        return date.fromisoformat(birthday)
    except ValueError:
        sys.exit("Invalid date")

def calculate_minutes(birth_date, today):
    return (today - birth_date).days * 24 * 60

def main():
    birth_date = get_birth_date()
    today = date.today()

    minutes = (today - birth_date).days * 24 * 60
    p = inflect.engine()
    print(p.number_to_words(minutes, andword="").capitalize() + " minutes")

if __name__ == "__main__":
    main()

