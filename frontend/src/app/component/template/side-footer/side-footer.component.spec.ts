import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SideFooterComponent } from './side-footer.component';

describe('SideFooterComponent', () => {
  let component: SideFooterComponent;
  let fixture: ComponentFixture<SideFooterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SideFooterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SideFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
