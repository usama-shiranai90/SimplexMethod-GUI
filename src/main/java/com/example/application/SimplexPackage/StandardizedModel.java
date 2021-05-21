package com.example.application.SimplexPackage;

import java.util.ArrayList;
import java.util.LinkedList;

/*
    class use to Formulate Problem,
    here i standardized the LP-Problem into Simplex Procedure.
 */
public class StandardizedModel {

    private int totalNoConstraints;  // number of constraints  -> e.g by calling size of ls_con or rs_con method.
    private int totalFunctionVariables;  // number of original variables   ->  Objective function ka variables ko show kary ga.
    private ArrayList<ArrayList<Double>> tableaux ;

    public StandardizedModel() {

    }

    public StandardizedModel(LinkedList<Double> objectiveFunction, LinkedList<LinkedList<Double>> lsConstraint,
                             LinkedList<Assets.OperatorConstraints> operatorConstraints, LinkedList<Double> rsConstraint) {

        totalNoConstraints = rsConstraint.size();
        totalFunctionVariables = objectiveFunction.size();
        tableaux = new ArrayList<>();          // [4][4]     // [4] [9]


        // initialize constraint
        for (int i = 0; i < totalNoConstraints; i++) {  //  4
            tableaux.add(new ArrayList<>());
            int j;
            for ( j= 0; j < totalFunctionVariables; j++) { // 4
                tableaux.get(i).add(lsConstraint.get(i).get(j));
            }

            // write  addition zero on each rowo.

        }

        for (ArrayList<Double> fuck:tableaux) {
            System.out.println(fuck);
        }

    }

    public int getTotalNoConstraints() {
        return totalNoConstraints;
    }

    public int getTotalFunctionVariables() {
        return totalFunctionVariables;
    }

    public ArrayList<ArrayList<Double>> getTableaux() {
        return tableaux;
    }
}
