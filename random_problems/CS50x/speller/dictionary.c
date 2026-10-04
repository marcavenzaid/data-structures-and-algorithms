// Implements a dictionary's functionality

#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>

#include "dictionary.h"

// Represents a node in a hash table
typedef struct node
{
    char word[LENGTH + 1];
    struct node *next;
} node;

// TODO: Choose number of buckets in hash table
const unsigned int N = 26;

// Hash table
node *table[N];

// Returns true if word is in dictionary, else false
bool check(const char *word)
{
    int len = strlen(word);
    char lowercaseword[len + 1];

    for (int i = 0; i < len; i++)
    {
        lowercaseword[i] = tolower(word[i]);
    }
    lowercaseword[len] = '\0';

    node *cursor = table[hash(lowercaseword)];
    while (cursor != NULL)
    {
        if (strcasecmp(word, cursor->word) == 0)
        {
            return true;
        }
        cursor = cursor->next;
    }

    return false;
}

// Hashes word to a number
unsigned int hash(const char *word)
{
    // TODO: Improve this hash function
    return toupper(word[0]) - 'A';
}

// Loads dictionary into memory, returning true if successful, else false
bool load(const char *dictionary)
{
    FILE *source = fopen(dictionary, "r");

    // Check if file opening was successful
    if (source == NULL)
    {
        return false;
    }

    char word[LENGTH + 1]; // Assuming the max length of a word is less than 100 characters

    // Read each word in the file using fscanf
    while (fscanf(source, "%s", word) != EOF)
    {
        // TODO: Allocate memory for new word
        node *n = malloc(sizeof(node));

        if (n == NULL)
        {
            return false;
        }

        strcpy(n->word, word);
        // n->next = NULL;

        // Add each word to the hash table

        // Hash the word to obtain its hash value
        int hash_value = hash(n->word);

        // Insert the new node into the hash table (using the index specified by its hash value)
        n->next = table[hash_value];
        table[hash_value] = n;
    }

    fclose(source);

    return true;
}

// Returns number of words in dictionary if loaded, else 0 if not yet loaded
unsigned int size(void)
{
    int counter = 0;

    for (int i = 0; i < N; i++)
    {
        node *cursor = table[i];

        while (cursor != NULL)
        {
            counter++;
            cursor = cursor->next;
        }
    }

    return counter;
}

// Unloads dictionary from memory, returning true if successful, else false
bool unload(void)
{
    for (int i = 0; i < N; i++)
    {
        node *cursor = table[i];
        while (cursor != NULL)
        {
            node *temp = cursor;
            cursor = cursor->next;
            free(temp);
        }
        free(cursor);
    }

    return true;
}
