#include <cs50.h>
#include <stdio.h>

int main(void)
{
    // Ask user for height
    int height = -1;

    // If height is less than 1 or greater than 8 (or not an integer at all), ask height again.
    do
    {
        height = get_int("Height: ");
    }
    while (height < 1 || height > 8);

    // Loop from 1 through height:
    for (int i = 1; i <= height; i++)
    {
        // On iteration i, print i hashes and then a newline
        int spaces = height - i;
        for (int j = 0; j < spaces; j++)
        {
            printf(" ");
        }
        for (int j = 0; j < i; j++)
        {
            printf("#");
        }
        printf("\n");
    }
}
