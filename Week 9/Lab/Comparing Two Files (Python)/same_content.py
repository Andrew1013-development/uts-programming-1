# Q5-W9: Comparing Two Files (Python)

import sys


def same_content(file1, file2) -> bool:
    f1 = open(file1, "r")
    f2 = open(file2, "r")
    buffer1 = f1.read()
    buffer2 = f2.read()
    f1.close()
    f2.close()
    return buffer1 == buffer2


if __name__ == "__main__":
    if same_content(sys.argv[1], sys.argv[2]):
        print("The two files have the same content.")
    else:
        print("The two files do not have the same content.")
