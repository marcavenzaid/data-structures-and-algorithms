#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
    // Accept a single command-line argument
    if (argc != 2)
    {
        printf("Usage: ./recover FILE\n");
        return 1;
    }

    // Open the memory card
    FILE *card = fopen(argv[1], "r");
    if (card == NULL)
    {
        printf("Could not open file %s.\n", argv[1]);
        return 1;
    }

    // Create a buffer for a block of data
    uint8_t buffer[512];

    int counter = 0;
    char name[8];
    FILE *output_file = NULL;

    // While there's still data left to read from the memory card
    while (fread(buffer, 1, 512, card) == 512)
    {
        // Create JPEGs from the data

        // check if jpeg image.
        if (buffer[0] == 0xFF && buffer[1] == 0xD8 && buffer[2] == 0xFF && (buffer[3] & 0xF0) == 0xE0)
        {
            if (output_file != NULL)
            {
                fclose(output_file);
            }

            // name the file.
            sprintf(name, "%03i.jpg", counter);
            output_file = fopen(name, "w");
            counter++;
        }

        // If an output file is open, write the buffer to it
        if (output_file != NULL)
        {
            fwrite(buffer, 1, 512, output_file);
        }
    }

    // Free any remaining resources
    if (output_file != NULL)
    {
        fclose(output_file);
    }
    fclose(card);
}
