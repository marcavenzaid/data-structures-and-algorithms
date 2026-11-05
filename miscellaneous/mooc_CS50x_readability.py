from cs50 import get_float, get_int, get_string

SENTENCE_END_INDICATOR = {'.', '!', '?'}


def count_letters(text):
    count = 0
    for t in text:
        if t.isalpha():
            count += 1
    return count


def count_words(text):
    count = 0
    for t in text:
        if (t == " "):
            count += 1
    return count + 1


def count_sentences(text):
    count = 0

    for t in text:
        is_sentence_end_indicator = False
        for j in SENTENCE_END_INDICATOR:
            if (t == j):
                is_sentence_end_indicator = True
                break
        if (is_sentence_end_indicator):
            count += 1

    return count


def coleman_liau_index(letters_count, words_count, sentences_count):
    L = (letters_count / words_count) * 100
    S = (sentences_count / words_count) * 100

    return 0.0588 * L - 0.296 * S - 15.8


"""
* Recall that the Coleman-Liau index is computed as 0.0588 * L - 0.296 * S - 15.8, where L is the average number of letters per 100 words in the text,
  and S is the average number of sentences per 100 words in the text.

* Use get_string from the CS50 Library to get the user’s input, and print to output your answer.

* Your program should count the number of letters, words, and sentences in the text.
  You may assume that a letter is any lowercase character from a to z or any uppercase character from A to Z,
  any sequence of characters separated by spaces should count as a word, and that any occurrence of a period,
  exclamation point, or question mark indicates the end of a sentence.

* Your program should print as output "Grade X" where X is the grade level computed by the Coleman-Liau formula, rounded to the nearest integer.

* If the resulting index number is 16 or higher (equivalent to or greater than a senior undergraduate reading level),
  your program should output "Grade 16+" instead of giving the exact index number.
  If the index number is less than 1, your program should output "Before Grade 1".
"""

text = get_string("Text: ")

letters_count = count_letters(text)

words_count = count_words(text)

sentences_count = count_sentences(text)

index = coleman_liau_index(letters_count, words_count, sentences_count)

rounded_index = round(index)

if rounded_index < 1:
    print("Before Grade 1")
elif (rounded_index >= 16):
    print("Grade 16+")
else:
    print(f"Grade {rounded_index}")
