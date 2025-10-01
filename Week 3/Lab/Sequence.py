# Q5-W3: Sequencing Overlapping Conditions

n = int(input())

# Put your if-elif in here

if 5 <= n <= 10:
    if n != 7:
        n += 2
    else:
        n += 1
elif n < 5:
    if n % 2 == 0:
        n //= 2
    else:
        n *= 3
else:
    n *= 3

# Anything after the print statement is not useful

print(n)