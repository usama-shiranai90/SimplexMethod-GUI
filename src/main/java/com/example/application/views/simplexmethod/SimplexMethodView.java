package com.example.application.views.simplexmethod;

import com.example.application.SimplexPackage.LPModel;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * A Designer generated component for the person-form-view template.
 * <p>
 * Designer will add and remove fields with @Id mappings but does not overwrite
 * or otherwise change this file.
 */
@Route(value = "simplex")
@RouteAlias(value = "")
@PageTitle("Simplex Method")
@Tag("simplex-method-view")
@JsModule("./views/simplexmethod/simplex-method-view.ts")

@CssImport("./themes/oneeyeowl0-0/componentStyling.css")
public class SimplexMethodView extends LitTemplate {

    private int totalDecisionVariables;
    private int totalConstraints;

//    private List<TextField> Constraint_TextField = new ArrayList<>();

    @Id("dv")
    private TextField dv;
    @Id("nocons")
    private TextField nocons;

    @Id("action")
    private Button action;

    @Id("constraint_h3")
    private H3 constraint_h3;
    @Id("obj_function_h3")
    private H3 obj_function_h3;

    @Id("topHL")
    private HorizontalLayout topHL;
    @Id("ofForm")
    private HorizontalLayout ofForm;

    private Button calculateSimplex;
    @Id("constraintVertticalForm")
    private VerticalLayout constraintVertticalForm;


    @Id("sampleButton")
    private Button sampleButton;
    @Id("lpmodeloutput")
    private TextArea lpmodeloutput;

    private Grid<String> vaadinGrid;

    public SimplexMethodView() {
//        topHL.getStyle().set("border", "1px solid #9E9E9E");

        obj_function_h3.setVisible(false);
        constraint_h3.setVisible(false);

        if (nocons != null && dv != null) {
            setToIntegerOnlyField(nocons);
            setToIntegerOnlyField(dv);
            action.addClickListener(buttonClickEvent -> generateConstraintsBox());
        }

        sampleButton.addClickListener(this::performSampleAction);

    }

    private void performSampleAction(ClickEvent<Button> buttonClickEvent) {
        LPModel model = new LPModel();  // Existing problem Solve Follow
        lpmodeloutput.setValue(model.printLPMODEL());

        ArrayList<String>[] test = new ArrayList[model.getObjectiveFunction().size()];

//        Trying to create a grid for Constraints but failed.
            /*

                    for (int row = 0; row < model.getObjectiveFunction().size(); row++) {


                        for (int col = 0; col < model.getLsConstraint().size(); col++) {
                            System.out.println("row = " + row + " col = " + col);
                            System.out.println("model.getLsConstraint().get(row).get(col) = " + model.getLsConstraint().get(col).get(row));
                            if (test[row] == null) {
                                test[row] = new ArrayList<>();
                            }
                            test[row].add(String.valueOf(model.getLsConstraint().get(col).get(row)));
                        }
                    }

                    vaadinGrid = new Grid<>();
                    vaadinGrid.removeAllColumns();

                    vaadinGrid.setItems(String.valueOf(test[0].stream().iterator()), test[1].toString(), test[2].toString(), test[3].toString());

                    for (int i = 0; i < test.length; i++) {
                        vaadinGrid.addColumn(String::valueOf).setHeader("x" + (i + 1));
                    }


                    vaadinGrid.setWidth("55%");
                    vaadinGrid.setSelectionMode(Grid.SelectionMode.NONE);
                    vaadinGrid.setHeight("270px");
                    vaadinGrid.setDetailsVisibleOnClick(false);
                    vaadinGrid.setDetailsVisibleOnClick(false);
                    vaadinGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

                    vaadinGrid.getElement().setAttribute("slot", "vaadinGrid");
                    vaadinGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
                    getElement().appendChild(vaadinGrid.getElement());
            */

    }


