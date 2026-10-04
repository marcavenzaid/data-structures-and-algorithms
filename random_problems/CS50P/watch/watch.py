import re
import sys


# <iframe src="http://www.youtube.com/embed/xvFZjo5PgG0"></iframe>
# <iframe width="560" height="315" src="https://www.youtube.com/embed/xvFZjo5PgG0" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

def parse(s):
    r"""
    '<iframe' Matches the literal text '<iframe'.
    '[^>]+'	one or more characters that are not '>'.
        - '[...]' = character set.
        - '^' inside '[]' = NOT.
        - '>' = the character we're excluding.
        - '+' = one or more.
    'src="'	Matches the literal 'src="'.
    'https?://'
        - '?' makes the preceding 's' optional.
        - Matches both 'http://' and 'https://'.
    '(?:www\.)?'		optionally match 'www.'.
        - '(?:...)' = non-capturing group.
        - '\.' = literal '.'.
        - '?' = optional.
    'youtube\.com/embed/'
        - Matches the literal 'youtube.com/embed/'.
        - '\.' means a literal period.
    '([^"]+)' Capture the video ID until the next '"'. Example: captures 'xvFZjo5PgG0'.
        - '(...)' = capturing group.
        - '[^"]' = any character except '"'.
        - '+' = one or more.
    '"' Matches the closing double quote.
    """

    pattern = r'<iframe[^>]+src="https?://(?:www\.)?youtube\.com/embed/([^"]+)"'
    match = re.search(pattern, s)

    if match:
        return f"https://youtu.be/{match.group(1)}"

    return None

def main():
    print(parse(input("HTML: ")))


if __name__ == "__main__":
    main()
