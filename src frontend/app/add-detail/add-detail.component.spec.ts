import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDetailComponent } from './add-detail.component';

describe('AddDetailComponent', () => {
  let component: AddDetailComponent;
  let fixture: ComponentFixture<AddDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
