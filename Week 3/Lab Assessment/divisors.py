# Q2-W3-A: Small Factors

print("Enter a number: ")
n = int(input())

for i in range(2,min(10,n+1)):
    if n % i == 0:
        print(f"{i} is a divisor of {n}.")