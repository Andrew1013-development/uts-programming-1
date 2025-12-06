# Q1-W10-A: Filtering Input

def only_positives(input_filename: str, output_filename: str) -> None:
    f_in = open(input_filename, "r")
    f_out = open(output_filename, "w")
    
    for line in f_in.readlines():
        if int(line.strip()) > 0:
            f_out.write(line)
    
    f_in.close()
    f_out.close()

if __name__ == "__main__":
    only_positives("in.txt", "out.txt")
