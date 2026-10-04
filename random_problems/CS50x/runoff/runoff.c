#include <cs50.h>
#include <limits.h>
#include <stdio.h>
#include <string.h>

// Max voters and candidates
#define MAX_VOTERS 100
#define MAX_CANDIDATES 9

// preferences[i][j] is jth preference for voter i
int preferences[MAX_VOTERS][MAX_CANDIDATES];

// Candidates have name, vote count, eliminated status
typedef struct
{
    string name;
    int votes;
    bool eliminated;
} candidate;

// Array of candidates
candidate candidates[MAX_CANDIDATES];

// Numbers of voters and candidates
int voter_count;
int candidate_count;

// Function prototypes
bool vote(int voter, int rank, string name);
void tabulate(void);
bool print_winner(void);
int find_min(void);
bool is_tie(int min);
void eliminate(int min);

int main(int argc, string argv[])
{
    // Check for invalid usage
    if (argc < 2)
    {
        printf("Usage: runoff [candidate ...]\n");
        return 1;
    }

    // Populate array of candidates
    candidate_count = argc - 1;
    if (candidate_count > MAX_CANDIDATES)
    {
        printf("Maximum number of candidates is %i\n", MAX_CANDIDATES);
        return 2;
    }
    for (int i = 0; i < candidate_count; i++)
    {
        candidates[i].name = argv[i + 1];
        candidates[i].votes = 0;
        candidates[i].eliminated = false;
    }

    voter_count = get_int("Number of voters: ");
    if (voter_count > MAX_VOTERS)
    {
        printf("Maximum number of voters is %i\n", MAX_VOTERS);
        return 3;
    }

    // Keep querying for votes
    for (int i = 0; i < voter_count; i++)
    {

        // Query for each rank
        for (int j = 0; j < candidate_count; j++)
        {
            string name = get_string("Rank %i: ", j + 1);

            // Record vote, unless it's invalid
            if (!vote(i, j, name))
            {
                printf("Invalid vote.\n");
                return 4;
            }
        }

        printf("\n");
    }

    // Keep holding runoffs until winner exists
    while (true)
    {
        // Calculate votes given remaining candidates
        tabulate();

        // Check if election has been won
        bool won = print_winner();
        if (won)
        {
            break;
        }

        // Eliminate last-place candidates
        int min = find_min();
        bool tie = is_tie(min);

        // If tie, everyone wins
        if (tie)
        {
            for (int i = 0; i < candidate_count; i++)
            {
                if (!candidates[i].eliminated)
                {
                    printf("%s\n", candidates[i].name);
                }
            }
            break;
        }

        // Eliminate anyone with minimum number of votes
        eliminate(min);

        // Reset vote counts back to zero
        for (int i = 0; i < candidate_count; i++)
        {
            candidates[i].votes = 0;
        }
    }
    return 0;
}

/*
If any candidate has a majority (more than 50%) of the first preference votes, that candidate is declared the winner of the
election.

    If no candidate has more than 50% of the vote, then an “instant runoff” occurrs.
        The candidate who received the fewest number of votes is eliminated from the election,
        and anyone who originally chose that candidate as their first preference now has their second preference considered.

    The process repeats:
        if no candidate has a majority of the votes, the last place candidate is eliminated,
        and anyone who voted for them will instead vote for their next preference (who hasn’t themselves already been eliminated).
        Once a candidate has a majority, that candidate is declared the winner.
*/

// Record preference if vote is valid
bool vote(int voter, int rank, string name)
{
    /*
    The function takes three arguments: voter, rank, and name.
    If name is a match for the name of a valid candidate,
    then you should update the global preferences array to indicate that the voter voter has that candidate as their rank
    preference. Keep in mind 0 is the first preference, 1 is the second preference, etc.

    You may assume that no two candidates will have the same name.
    If the preference is successfully recorded, the function should return true.
    The function should return false otherwise.
    Consider, for instance, when name is not the name of one of the candidates.
    As you write your code, consider these hints:

    Recall that candidate_count stores the number of candidates in the election.
    Recall that you can use strcmp to compare two strings.
    Recall that preferences[i][j] stores the index of the candidate who is the jth ranked preference for the ith voter.
    */
    for (int i = 0; i < candidate_count; i++)
    {
        if (strcmp(name, candidates[i].name) == 0)
        {
            preferences[voter][rank] = i;
            return true;
        }
    }
    return false;
}

