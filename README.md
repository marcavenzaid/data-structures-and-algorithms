# Data Structures and Algorithms

My implementations of common data structures and algorithms, along with my solutions to competitive programming problems, practice problems from various websites, and other problems I've come across.

## Folder Structure

<!-- folder-table:start -->
| Folder | Items | Description |
| --- | --- | --- |
| algorithms | 42 | Implementations of common algorithms. |
| competitive_programming | 40 | Solutions to problems from programming contests. |
| data_structures | 3 | Implementations of common data structures. |
| miscellaneous | 204 | Miscellaneous code, such as solutions to practice problems, exercises from online courses, and other code I've written. |
| references | 3 | Reusable helper code and reference notes. |
| templates | 2 | Starter templates for competitive programming. |
<!-- folder-table:end -->

## Item Counts

The **Items** column in the folder structure table is updated automatically by a git pre-commit hook in [`.githooks/`](.githooks/). 

On every commit, the hook recounts the files git tracks in each folder listed in the table (including subfolders, excluding `README.md` files), rewrites the column, and includes the updated `README.md` in the commit.

To add a folder to the table, add a row in the folder structure table with the same folder name as it appears in the repo, with any placeholder count. The next commit fills it in.

## Setup

Git doesn't enable hooks from a cloned repository automatically. Run this once after cloning:

```sh
git config core.hooksPath .githooks
```

To update the counts without committing, run:

```sh
python .githooks/update_readme_counts.py
```

Commits made without the hook, such as edits on GitHub's website, don't update the counts. The next commit made with the hook corrects them.
