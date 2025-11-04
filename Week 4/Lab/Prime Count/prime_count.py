# Q7-W4: Prime Count

print("Input a number: ", end="")
n = int(input())

x = 0  # This variable may or may not be needed in your solution
prime = [True] * (n+1)

# Compute your answer
i = 2
while i * i <= n:
    if prime[i]:
        for j in range(i*i, n+1, i):
            prime[j] = False
    i += 1

for i in range(2,n+1):
    x += prime[i]

# Output (don't forget to change x if necessary)
print("There are " + str(x) + " primes less than or equal to " + str(n) + ".")