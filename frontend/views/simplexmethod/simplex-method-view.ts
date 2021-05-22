import { customElement, html, LitElement } from 'lit-element';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-area.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@polymer/iron-icon/iron-icon.js';

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
  <vaadin-vertical-layout style="justify-content: flex-end; align-items: center;">
   <h4>Run Demo Code</h4>
   <vaadin-button theme="icon" aria-label="Add new" id="sampleButton">
    <iron-icon icon="lumo:plus" style="margin: var(--lumo-space-s); margin-top: var(--lumo-space-s);"></iron-icon>Run 
   </vaadin-button>
  </vaadin-vertical-layout>
 </vaadin-horizontal-layout>
 <h3 style="width: 100%;" id="obj_function_h3">Objective Function</h3>
 <vaadin-horizontal-layout id="ofForm"></vaadin-horizontal-layout>
 <h3 style="width: 100%;" id="constraint_h3">Constraints</h3>
 <vaadin-vertical-layout id="constraintVertticalForm" style="align-items: flex-start;"></vaadin-vertical-layout>
 <vaadin-horizontal-layout style="justify-content: space-around; align-content: flex-end; align-items: stretch; height: 100%;">
  <vaadin-vertical-layout style="align-items: center; justify-content: space-between; height: 100%; width: 100%; margin-right: var(--lumo-space-m); margin-left: var(--lumo-space-m);">
   <h3>LP Model Formulize </h3>
   <vaadin-text-area id="lpmodeloutput" style="width: 100%;" readonly has-value></vaadin-text-area>
  </vaadin-vertical-layout>
  <vaadin-vertical-layout style="height: 100%; align-items: center; width: 100%; margin-left: var(--lumo-space-m); margin-right: var(--lumo-space-m); flex-direction: column;">
   <h3>Simplex Table</h3>
   <vaadin-text-area style="width: 100%;" id="simplexTableoutput" readonly></vaadin-text-area>
  </vaadin-vertical-layout>
  <div style="flex-grow:1; width:100%;" id="grid-wrapper">
   <slot name="vaadinGrid"></slot>
  </div>
 </vaadin-horizontal-layout>
</div>
`;
  }
}
