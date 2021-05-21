package com.example.application.views.simplexmethod;

import com.example.application.data.entity.SamplePerson;
import com.example.application.data.service.SamplePersonService;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.component.Component;

import java.util.ArrayList;
import java.util.Collections;
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
public class SimplexMethodView extends LitTemplate {

    private int decisionVar;
    private int constraint;

    @Id("dv")
    private TextField dv;
    @Id("nocons")
    private TextField nocons;
    @Id("action")
    private Button action;
    @Id("form")
    private FormLayout form;
    @Id("obj_function_h3")
    private H3 obj_function_h3;

    public SimplexMethodView(SamplePersonService personService) {
        obj_function_h3.setVisible(false);

        setToIntegerOnlyField(nocons);
        setToIntegerOnlyField(dv);
        action.addClickListener(this::showButtonClickedMessage);

    }


    private void showButtonClickedMessage(ClickEvent<Button> buttonClickEvent) {
        form.removeAll();
        obj_function_h3.setVisible(true);
        int decisionVar = Integer.parseInt(dv.getValue());
        int constraint = Integer.parseInt(nocons.getValue());
        setDecisionVar(decisionVar);
        setConstraint(constraint);

        List<TextField> objectiveFunction_TextField = new ArrayList<>();
        for (int index = 0; index < getDecisionVar(); index++) {
            TextField titleField = new TextField();
            titleField.setLabel("Decision variable x" + (index + 1));
            titleField.setPlaceholder("x" + (index + 1));
            titleField.setWidth("5%");
//            titleField.setMaxWidth("25%");

            objectiveFunction_TextField.add(titleField);
            form.add(objectiveFunction_TextField.get(index));

            form.setResponsiveSteps(new FormLayout.ResponsiveStep("15%", index + 1));
        }


        objectiveFunction_TextField = null;

    }

    private void setToIntegerOnlyField(TextField field) {
        field.setPattern("[0-9]*");
        field.setPreventInvalidInput(true);
        field.setMaxLength(3);
    }

    public int getDecisionVar() {
        return decisionVar;
    }

    public void setDecisionVar(int decisionVar) {
        this.decisionVar = decisionVar;
    }

    public int getConstraint() {
        return constraint;
    }

    public void setConstraint(int constraint) {
        this.constraint = constraint;
    }
}
