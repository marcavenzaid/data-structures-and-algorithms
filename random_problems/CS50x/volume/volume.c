// Modifies the volume of an audio file

#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

// Number of bytes in .wav header
const int HEADER_SIZE = 44;

int main(int argc, char *argv[])
{
    // Check command-line arguments
    if (argc != 4)
    {
        printf("Usage: ./volume input.wav output.wav factor\n");
        return 1;
    }

    // Open files and determine scaling factor
    FILE *input = fopen(argv[1], "r");
    if (input == NULL)
    {
        printf("Could not open file.\n");
        return 1;
    }

    FILE *output = fopen(argv[2], "w");
    if (output == NULL)
    {
        printf("Could not open file.\n");
        return 1;
    }

    float factor = atof(argv[3]);

    /*
    Complete the implementation of volume.c, such that it changes the volume of a sound file by a given factor.

    The program should accept three command-line arguments.
        The first is input, which represents the name of the original audio file.
        The second is output, which represents the name of the new audio file that should be generated.
        The third is factor, which is the amount by which the volume of the original audio file should be scaled.
        For example, if factor is 2.0, then your program should double the volume of the audio file in input and save the newly
    generated audio file in output. Your program should first read the header from the input file and write the header to the output
    file. Your program should then read the rest of the data from the WAV file, one 16-bit (2-byte) sample at a time. Your program
    should multiply each sample by the factor and write the new sample to the output file. You may assume that the WAV file will use
    16-bit signed values as samples. In practice, WAV files can have varying numbers of bits per sample, but we’ll assume 16-bit
    samples for this problem. Your program, if it uses malloc, must not leak any memory.
    */

    // TODO: Copy header from input file to output file
    // Copy header from input file to output file
    uint8_t header[HEADER_SIZE];
    fread(header, HEADER_SIZE, 1, input);
    fwrite(header, HEADER_SIZE, 1, output);

    // TODO: Read samples from input file and write updated data to output file
    // Create a buffer for a single sample
    int16_t buffer;

    // Read single sample from input into buffer while there are samples left to read
    while (fread(&buffer, sizeof(int16_t), 1, input))
    {
        // Update volume of sample
        buffer *= factor;

        // Write updated sample to new file
        fwrite(&buffer, sizeof(int16_t), 1, output);
    }

    // Close files
    fclose(input);
    fclose(output);
}
