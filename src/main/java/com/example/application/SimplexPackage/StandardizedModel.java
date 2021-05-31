package com.example.application.SimplexPackage;

import java.util.ArrayList;
import java.util.LinkedList;

/*
    class use to Formulate Problem,
    here i standardized the LP-Problem into Simplex Procedure.
 */
public class StandardizedModel {

    private ArrayList<ArrayList<Double>> tableaux;

    private final String tableOutput;
    private final double optimalSolution;

    public StandardizedModel(LinkedList<Double> objectiveFunction, LinkedList<LinkedList<Double>> lsConstraint, LinkedList<Double> rsConstraint) {

        int totalNoConstraints = rsConstraint.size();
        int totalFunctionVariables = objectiveFunction.size();
        tableaux = new ArrayList<>();                 /**
         * Original = [4][4]  -> but we need to add slack-variables:
         *  if 4 constraints then 4 slack
         *  1 for right side constraint.   [4] [9]
         */

        String[] rowField = new String[totalNoConstraints + 1];// addition 1 for Cj-Zj
        String[] columnField = new String[totalFunctionVariables + totalNoConstraints + 1];

        int slackIncrement = totalFunctionVariables;

        // initialize constraint
        for (int row = 0; row < totalNoConstraints + 1; row++) {  // 4 constraint and 1 for O-Fun
            tableaux.add(new ArrayList<>());

            boolean slackFlag = false;

            rowField[row] = "s" + (row + 1);

            for (int column = 0; column < (totalFunctionVariables + totalNoConstraints + 1); column++) {  // 5 < 9
//                System.out.println("row = " + row + "   tableaux.size = " + tableaux.size());

                if (row == totalNoConstraints) {

                    rowField[row] = "Cj-Zj";
                    if (column < objectiveFunction.size())
                        tableaux.get(row).add(objectiveFunction.get(column));
                    else
                        tableaux.get(row).add(0.0);
                } else if (column < totalFunctionVariables) {      // for adding left constraints.

                    tableaux.get(row).add(lsConstraint.get(row).get(column));
                    columnField[column] = "x" + (column + 1);

                } else if (column < (totalFunctionVariables + totalNoConstraints)) {    // adding slacks section      4 < 7

                    if (slackIncrement == column && !slackFlag) {   // 4 == 4
                        tableaux.get(row).add(1.0);
                        slackFlag = true;
                        slackIncrement++;   //  SI = 5

                    } else
                        tableaux.get(row).add(0.0);
                    columnField[column] = "s" + (row + 1);


                } else {         // right constraint
                    tableaux.get(row).add(rsConstraint.get(row));
                    columnField[column] = "profit";
                }

            }

        }

        System.out.println("slackIncrement = " + slackIncrement);

        SimplexSolver simplexSolver = new SimplexSolver(tableaux, totalFunctionVariables, totalNoConstraints, rowField, columnField);
        simplexSolver.solve();
        System.out.println("simplexSolver = " + simplexSolver.getEndResult().toString());
        System.out.println("simplexSolver.getOptimalSolution() = " + simplexSolver.getOptimalSolution());

        tableOutput = simplexSolver.getEndResult().toString();
        optimalSolution = simplexSolver.getOptimalSolution();
    }



    public String getOutputTableux() {
        return tableOutput;
    }

    public double getOptimalSolutionValue() {
        return optimalSolution;
    }

    public ArrayList<ArrayList<Double>> getTableaux() {
        return tableaux;
    }


}
