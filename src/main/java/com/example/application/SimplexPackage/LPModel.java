package com.example.application.SimplexPackage;

import java.util.*;

public class LPModel {

    private int sampleTest;
    ArrayList<String> rowField = new ArrayList<>();
    ArrayList<String> columnsField = new ArrayList<>();

    // starts from here.
    private LinkedList<Double> objectiveFunction;
    private LinkedList<LinkedList<Double>> lsConstraint;
    private LinkedList<Double> rsConstraint;
    private LinkedList<Assets.OperatorConstraints> operatorConstraints;

    //     Existing problem solve as test.
    public LPModel() {
        sampleTest = 0;
        // define OBJECTIVE FUNCTION  , Matrix Constraints.
        objectiveFunction = new LinkedList<>();
        lsConstraint = new LinkedList<>();
        operatorConstraints = new LinkedList<>();
        rsConstraint = new LinkedList<>();

        fillObjectiveFunction(objectiveFunction);
        fillLeftSideConstraint(lsConstraint, null);
        fillOperator(operatorConstraints);
        fillRightSideConstraint(rsConstraint);

    }

    public LPModel(List<Double> objectfunction, LinkedList<LinkedList<Double>> lconstraint, List<Double> rconstraint) {
        sampleTest = 1;
        // define OBJECTIVE FUNCTION  , Matrix Constraints.
        objectiveFunction = new LinkedList<>(objectfunction);  // copies objective function ref here
        lsConstraint = new LinkedList<>(lconstraint);   // we need to call fillLeftSideConstraint to properly implement it.
        operatorConstraints = new LinkedList<>();  // simply (fillOperator) call function.
        rsConstraint = new LinkedList<>(rconstraint); // copies right side constraint here.


//        fillLeftSideConstraint(lsConstraint , lconstraint);
        fillOperator(operatorConstraints);

    }


    public void fillObjectiveFunction(LinkedList<Double> of) {

        of.addAll(Arrays.asList(2100.0, 6400.0, 5500.0, 6000.0));


    }

    public void fillLeftSideConstraint(LinkedList<LinkedList<Double>> lsConstraint, Double[][] constraintLeftSide) {

        if (getSampleTest() == 0) {
            constraintLeftSide = new Double[][]{
                    {12.0, 8.0, 7.0, 8.0}              // [4][4]
                    , {3.0, 9.0, 11.0, 13.0}
                    , {4.3, 6.0, 8.0, 19.0}
                    , {15.0, 11.0, 13.0, 17.0}
            };
        }

        int i = 0;
        while (i < constraintLeftSide.length) {
            lsConstraint.add(i, new LinkedList<>());
//            lsConstraint.get(0).addAll(Arrays.asList(12.0, 8.0, 7.0, 8.0));
            lsConstraint.get(i).addAll(Arrays.asList(constraintLeftSide[i]));
            i++;
        }
    }

    public void fillRightSideConstraint(LinkedList<Double> rsConstraint) {
        Collections.addAll(rsConstraint,
                85.0, 75.0, 122.0, 90.0);
    }

    public void fillOperator(LinkedList<Assets.OperatorConstraints> operatorConstraints) {
        Collections.addAll(operatorConstraints,
                Assets.OperatorConstraints.LESS_THAN,
                Assets.OperatorConstraints.EQUALS_TO,
                Assets.OperatorConstraints.GREATER_THAN);
    }

    public String printLPMODEL() {
        StringBuilder builder = new StringBuilder();

        builder.append("For Maximization \nZ = ");
        for (int i = 0; i < objectiveFunction.size(); i++) {
            builder.append(objectiveFunction.get(i)).append("x").append(i + 1);
            if (i != objectiveFunction.size() - 1) {
                builder.append(" + ");
            }
        }

        builder.append("\nSubject to = \n");

        for (int rowIndex = 0; rowIndex < lsConstraint.size(); rowIndex++) {  // doesnt work on 16 iteration.

            for (int itemInRow = 0; itemInRow < lsConstraint.get(rowIndex).size(); itemInRow++) {
                builder.append(lsConstraint.get(rowIndex).get(itemInRow)).append("x").append(itemInRow + 1).append(" ");

                if (itemInRow < lsConstraint.get(rowIndex).size() - 1) {  // i != objectiveFunction.size() - 1
                    builder.append(" + ");
                }
                if (itemInRow == lsConstraint.get(rowIndex).size() - 1) { // 2 == 2
                    builder.append(operatorConstraints.get(2)).append("  ")
                            .append(rsConstraint.get(rowIndex)).append("\n");
                }
            }

        }
        return builder.toString();
    }

    // getter and setters
    public ArrayList<String> getRowField() {
        return rowField;
    }

    public void setRowField(ArrayList<String> rowField) {
        this.rowField = rowField;
    }

    public ArrayList<String> getColumnsField() {
        return columnsField;
    }

    public void setColumnsField(ArrayList<String> columnsField) {
        this.columnsField = columnsField;
    }

    public LinkedList<Double> getObjectiveFunction() {
        return objectiveFunction;
    }

    public void setObjectiveFunction(LinkedList<Double> objectiveFunction) {
        this.objectiveFunction = objectiveFunction;
    }

    public int getSampleTest() {
        return sampleTest;
    }

    public LinkedList<LinkedList<Double>> getLsConstraint() {
        return lsConstraint;
    }

    public void setLsConstraint(LinkedList<LinkedList<Double>> lsConstraint) {
        this.lsConstraint = lsConstraint;
    }

    public LinkedList<Double> getRsConstraint() {
        return rsConstraint;
    }

    public void setRsConstraint(LinkedList<Double> rsConstraint) {
        this.rsConstraint = rsConstraint;
    }

    public LinkedList<Assets.OperatorConstraints> getOperatorConstraints() {
        return operatorConstraints;
    }

    public void setOperatorConstraints(LinkedList<Assets.OperatorConstraints> operatorConstraints) {
        this.operatorConstraints = operatorConstraints;
    }


}
