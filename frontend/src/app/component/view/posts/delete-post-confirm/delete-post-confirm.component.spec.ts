import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletePostConfirmComponent } from './delete-post-confirm.component';

describe('DeletePostConfirmComponent', () => {
  let component: DeletePostConfirmComponent;
  let fixture: ComponentFixture<DeletePostConfirmComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeletePostConfirmComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeletePostConfirmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
