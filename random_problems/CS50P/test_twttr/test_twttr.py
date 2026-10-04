from archive_CS50P.test_twttr.twttr import shorten

def test_shorten():
    assert shorten("zaEiOuy") == "zy"
    assert shorten("zAEIOUy") == "zy"
    assert shorten("zaeiouy") == "zy"
    assert shorten("z123y") == "z123y"
    assert shorten("z!?.,y") == "z!?.,y"

# def test_shorten_vowel_replacement():
#     assert shorten("zaEiOuy") == "zy"

# def test_shorten_capitalized_vowel_replacement():
#     assert shorten("zAEIOUy") == "zy"

# def test_shorten_lowercase_vowel_replacement():
#     assert shorten("zaeiouy") == "zy"

# def test_shorten_omitting_numbers():
#     assert shorten("z123y") == "zy"

# def test_shorten_omitting_punctuations():
#     assert shorten("z!?.,y") == "zy"

def main():
    test_shorten()
    # test_shorten_vowel_replacement()
    # test_shorten_capitalized_vowel_replacement()
    # test_shorten_lowercase_vowel_replacement()
    # test_shorten_omitting_numbers()
    # test_shorten_omitting_punctuations()

if __name__ == "__main__":
    main()
