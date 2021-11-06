import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePostTextComponent } from './create-post-text.component';

describe('CreatePostTextComponent', () => {
  let component: CreatePostTextComponent;
  let fixture: ComponentFixture<CreatePostTextComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatePostTextComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatePostTextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
