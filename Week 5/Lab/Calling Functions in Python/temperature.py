# Q7-W5: Calling Functions in Python

class temperature:

    temps = []

    def __init__(self, filename: str) -> None:
        self.readTemps(filename)

    def dayToIndex(self, day: str) -> int:
        match day:
            case "Monday":
                return 0
            case "Tuesday":
                return 1
            case "Wednesday":
                return 2
            case "Thursday":
                return 3
            case "Friday":
                return 4
            case "Saturday":
                return 5
            case "Sunday":
                return 6

    def readTemps(self, filename: str) -> None:
        file = open(filename)
        for line in file:
            self.temps.append(float(line))

    def getTemp(self, index: int) -> int:
        return self.temps[index]

    def average(self) -> float:
        return sum(self.temps)/len(self.temps)

    def high(self) -> int:
        return max(self.temps)

    def low(self) -> int:
        return min(self.temps)
