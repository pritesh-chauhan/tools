import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletetodoComponent } from './deletetodo.component';

describe('DeletetodoComponent', () => {
  let component: DeletetodoComponent;
  let fixture: ComponentFixture<DeletetodoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeletetodoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeletetodoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
