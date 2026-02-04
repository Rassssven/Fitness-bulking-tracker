import { TestBed } from '@angular/core/testing';

import { TestSpring } from './test-spring';

describe('TestSpring', () => {
  let service: TestSpring;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TestSpring);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
