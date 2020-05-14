public class ProgramLCS_Simple {

	public static void doForPairOfStrings(ILCS_Simple solver, String x, String y) {
		
		long startTime, endTime;
		int lrez;

		System.out.println();
		System.out.println("First string is:\n" + x);
		System.out.println("Length of first string is " + x.length());
		System.out.println("Second string is:\n" + y);
		System.out.println("Length of second string is " + y.length());

		System.out.print("Computing the LCS string ... ");
		startTime = System.nanoTime();
		lrez = solver.LCS_length(x, y);
		endTime = System.nanoTime();
		System.out.println("Done in " + (endTime - startTime) / 1000000
				+ " milisec");
		System.out.println("The length of the LCS is: " + lrez);

	}

	public static void main(String[] args) {

		String short1 = "HORSEBACK";
		String short2 = "SNOWFLAKE";

		String medium1 = "The cats have fur.";
		String medium2 = "My cat is very furry.";

		String long1 = "The domestic cat Felis catus or Felis silvestris catus is a small, usually furry, domesticated, and carnivorous mammal. They are often called a housecat when kept as an indoor pet, or simply a cat when there is no need to distinguish them from other felids and felines. Cats are often valued by humans for companionship, and their ability to hunt vermin and household pests.";
		String long2 = "The domestic cat is small and furry. Cats are social species. They are cute.";

		// Sets the implementation to the simple recursive one
		// Here you will replace with your better dynamic programming solution
		ILCS_Simple solver = new LCS_Simple_Recursive();

		doForPairOfStrings(solver, short1, short2);
		
		doForPairOfStrings(solver, medium1, medium2);
		
		doForPairOfStrings(solver, long1, long2); // this is going to be extremely slow with the recursive  solver !
	}

}