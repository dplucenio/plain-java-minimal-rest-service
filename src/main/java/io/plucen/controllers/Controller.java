package io.plucen.controllers;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {

  void execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
