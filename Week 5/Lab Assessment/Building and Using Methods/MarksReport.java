// Q2-W5-A: Building and Using Methods
public class MarksReport {
	
	public static void main(String[] args) {
		Student best, worst;
		StudentDatabase database = new StudentDatabase(args[0]);
		
		best = database.best_student();
		worst = database.worst_student();
		System.out.printf("There are %d students.\n", database.student_count());
		System.out.printf("The average mark is %s.\n", Float.toString(database.average_mark()));
		System.out.printf("The best student is %s with a mark of %d.\n", best.name(), best.mark());
		System.out.printf("The worst student is %s with a mark of %d.\n", worst.name(), worst.mark());
	}
	
}