// Tabulate votes for non-eliminated candidates
void tabulate(void)
{
    /*
    Complete the tabulate function.

    The function should update the number of votes each candidate has at this stage in the runoff.
    Recall that at each stage in the runoff, every voter effectively votes for their top-preferred candidate who has not already
    been eliminated. Hints Recall that voter_count stores the number of voters in the election and that, for each voter in our
    election, we want to count one ballot. Recall that for a voter i, their top choice candidate is represented by
    preferences[i][0], their second choice candidate by preferences[i][1], etc. Recall that the candidate struct has a field called
    eliminated, which will be true if the candidate has been eliminated from the election. Recall that the candidate struct has a
    field called votes, which you’ll likely want to update for each voter’s preferred candidate. Once you’ve cast a vote for a
    voter’s first non-eliminated candidate, you’ll want to stop there, not continue down their ballot! Recall that you can break out
    of a loop early using break inside of a conditional.
    */
    // TODO

    for (int i = 0; i < voter_count; i++)
    {
        for (int j = 0; j < candidate_count; j++)
        {
            int voted_candidate = preferences[i][j];
            if (!candidates[voted_candidate].eliminated)
            {
                candidates[voted_candidate].votes++;
                break;
            }
        }
    }

    return;
}

// Print the winner of the election, if there is one
bool print_winner(void)
{
    /*
    Complete the print_winner function.

    If any candidate has more than half of the vote, their name should be printed and the function should return true.
    If nobody has won the election yet, the function should return false.
    Hints
    Recall that voter_count stores the number of voters in the election. Given that, how would you express the number of votes
    needed to win the election?
    */

    int half_of_all_votes = voter_count / 2;
    for (int i = 0; i < candidate_count; i++)
    {
        if (candidates[i].votes > half_of_all_votes)
        {
            printf("%s\n", candidates[i].name);
            return true;
        }
    }

    return false;
}

// Return the minimum number of votes any remaining candidate has
int find_min(void)
{
    /*
    Complete the find_min function.

The function should return the minimum vote total for any candidate who is still in the election.
Hints
You’ll likely want to loop through the candidates to find the one who is both still in the election and has the fewest number of
votes. What information should you keep track of as you loop through the candidates?
*/
    int min_vote = INT_MAX;
    // int min_voted_candidate = -1;
    for (int i = 0; i < candidate_count; i++)
    {
        if (!candidates[i].eliminated)
        {
            int votes = candidates[i].votes;
            if (votes < min_vote)
            {
                // min_voted_candidate = i;
                min_vote = votes;
            }
        }
    }

    return min_vote;
}

// Return true if the election is tied between all candidates, false otherwise
bool is_tie(int min)
{
    /*
    Complete the is_tie function.

The function takes an argument min, which will be the minimum number of votes that anyone in the election currently has.
The function should return true if every candidate remaining in the election has the same number of votes, and should return false
otherwise. Hints Recall that a tie happens if every candidate still in the election has the same number of votes. Note, too, that
the is_tie function takes an argument min, which is the smallest number of votes any candidate currently has. How might you use that
information to determine if the election is a tie (or, conversely, not a tie)?
*/
    bool is_all_same = true;
    for (int i = 0; i < candidate_count; i++)
    {
        if (!candidates[i].eliminated)
        {
            if (candidates[i].votes != min)
            {
                is_all_same = false;
            }
        }
    }

    return is_all_same;
}

// Eliminate the candidate (or candidates) in last place
void eliminate(int min)
{
    /*
    Complete the eliminate function.

The function takes an argument min, which will be the minimum number of votes that anyone in the election currently has.
The function should eliminate the candidate (or candidates) who have min number of votes.
*/
    for (int i = 0; i < candidate_count; i++)
    {
        if (candidates[i].votes == min)
        {
            candidates[i].eliminated = true;
        }
    }

    return;
}
