#include <cs50.h>
#include <ctype.h>
#include <stdio.h>
#include <string.h>

// Points assigned to each letter of the alphabet
int POINTS[] = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
int POINTS_LEN = 26;

int ASCII_OFFSET_UPPER_CASE_A = 65;
int ASCII_OFFSET_LOWER_CASE_A = 97;

int compute_score(string word);

int main(void)
{
    // Get input words from both players
    string word1 = get_string("Player 1: ");
    string word2 = get_string("Player 2: ");

    // Score both words
    int score1 = compute_score(word1);
    int score2 = compute_score(word2);

    // TODO: Print the winner
    if (score1 > score2)
    {
        printf("Player 1 wins!");
    }
    else if (score1 < score2)
    {
        printf("Player 2 wins!");
    }
    else
    {
        printf("Tie!");
    }
    printf("\n");
}

int compute_score(string word)
{
    // TODO: Compute and return score for string
    int score = 0;

    for (int i = 0; i < strlen(word); i++)
    {
        char c = word[i];
        int points_index;
        if (isupper(c))
        {
            points_index = c - ASCII_OFFSET_UPPER_CASE_A;
        }
        else
        {
            points_index = c - ASCII_OFFSET_LOWER_CASE_A;
        }

        if (points_index < 0 || points_index > POINTS_LEN)
        {
            score += 0;
        }
        else
        {
            score += POINTS[points_index];
        }
    }

    return score;
}
