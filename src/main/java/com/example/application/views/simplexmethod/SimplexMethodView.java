package com.example.application.views.simplexmethod;

import com.example.application.data.service.SamplePersonService;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;

import java.util.ArrayList;
import java.util.List;

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

    @Id("constraintVertticalForm")
    private VerticalLayout constraintVertticalForm;
    @Id("topHL")
    private HorizontalLayout topHL;
    @Id("ofForm")
    private HorizontalLayout ofForm;


    public SimplexMethodView(SamplePersonService personService) {
//        topHL.getStyle().set("border", "1px solid #9E9E9E");

        obj_function_h3.setVisible(false);
        constraint_h3.setVisible(false);

        setToIntegerOnlyField(nocons);
        setToIntegerOnlyField(dv);
        action.addClickListener(this::showButtonClickedMessage);

    }


    private void showButtonClickedMessage(ClickEvent<Button> buttonClickEvent) {
        List<TextField> objectiveFunction_TextField = new ArrayList<>();
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

                perConstraintData.add(constraintTextField);

            }

            constraintVertticalForm.add(perConstraintData);
        }


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
