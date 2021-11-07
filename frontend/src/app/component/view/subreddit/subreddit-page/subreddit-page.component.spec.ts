import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubredditPageComponent } from './subreddit-page.component';

describe('SubredditPageComponent', () => {
  let component: SubredditPageComponent;
  let fixture: ComponentFixture<SubredditPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubredditPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubredditPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