    private void generateConstraintsBox() {
        List<Double> objectiveFunction = new ArrayList<>();
        LinkedList<LinkedList<Double>> leftConstraint = new LinkedList<>();
        List<Double> rightConstraint = new ArrayList<>();

        List<TextField> objectiveFunction_TextField = new ArrayList<>();
        List<TextField> leftCon_TextField = new ArrayList<>();
        List<TextField> rightCon_TextField = new ArrayList<>();


        // clear Objective Function and Constraints Forms.
        ofForm.removeAll();
        ofForm.addClassName("horizontalConstraint");
        ofForm.getStyle().set("margin-left", "9%");

        constraintVertticalForm.removeAll();

        // set Visible true
        obj_function_h3.setVisible(true);
        constraint_h3.setVisible(true);

        // no of DV and Cons.
        setTotalDecisionVariables(Integer.parseInt(dv.getValue()));
        setTotalConstraints(Integer.parseInt(nocons.getValue()));

        for (int index = 0; index < getTotalDecisionVariables(); index++) {
            TextField titleField = new TextField();
            titleField.setLabel("Decision variable x" + (index + 1));
            titleField.setPlaceholder("x" + (index + 1));
            titleField.setWidth("14%");
            setToIntegerOnlyField(titleField);

            objectiveFunction_TextField.add(titleField);
            ofForm.add(objectiveFunction_TextField.get(index));

//            ofForm.setResponsiveSteps(new FormLayout.ResponsiveStep("15%", index + 1));
        }


        for (int cons = 1; cons <= totalConstraints; cons++) {

            H4 constraintHeader = new H4("For Constraint :" + cons);
            constraintHeader.addClassName("heading");

            HorizontalLayout perConstraintData = new HorizontalLayout();
            perConstraintData.addClassName("horizontalConstraint");
            perConstraintData.add(constraintHeader);

            for (int variable = 1; variable <= totalDecisionVariables; variable++) {

                TextField constraintTextField = new TextField();
                constraintTextField.setLabel("constraint x" + (variable));
                constraintTextField.setPlaceholder("x" + (variable));
                constraintTextField.setWidth("10%");
                setToIntegerOnlyField(constraintTextField);

                leftCon_TextField.add(constraintTextField);

                perConstraintData.add(constraintTextField);
                if (variable == totalDecisionVariables) {
                    TextField ProfitTextField = new TextField();
                    ProfitTextField.setLabel("Profit");
                    ProfitTextField.setWidth("10%");
                    perConstraintData.add(ProfitTextField);
                    rightCon_TextField.add(ProfitTextField);
                }

            }

            constraintVertticalForm.add(perConstraintData);
        }

        calculateSimplex = new Button("Perform");
        constraintVertticalForm.add(calculateSimplex);

        calculateSimplex.addClickListener(buttonClickEvent1 -> {


            System.out.println("totalDecisionVariables = " + totalDecisionVariables);
            System.out.println("totalConstraints = " + totalConstraints);

            // OF
            IntStream.iterate(0, i -> i + 1).limit(totalDecisionVariables).forEach(value -> {

                objectiveFunction.add(Double.valueOf(objectiveFunction_TextField.get(value).getValue()));
            });

            System.out.println("objectiveFunction = " + objectiveFunction.toString() + " \t Size " + objectiveFunction.size());

            // LC AND RC
            IntStream.iterate(0, i -> i + 1).limit(totalConstraints).forEach(in -> {

                leftConstraint.add(new LinkedList<>());
                IntStream.iterate(0, i -> i + 1).limit(totalDecisionVariables).forEach(z -> {

                    leftConstraint.get(in).add(Double.valueOf(leftCon_TextField.get(z).getValue()));
                });

                rightConstraint.add(Double.valueOf(rightCon_TextField.get(in).getValue()));
            });

            LPModel model = new LPModel(objectiveFunction, leftConstraint, rightConstraint);
            lpmodeloutput.setValue(model.printLPMODEL());

        });

    }

    private void setToIntegerOnlyField(TextField field) {
        field.setPattern("[0-9]*");
        field.setPreventInvalidInput(true);
        field.setMaxLength(3);
    }

    public int getTotalDecisionVariables() {
        return totalDecisionVariables;
    }

    public void setTotalDecisionVariables(int totalDecisionVariables) {
        this.totalDecisionVariables = totalDecisionVariables;
    }

    public int getTotalConstraints() {
        return totalConstraints;
    }

    public void setTotalConstraints(int totalConstraints) {
        this.totalConstraints = totalConstraints;
    }
}
