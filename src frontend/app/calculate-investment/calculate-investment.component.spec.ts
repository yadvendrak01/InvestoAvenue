import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CalculateInvestmentComponent } from './calculate-investment.component';

describe('CalculateInvestmentComponent', () => {
  let component: CalculateInvestmentComponent;
  let fixture: ComponentFixture<CalculateInvestmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CalculateInvestmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CalculateInvestmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
