# Advent of Code - 2022

These are my answers to the programming puzzles available at https://adventofcode.com/

I'm using Clojure because this is the language I'm currently most working.

These challenges are grouped by day and each day has its own structure:

```
├── resources
│   ├── input-dayXX.txt
│   └── input-dayYY.txt
├── src
│   └── aco2022
│       ├── dayXX
│       │   └── adapters
│       │       └── challenge_text.clj
│       │   └── logic
│       │       └── challenge.clj
│       └── dayYY
└── test
    └── aco2022
        ├── dayXX
        │   └── adapters
        │       └── challenge_text_test.clj
        │   └── logic
        │       └── challenge_test.clj
        │   └── challenge_test.clj
        └── dayYY
```

Each challenge is composed by:
- its text input, given by the platform;
- the adapter code that transforms the text input into a more adequate data representation (mostly Clojure's maps and sequences)
- the logic code that resolves the challenges

Each part has its own tests, considering the respective responsibility (adapters tests the conversion of data from a textual representation to Clojure's data representation; logic works on these data representations to achieve the result). The challenge test namespace directly in the *day* folder wraps all tests: it gets the input text, pass it to the adapters code, process the logic and the compare the result with the expected value (the answer to the challenge).

To run all the tests:
```bash
lein test
```