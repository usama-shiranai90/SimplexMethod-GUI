import { customElement, html, LitElement } from 'lit-element';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';

@customElement('simplex-method-view')
export class SimplexMethodView extends LitElement {
  createRenderRoot() {
    // Do not use a shadow root
    return this;
  }

  render() {
    return html`
<div>
 <h1 class="welcome" style="text-align:center">Welcome To Simplex Method Solver</h1>
 <h3 style="width: 100%;">Please enter Decision-Variables and Constraints </h3>
 <vaadin-horizontal-layout id="topHL" style="justify-content: space-evenly; align-items: flex-end;">
  <vaadin-text-field label="No Of Decision Variables" id="dv" clear-button-visible style="flex-grow: 0; flex-shrink: 0;"></vaadin-text-field>
  <vaadin-text-field label="No Of Constraints" id="nocons"></vaadin-text-field>
  <vaadin-button theme="primary" id="action" style="align-self: flex-end; flex-grow: 0;background-color:gray">
    Action 
  </vaadin-button>
 </vaadin-horizontal-layout>
 <h3 style="width: 100%;" id="obj_function_h3">Objective Function</h3>
 <vaadin-horizontal-layout id="ofForm"></vaadin-horizontal-layout>
 <h3 style="width: 100%;" id="constraint_h3">Constraints</h3>
 <vaadin-vertical-layout id="constraintVertticalForm" style="align-items: flex-start;"></vaadin-vertical-layout>
</div>
`;
  }
}
