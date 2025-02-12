// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
import { Component, Input, Directive } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';

export declare type BooleanInput = string | boolean | null | undefined;

@Component({
  selector: 'mat-sidenav',
  standalone: true,
  template: "",
})
class SideNav {
  private value: boolean
  get fixedInViewport(): boolean {
    return this.value;
  }
  @Input()
  set fixedInViewport(value: BooleanInput) {
    this.value = value === true
  }
}

@Directive({
   selector: '[icn-btn]',
   standalone: true,
   template: "",
 })
class IcnBtn {
  private value: boolean
  get disabled(): boolean {
    return this.value;
  }
  @Input()
  set disabled(value: { foo: string }) {
    this.value = value.foo === "12"
  }
}

@Component({
   selector: 'app-root',
   template: `
      <div>
          <button disabled mat-icon-button></button>
          <button <error descr="Type \"\" is not assignable to type { foo: string }">disabled</error> icn-btn></button>
          <app-root <error descr="Type undefined is not assignable to type string">[foo]</error>></app-root>
          <mat-sidenav
            class="sidenav"
            fixedInViewport/>
          <mat-sidenav
            class="sidenav"
            [fixedInViewport]="<error descr="Type number is not assignable to type BooleanInput...  Type number is not assignable to type string | boolean | null | undefined">12</error>"/>
      </div>
  `,
  standalone: true,
  imports: [MatButtonModule,SideNav,IcnBtn]
})
export class AppComponent {

  @Input()
  protected foo!: string

}