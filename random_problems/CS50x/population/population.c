#include <cs50.h>
#include <stdio.h>

int main(void)
{
    // TODO: Prompt for start size
    int start_size;
    do
    {
        start_size = get_int("Start size: ");
    }
    while (start_size < 9);

    // TODO: Prompt for end size
    int end_size;
    do
    {
        end_size = get_int("End size: ");
    }
    while (end_size < start_size);

    // TODO: Calculate number of years until we reach threshold
    int years = 0;
    int population = start_size;
    while (population < end_size)
    {
        int new_born = population / 3;
        int pass_away = population / 4;
        population = population + new_born - pass_away;
        years++;
    }

    // TODO: Print number of years
    printf("Years: %i\n", years);
}
