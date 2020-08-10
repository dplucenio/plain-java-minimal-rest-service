package io.plucen.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Pair<T, U> {

  private final T first;
  private final U second;

  public static <T, U> Pair<T, U> of(T t, U u) {
    return new Pair<>(t, u);
  }
}
