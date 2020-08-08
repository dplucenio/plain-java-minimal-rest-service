package io.plucen;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Pair<T,U> {
  private final T first;
  private final U second;
}
