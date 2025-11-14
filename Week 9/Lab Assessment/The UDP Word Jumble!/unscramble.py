# Q2-W9-A: The UDP Word Jumble

def unscramble(filename: str) -> str:
    d = {}
    with open(filename, "r") as f:
        for line in f.readlines():
            pair = line.split(": ")
            print(pair)
            d[int(pair[0])] = pair[1].strip("\n")
    packets = sorted(d.items(), key=lambda pair: pair[0])
    return "".join([p[1] for p in packets])

if __name__ == "__main__":
    print(unscramble("example.txt"))
