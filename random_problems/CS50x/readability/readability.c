#include <cs50.h>
#include <ctype.h>
#include <math.h>
#include <stdio.h>
#include <string.h>

/*
• Your program must prompt the user for a string of text using get_string.
• Your program should count the number of letters, words, and sentences in the text.
  You may assume that a letter is any lowercase character from a to z or any uppercase character from A to Z,
  any sequence of characters separated by spaces should count as a word, and that any occurrence of a period,
  exclamation point, or question mark indicates the end of a sentence.
• Your program should print as output "Grade X" where X is the grade level computed by the Coleman-Liau formula, rounded to the
nearest integer. • If the resulting index number is 16 or higher (equivalent to or greater than a senior undergraduate reading
level), your program should output "Grade 16+" instead of giving the exact index number. If the index number is less than 1, your
program should output "Before Grade 1".
*/

#define array_size(x) (sizeof(x) / sizeof((x)[0]))

int count_letters(string text);
int count_words(string text);
int count_sentences(string text);

char SPACE = ' ';
char SENTENCE_END_INDICATOR[] = {'.', '!', '?'};

int count_letters(string text)
{
    int count = 0;
    for (int i = 0; i < strlen(text); i++)
    {
        if (isalpha(text[i]))
        {
            count++;
        }
    }
    return count;
}

int count_words(string text)
{
    int count = 0;
    for (int i = 0; i < strlen(text); i++)
    {
        if (text[i] == SPACE)
        {
            count++;
        }
    }
    return count + 1;
}

int count_sentences(string text)
{
    int count = 0;

    for (int i = 0; i < strlen(text); i++)
    {
        char c = text[i];

        bool is_sentence_end_indicator = false;
        for (int j = 0; j < array_size(SENTENCE_END_INDICATOR); j++)
        {
            if (c == SENTENCE_END_INDICATOR[j])
            {
                is_sentence_end_indicator = true;
                break;
            }
        }

        if (is_sentence_end_indicator)
        {
            count++;
        }
    }
    return count;
}

double coleman_liau_index(int letters_count, int words_count, int sentences_count)
{
    // average_letters_per_100_words
    double L = ((double) letters_count / (double) words_count) * 100;
    // printf("L: %f\n", L);
    // average_sentences_per_100_words
    double S = ((double) sentences_count / (double) words_count) * 100;
    // printf("S: %f\n", S);

    return 0.0588 * L - 0.296 * S - 15.8;
}

int main(void)
{
    string text = get_string("Text: ");
    // printf("%s\n", text);

    int letters_count = count_letters(text);
    // printf("%d letters\n", letters_count);

    int words_count = count_words(text);
    // printf("%d words\n", words_count);

    int sentences_count = count_sentences(text);
    // printf("%d sentences\n", sentences_count);

    double index = coleman_liau_index(letters_count, words_count, sentences_count);
    // printf("coleman_liau_index: %f\n", index);

    int rounded_index = round(index);

    if (rounded_index < 1)
    {
        printf("Before Grade 1");
    }
    else if (rounded_index >= 16)
    {
        printf("Grade 16+");
    }
    else
    {
        printf("Grade %d", rounded_index);
    }

    printf("\n");
}