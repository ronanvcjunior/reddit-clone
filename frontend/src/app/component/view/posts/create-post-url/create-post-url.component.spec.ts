import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePostUrlComponent } from './create-post-url.component';

describe('CreatePostUrlComponent', () => {
  let component: CreatePostUrlComponent;
  let fixture: ComponentFixture<CreatePostUrlComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatePostUrlComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatePostUrlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
