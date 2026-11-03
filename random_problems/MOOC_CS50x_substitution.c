#include <cs50.h>
#include <ctype.h>
#include <math.h>
#include <stdio.h>
#include <string.h>

bool has_space(string s)
{
    for (int i = 0; i < strlen(s); i++)
    {
        if (s[i] == ' ')
        {
            return true;
        }
    }
    return false;
}

bool key_has_error(string s)
{
    // Check if not equal to 26 characters.
    if (strlen(s) != 26)
    {
        printf("Key must contain 26 characters.");
        return true;
    }

    // Check if there is a non alphabetic character.
    for (int i = 0; i < strlen(s); i++)
    {
        if (!isalpha(s[i]))
        {
            printf("Input has error: There is a non alphabetic character.");
            return true;
        }
    }

    // Check if not containing each letter exactly once.
    int ALPHA_UPPERCASE[26];
    for (int i = 0; i < 26; i++)
    {
        ALPHA_UPPERCASE[i] = 0;
    }
    for (int i = 0; i < strlen(s); i++)
    {
        int index = toupper(s[i]) - 'A';
        if (ALPHA_UPPERCASE[index] == 0)
        {
            ALPHA_UPPERCASE[index] = 1;
            continue;
        }
        if (ALPHA_UPPERCASE[index] == 1)
        {
            // If true, then there is a duplicate.
            printf("Input has error: Not containing each letter exactly once.");
            return true;
        }
    }

    return false;
}

int alpha_index(char c)
{
    if (isupper(c))
    {
        return c - 'A';
    }
    return c - 'a';
}

string ciphertext(string plaintext, string key)
{
    for (int i = 0; i < strlen(plaintext); i++)
    {
        char p = plaintext[i];

        if (!isalpha(p))
        {
            plaintext[i] = p;
            continue;
        }

        int p_index = alpha_index(p);

        if (isupper(p))
        {
            plaintext[i] = toupper(key[p_index]);
        }
        else
        {
            plaintext[i] = tolower(key[p_index]);
        }
    }
    return plaintext;
}

int main(int argc, string argv[])
{
    if (argc == 1 || argc > 2)
    {
        printf("Usage: ./substitution key");
        return 1;
    }
    string key = argv[1];
    // printf("%s\n", key);
    if (key_has_error(key))
    {
        return 1;
    }

    string plaintext = get_string("plaintext: ");

    printf("ciphertext: %s", ciphertext(plaintext, key));
    printf("\n");
    return 0;
}