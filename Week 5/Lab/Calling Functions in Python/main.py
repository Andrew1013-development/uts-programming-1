# Q7-W5: Calling Functions in Python

import sys
from temperature import temperature

temps = temperature(sys.argv[1])

# Begin here!
days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]
for day in days:
    print(f"{day} was {temps.getTemp(temps.dayToIndex(day))}")
print(f"The maximum was {temps.high()}")
print(f"The minimum was {temps.low()}")
print(f"The average was {temps.average()}")