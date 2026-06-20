package week1.DSA;

// Recurssion : A programming technique where a method calls itself to solve it's smaller version.


public class FinancialForecast {
    public static void main(String[] args) {
        double investment = 100000;
        double growth = 0.10;
        int years = 5;

        System.out.println(futureValue(investment, growth, years));

        System.out.println(futureValueOptimized(investment, growth, years));
    }

    public static double futureValue(double inv, double growth, int years) {
        if(years == 0) return inv;
        return futureValue(inv, growth, years - 1) * (growth + 1);
    }

    /*
    to optimise this solution we can use Math.pow(x, y) formula : 
    */
    public static double futureValueOptimized(double inv, double growth, int years) {

        return inv * Math.pow(1 + growth, years);
    }
}


/*
time complexity for the given problem : 
number of calls = 5 (can be represented as n).
space occupied = 5 (can be represented as n).
therefore, time complexity for this solution is O(n) and space complexity is O(n).
*/

