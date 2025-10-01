# Q5-W2: An Even Smaller Input Challenge

n = int(input("Enter a number:"))
match (n % 2):
    case 0:
        print("That number is even.")
    case 1:
        print("That number is odd.")