import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LabInterfaceComponent } from './lab-interface.component';

describe('LabInterfaceComponent', () => {
  let component: LabInterfaceComponent;
  let fixture: ComponentFixture<LabInterfaceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LabInterfaceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LabInterfaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
