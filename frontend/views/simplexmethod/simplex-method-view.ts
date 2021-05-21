import { customElement, html, LitElement } from 'lit-element';
import '@vaadin/vaadin-form-layout/src/vaadin-form-layout.js';

@customElement('simplex-method-view')
export class SimplexMethodView extends LitElement {
  createRenderRoot() {
    // Do not use a shadow root
    return this;
  }

  render() {
    return html`
<div style="width: 100%;">
 <h3 style="width: 100%;">Please enter Decision-Variables and Constraints </h3>
 <vaadin-form-layout style="width: 100%;" id="vaadinFormLayout">
  <vaadin-text-field label="No Of Decision Variables" id="dv" clear-button-visible helper-text="Numeric only"></vaadin-text-field>
  <vaadin-text-field label="No Of Constraints" id="nocons" helper-text="Numeric only"></vaadin-text-field>
 </vaadin-form-layout>
 <vaadin-horizontal-layout style="margin-top: var(--lumo-space-m); margin-bottom: var(--lumo-space-l); flex-direction: column; align-items: center;" theme="spacing" id="vaadinHorizontalLayout">
  <vaadin-button theme="primary" id="action" style="align-self: center; flex-grow: 0;">
    Action 
  </vaadin-button>
 </vaadin-horizontal-layout>
 <h3 style="width: 100%;" id="obj_function_h3">Objective Function</h3>
 <vaadin-form-layout id="form"></vaadin-form-layout>
</div>
`;
  }
}
