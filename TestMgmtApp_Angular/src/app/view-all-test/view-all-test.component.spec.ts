import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllTestComponent } from './view-all-test.component';

describe('ViewAllTestComponent', () => {
  let component: ViewAllTestComponent;
  let fixture: ComponentFixture<ViewAllTestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllTestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
