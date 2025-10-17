# Q1-W5-A: Building and Using Functions

def number_of_students(data: list[tuple[str, int]]) -> int:
    return len(data)


def average_mark(data: list[tuple[str, int]]) -> float:
    return sum([v for k,v in data]) / len(data)


def name_of_best_student(data: list[tuple[str, int]]) -> str:
    return max(data, key=lambda item: item[1])[0]


def name_of_worst_student(data: list[tuple[str, int]]) -> str:
    return min(data, key=lambda item: item[1])[0]


# This __main__ block just demonstrates the code, this is not the
# part that's tested.
if __name__ == "__main__":
    student_data = [('Luke', 50), ('Cat', 99), ('Nick', 98), ('Mazza', 98)]

    print("There are", number_of_students(student_data), "students.")
    print("The average mark is:", average_mark(student_data))
    print("The best student is:", name_of_best_student(student_data))
    print("The worst student is:", name_of_worst_student(student_data))