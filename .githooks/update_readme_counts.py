"""Update the "Items" column of the folder table in README.md.

An item is any file tracked by git inside the folder (including subfolders),
excluding README.md files. Run from the repository root.
"""

import subprocess
from pathlib import Path

README = Path("README.md")
START = "<!-- folder-table:start -->"
END = "<!-- folder-table:end -->"
HEADER = "| Folder | Items | Description |"
DIVIDER = "| --- | --- | --- |"


def count_items(folder: str) -> int:
    files = subprocess.run(
        ["git", "ls-files", "-z", "--", folder],
        check=True,
        capture_output=True,
        text=True,
    ).stdout.split("\0")
    return sum(1 for f in files if f and Path(f).name.lower() != "readme.md")


def main() -> None:
    text = README.read_text(encoding="utf-8")
    before, rest = text.split(START, 1)
    table, after = rest.split(END, 1)

    rows = []
    for line in table.strip().splitlines():
        cells = [c.strip() for c in line.strip().strip("|").split("|")]
        if cells[0] in ("Folder", "---"):
            continue
        folder, description = cells[0], cells[-1]
        rows.append(f"| {folder} | {count_items(folder)} | {description} |")

    new_table = "\n".join([HEADER, DIVIDER, *rows])
    README.write_text(f"{before}{START}\n{new_table}\n{END}{after}", encoding="utf-8")


if __name__ == "__main__":
    main()
