/**
 * Test value :
 * Z =  2100.0x1 + 6400.0x2 + 5500.0x3 + 6000.0x4
 * <p>
 * S1      12x1 + 8x2 + 7x3 + 8x4 + S1 ≤ 85
 * S2      3x1 + 9x2 + 11x3 + 13x4 + S2 ≤ 75
 * S3      4x1 +6x2 + 8x3 + 19x4 + S3 ≤ 122
 * S4      15x1 + 11x2 + 13x3 + 17x4 + S4 ≤ 90
 */

package com.example.application.SimplexPackage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        int check;
        Scanner input = new Scanner(System.in);
        System.out.println("""
                Welcome To Simplex Method Solver (Maximization)
                1.Press 1 for executing existing problem sample
                2.Create your own problem(LP)""");
        check = input.nextInt();

        if (check == 1) {
/*            System.out.println("
                    -----------------Maximization LP-Model-----------------\n" +
                    "Z =  2100.0x1 + 6400.0x2 + 5500.0x3 + 6000.0x4\n" +
                    "S1      12x1 + 8x2 + 7x3 + 8x4 + S1 ≤ 85\n" +
                    "S2      3x1 + 9x2 + 11x3 + 13x4 + S2 ≤ 75\n" +
                    "S3      4x1 +6x2 + 8x3 + 19x4 + S3 ≤ 122\n" +
                    "S4      15x1 + 11x2 + 13x3 + 17x4 + S4 ≤ 90 \n" +
                    "------------------------------------------------------");*/

            LPModel model = new LPModel();  // Existing problem Solve Follow
//            System.out.println(model.printLPMODEL());

        } else if (check == 2) {

            System.out.print("Enter No of Decision Variables :");
            int maxDecisionVar = input.nextInt();
            System.out.print("Enter No of Constraints Variables :");
            int maxConstraints = input.nextInt();

            List<Double> objectiveFunction = new ArrayList<>();
            LinkedList<LinkedList<Double>> leftConstraint = new LinkedList<>();
            List<Double> rightConstraint = new ArrayList<>();

            System.out.println("Please Enter Objective Function : ");
            IntStream.iterate(1, i -> i + 1).limit(maxDecisionVar).forEach(value -> {
                System.out.print("For x" + (value) + ": ");
                objectiveFunction.add(input.nextDouble());
            });


            IntStream.iterate(1, i -> i + 1).limit(maxConstraints).forEach(value -> {
                leftConstraint.add(new LinkedList<>());
                System.out.println("\nConstraint " + value + " : ");
                IntStream.iterate(1, i -> i + 1).limit(maxDecisionVar).forEach(z -> {
                    System.out.print("For x" + (z) + ": ");
                    leftConstraint.get(value - 1).add(input.nextDouble());
                });
                System.out.println("Right-Side Constraint of x" + value + " : ");
                rightConstraint.add(input.nextDouble());
            });

            new LPModel(objectiveFunction, leftConstraint, rightConstraint);

        }


    }


}
