package com.example.application.SimplexPackage;

import java.util.ArrayList;

public class SimplexSolver {

    private final String[] rowField;
    private final String[] columnField;
    private final int[] basis; //  corresponding to row hue jo bi index ho .
    private final int totalNoConstraints;  // number of constraints  -> e.g by calling size of ls_con or rs_con method.
    private final int totalFunctionVariables;  // number of original variables   ->  Objective function ka variables ko show kary ga.
    ArrayList<ArrayList<Double>> tableaux;

    public SimplexSolver(ArrayList<ArrayList<Double>> tableaux, int total_variables, int totalCon, String[] row, String[] column) {

        totalFunctionVariables = total_variables;
        totalNoConstraints = totalCon;

        rowField = row;
        columnField = column;

        this.tableaux = new ArrayList<>(tableaux);
        basis = new int[totalNoConstraints];
        for (int i = 0; i < totalNoConstraints; i++)
            basis[i] = totalFunctionVariables + i;


    }

    protected String solve() { //initialize
        String result = null;
        while (true) {
            result = show();
            int q = 0;
            // find entering column q
            q = columnEntery();

            if (q == -1)
                break; // optimal

            // find leaving row leavingP
            int leavingP = minRatioRule(q);
            if (leavingP == -1)
                throw new ArithmeticException("Linear program is unbounded");

            // pivot
            pivot(leavingP, q);

            // update basis
            basis[leavingP] = q;

            return result;
        }
        return result;
    }

    private int columnEntery() {  // last row ma highest value ly kr aye.
        int temp = 0;
        for (int j = 1; j < totalNoConstraints + totalFunctionVariables; j++)
            if (tableaux.get(totalNoConstraints).get(j) > tableaux.get(totalNoConstraints).get(temp)) {
                temp = j;
                System.out.println("highest value = " + tableaux.get(totalNoConstraints).get(j));
            }


        if (tableaux.get(totalNoConstraints).get(temp) <= 0)
            return -1; // optimal
        else
            return temp;
    }

    private int minRatioRule(int q) {
        int p = -1;
        for (int i = 0; i < totalNoConstraints; i++) {
            if (p == -1)
                p = i;  // p = 0;
            else if (tableaux.get(i).get(q) <= 0) {
            } else if ((tableaux.get(i).get(totalNoConstraints + totalFunctionVariables) / tableaux.get(i).get(q)) // t[1][8]/ t[1][1] = 75/9
                    <
                    (tableaux.get(p).get(totalNoConstraints + totalFunctionVariables) / tableaux.get(p).get(q))) // t[0][8] / t[0][1]
                p = i;
        }
        return p;
    }

    private void pivot(int p, int q) {

        // everything but row p and column q
        for (int i = 0; i <= totalNoConstraints; i++)
            for (int j = 0; j <= totalNoConstraints
                    + totalFunctionVariables; j++)
                if (i != p && j != q)
                    tableaux.get(i).set(j, tableaux.get(i).get(j) - (tableaux.get(p).get(j) * tableaux.get(i).get(q) / tableaux.get(p).get(q)));


        for (int i = 0; i <= totalNoConstraints; i++)        // zero out column q
            if (i != p)
                tableaux.get(i).set(q, 0.0);


        for (int j = 0; j <= totalNoConstraints + totalFunctionVariables; j++)        // scale row p
            if (j != q)
                tableaux.get(p).set(j, (tableaux.get(p).get(j) / tableaux.get(p).get(q)));
        tableaux.get(p).set(q, 1.0);
    }

    public double valueOfZ() {
        return -tableaux.get(totalNoConstraints).get(totalNoConstraints + totalFunctionVariables);
    }


    public String show() {

        StringBuilder builder = new StringBuilder();

//        builder.append("value = ").append(valueOfZ()).append("\n");

        for (int i = 0; i < totalNoConstraints; i++) {
            if (basis[i] < totalFunctionVariables) //   < 4
            {
                rowField[i] = "x".concat(String.valueOf(basis[i] + 1));
                builder.append("x_").append(basis[i]).append(" = ").append(tableaux.get(i).get(totalNoConstraints + totalFunctionVariables)).append("\n");
            }
        }


        builder.append("Base");
        for (String s : columnField) {
            builder.append(String.format("%7.2s ", s));
        }
        builder.append("\n");
        for (int i = 0; i <= totalNoConstraints; i++) {
            builder.append(rowField[i] + "   ");
            for (int j = 0; j <= totalNoConstraints + totalFunctionVariables; j++) {
                builder.append(String.format("%7.2f ", tableaux.get(i).get(j)));
            }
            builder.append("\n");
        }
        return builder.toString();

    }

    public int getTotalNoConstraints() {
//        float result = Float.parseFloat(String.format("%.2f", 0.0232));
        return totalNoConstraints;
    }

    public int getTotalFunctionVariables() {
        return totalFunctionVariables;
    }


}
