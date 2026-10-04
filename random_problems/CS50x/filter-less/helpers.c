#include "helpers.h"
#include <math.h>

// Convert image to grayscale
void grayscale(int height, int width, RGBTRIPLE image[height][width])
{
    // Loop over all pixels
    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < width; j++)
        {
            // Take average of red, green, and blue
            int rgbtRed = image[i][j].rgbtRed;
            int rgbtBlue = image[i][j].rgbtBlue;
            int rgbtGreen = image[i][j].rgbtGreen;

            float avg = round((rgbtRed + rgbtBlue + rgbtGreen) / 3.0);

            // Update pixel values
            image[i][j].rgbtRed = avg;
            image[i][j].rgbtBlue = avg;
            image[i][j].rgbtGreen = avg;
        }
    }
    return;
}

// Convert image to sepia
void sepia(int height, int width, RGBTRIPLE image[height][width])
{
    // Loop over all pixels
    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < width; j++)
        {
            // Compute sepia values
            int sepiaRed = round(.393 * image[i][j].rgbtRed + .769 * image[i][j].rgbtGreen + .189 * image[i][j].rgbtBlue);
            int sepiaGreen = round(.349 * image[i][j].rgbtRed + .686 * image[i][j].rgbtGreen + .168 * image[i][j].rgbtBlue);
            int sepiaBlue = round(.272 * image[i][j].rgbtRed + .534 * image[i][j].rgbtGreen + .131 * image[i][j].rgbtBlue);

            if (sepiaRed > 255)
            {
                sepiaRed = 255;
            }
            if (sepiaGreen > 255)
            {
                sepiaGreen = 255;
            }
            if (sepiaBlue > 255)
            {
                sepiaBlue = 255;
            }

            // Update pixel with sepia values
            image[i][j].rgbtRed = sepiaRed;
            image[i][j].rgbtGreen = sepiaGreen;
            image[i][j].rgbtBlue = sepiaBlue;
        }
    }
    return;
}

// Reflect image horizontally
void reflect(int height, int width, RGBTRIPLE image[height][width])
{
    // Loop over all pixels
    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < width / 2; j++)
        {
            // Swap pixels
            RGBTRIPLE temp = image[i][j];
            image[i][j] = image[i][width - 1 - j];
            image[i][width - 1 - j] = temp;
        }
    }
    return;
}

// Blur image
void blur(int height, int width, RGBTRIPLE image[height][width])
{
    // Create a copy of image
    RGBTRIPLE copy[height][width];
    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < width; j++)
        {
            copy[i][j] = image[i][j];
        }
    }

    // blur
    for (int row = 0; row < height; row++)
    {
        for (int col = 0; col < width; col++)
        {
            int iStart = row - 1;
            int jStart = col - 1;
            int iEnd = row + 1;
            int jEnd = col + 1;

            if (row == 0)
            {
                iStart = row;
            }
            if (row == height - 1)
            {
                iEnd = row;
            }

            if (col == 0)
            {
                jStart = col;
            }

            if (col == width - 1)
            {
                jEnd = col;
            }

            float sumRed = 0;
            float sumGreen = 0;
            float sumBlue = 0;
            for (int i = iStart; i <= iEnd; i++)
            {
                for (int j = jStart; j <= jEnd; j++)
                {
                    sumRed += copy[i][j].rgbtRed;
                    sumGreen += copy[i][j].rgbtGreen;
                    sumBlue += copy[i][j].rgbtBlue;
                }
            }

            int cellCount = (((iEnd - iStart) + 1) * ((jEnd - jStart) + 1));
            int avgRed = round(sumRed / cellCount);
            int avgGreen = round(sumGreen / cellCount);
            int avgBlue = round(sumBlue / cellCount);

            image[row][col].rgbtRed = avgRed;
            image[row][col].rgbtGreen = avgGreen;
            image[row][col].rgbtBlue = avgBlue;
        }
    }

    return;
}
